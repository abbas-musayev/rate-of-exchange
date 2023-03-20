package az.example.rateofexchange.services.impl;

import az.example.rateofexchange.domain.ValuteCursEnt;
import az.example.rateofexchange.domain.ValuteEnt;
import az.example.rateofexchange.domain.ValuteTypeEnt;
import az.example.rateofexchange.exception.NotFoundCustomException;
import az.example.rateofexchange.mapper.ValuteTypeMapper;
import az.example.rateofexchange.repository.ValuteRepo;
import az.example.rateofexchange.repository.ValuteTypeRepo;
import az.example.rateofexchange.rest.dtos.response.Valute;
import az.example.rateofexchange.rest.dtos.response.ValuteType;
import az.example.rateofexchange.mapper.ValuteCursMapper;
import az.example.rateofexchange.repository.ValuteCursRepo;
import az.example.rateofexchange.rest.dtos.request.ExchangeRequestDTO;
import az.example.rateofexchange.rest.dtos.response.ExchangeDTO;
import az.example.rateofexchange.rest.dtos.response.ValuteCurs;
import az.example.rateofexchange.mapper.ValuteMapper;
import az.example.rateofexchange.services.ExchangeService;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import static az.example.rateofexchange.exception.ErrorCodesEnum.VALUTE_CURS_NOT_FOUND;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExchangeServiceImpl implements ExchangeService {
    private final ValuteCursRepo valuteCursRepo;
    private final ValuteRepo valuteRepo;
    private final ValuteTypeRepo valuteTypeRepo;
    private final ValuteCursMapper valuteCursMapper;
    private final ValuteTypeMapper valuteTypeMapper;
    private final ValuteMapper valuteMapper;

    @Override
    public ExchangeDTO saveCurrency(ExchangeRequestDTO dto) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date = LocalDate.parse(dto.getDate(), formatter);

        LocalDate localDate = checkWeekendDate(date);
        ValuteCursEnt valuteCursEnt = valuteCursRepo.findByDate(localDate);
        if (valuteCursEnt !=null){
            log.info("DATA FOUND IN DATABASE");
            ValuteCurs map = valuteCursMapper.toDto(valuteCursEnt);
            return new ExchangeDTO(map);
        }
        else {
            ValuteCurs valuteCurs = callFeignCBARClient(dto.getDate());
            log.info("ValuteCurs not exists A DATABASE {}",valuteCurs);
            ValuteCursEnt map = valuteCursMapper.toEntity(valuteCurs);
            valuteCursRepo.save(map);
            log.info("ValuteCursEnt SAVED TO DATABASE");
            return new ExchangeDTO(valuteCurs);
        }
    }

    @Override
    public Valute getCurrencyByDateAndValute(String date, String code) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date1 = LocalDate.parse(date, formatter);

        LocalDate localDate = checkWeekendDate(date1);
        Valute response = new Valute();
        ValuteEnt byDateAndValute = valuteRepo.findByDateAndValute(localDate, code);
        if (byDateAndValute == null){
            log.info("VALUTE DOES NOT EXISTS IN DATABASE");

            ValuteCurs valuteCurs = callFeignCBARClient(date);

            Set<ValuteType> valuteType = valuteCurs.getValuteType();
            for (ValuteType type : valuteType) {
                Set<Valute> valute1 = type.getValute();
                for (Valute valute2 : valute1) {
                    if (valute2.getCode().equals(code)){
                        response = valute2;
                    }
                }
            }
            ValuteCursEnt valuteCursEnt = valuteCursMapper.toEntity(valuteCurs);
            valuteCursRepo.save(valuteCursEnt);
            log.info("VALUTE CURS SAVED IN DATABASE");
        }else {
            log.info("VALUTE EXISTS IN DATABASE");
            return valuteMapper.toDto(byDateAndValute);
        }
        return response;
    }

    @Override
    public Set<ValuteType> getCurrencyByDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date1 = LocalDate.parse(date, formatter);

        LocalDate localDate = checkWeekendDate(date1);
        Set<ValuteTypeEnt> valuteTypeEnts = valuteTypeRepo.findByDate(localDate);
        if (valuteTypeEnts.isEmpty()){
            ValuteCurs valuteCurs = callFeignCBARClient(date);
            ValuteCursEnt valuteCursEnt = valuteCursMapper.toEntity(valuteCurs);
            valuteCursRepo.save(valuteCursEnt);
            log.info("VALUTE CURS SAVED IN DATABASE");
            return valuteCurs.getValuteType();
        }else {
            return valuteTypeMapper.toDto(valuteTypeEnts);
        }
    }

    @Override
    public void deleteCurrency(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        localDate = checkWeekendDate(localDate);
        ValuteCursEnt valuteCursEnt = valuteCursRepo.findByDate(localDate);
        if (valuteCursEnt != null){
            valuteCursRepo.delete(valuteCursEnt);
        }else {
            throw new NotFoundCustomException(VALUTE_CURS_NOT_FOUND,"No information was found for the selected date");
        }
    }


    private ValuteCurs callFeignCBARClient(String date ){
        String url = "https://www.cbar.az/currencies/";
        HttpClient httpClient = HttpClient.newBuilder().build();
        try {
            java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
                    .uri(URI.create(url + date + ".xml"))
                    .build();
            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());
            log.info("<<<<<<CALLED FEIGN CLIENT>>>>>>");
            XmlMapper xmlMapper1 = new XmlMapper();
            return xmlMapper1.readValue(response.body(), ValuteCurs.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    private LocalDate checkWeekendDate(LocalDate date){
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        // Əgər tarix həftə sonuna uyğun gəlirsə, Cümə günününə olan tarixi qaytarır
        if (dayOfWeek == DayOfWeek.SUNDAY) {
            return date.minusDays(2);
        }else if (dayOfWeek == DayOfWeek.SATURDAY){
            return date.minusDays(1);
        }
        else {
            return date;
        }
    }
}
