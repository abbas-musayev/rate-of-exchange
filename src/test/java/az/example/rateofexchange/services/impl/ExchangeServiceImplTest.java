package az.example.rateofexchange.services.impl;

import az.example.rateofexchange.domain.ValuteCursEnt;
import az.example.rateofexchange.domain.ValuteEnt;
import az.example.rateofexchange.domain.ValuteTypeEnt;
import az.example.rateofexchange.enums.ValuteEnum;
import az.example.rateofexchange.exception.NotFoundCustomException;
import az.example.rateofexchange.mapper.ValuteCursMapper;
import az.example.rateofexchange.mapper.ValuteMapper;
import az.example.rateofexchange.mapper.ValuteTypeMapper;
import az.example.rateofexchange.repository.ValuteCursRepo;
import az.example.rateofexchange.repository.ValuteRepo;
import az.example.rateofexchange.repository.ValuteTypeRepo;
import az.example.rateofexchange.rest.dtos.request.ExchangeRequestDTO;
import az.example.rateofexchange.rest.dtos.response.Valute;
import az.example.rateofexchange.rest.dtos.response.ValuteCurs;
import az.example.rateofexchange.rest.dtos.response.ValuteType;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.will;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExchangeServiceImplTest {

    @InjectMocks
    ExchangeServiceImpl exchangeServiceImpl;

    @Mock
    private ValuteCursRepo valuteCursRepo;

    @Mock
    private ValuteTypeRepo valuteTypeRepo;

    @Mock
    public ValuteRepo valuteRepo;

    @Mock
    private ValuteCursMapper valuteCursMapper;

    @Mock
    private ValuteTypeMapper valuteTypeMapper;

    @Mock
    private ValuteMapper valuteMapper;

    @Mock
    private HttpClient httpClient;

    @Mock
    private HttpResponse<String> httpResponse;

    static Valute valute = new Valute();

    static ValuteType valuteType = new ValuteType();

    static ValuteCurs valuteCurs = new ValuteCurs();

    static ValuteEnt valuteEnt = new ValuteEnt();

    static Set<ValuteTypeEnt> valuteTypes = new HashSet<>();

    static ValuteCursEnt valuteCursEnt = new ValuteCursEnt();

    @BeforeAll
    static void setUp(){
        String date = "01.01.2022";
        valute.setCode(ValuteEnum.USD.name());
        valute.setNominal("1");
        valute.setName("US Dollar");
        valute.setValue(new BigDecimal(1.0));

        valuteType.setType("Foreign Currency");

        valuteType.setValute(Set.of(valute));

        valuteCurs.setDate(date);
        valuteCurs.setName("Currency Exchange Rates");
        valuteCurs.setValuteType(Set.of(valuteType));

        ValuteEnum valute = ValuteEnum.USD;

        ValuteTypeEnt valuteTypeEnt = new ValuteTypeEnt();
        valuteTypeEnt.setId(1L);
        valuteTypeEnt.setValutes(Set.of(valuteEnt));
        valuteEnt.setCode("840");
        valuteEnt.setNominal("1");
        valuteEnt.setName("US Dollar");
        valuteEnt.setValue(BigDecimal.valueOf(1.0));

        // Create a mock Set of ValuteTypeEnt
        valuteTypes.add(valuteTypeEnt);

        valuteCursEnt.setValuteTypes(valuteTypes);

    }

//    @Test
//    void testGetCurrencyByDateAndValute() {
//        // Arrange
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
//        LocalDate date = LocalDate.parse("03.01.2022", formatter);
//
////        when(exchangeServiceImpl.checkWeekendDate(date)).thenReturn(date);
//
//        when(valuteRepo.findByDateAndValute(date, ValuteEnum.USD.name())).thenReturn(valuteEnt);
//
//        // Act
//        Valute result = exchangeServiceImpl.getCurrencyByDateAndValute("03.01.2022", ValuteEnum.USD);
//
//        // Assert
//        assertThat(result.getCode()).isEqualTo(ValuteEnum.USD);
//        assertThat(result.getNominal()).isEqualTo(1);
//        verify(valuteMapper, times(1)).toDto(valuteEnt);
//        assertThat(result.getName()).isEqualTo("US Dollar");
//        assertThat(result.getValue()).isEqualTo("1.0");
//    }

    @Test
    void saveCurrencyWhenDataExistsInDatabase() {
        String date = "20.02.2023";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);

        ExchangeRequestDTO exchangeRequestDTO = new ExchangeRequestDTO();
        exchangeRequestDTO.setDate(date);

        when(valuteCursRepo.findByDate(localDate)).thenReturn(valuteCursEnt);

        exchangeServiceImpl.saveCurrency(exchangeRequestDTO);

        verify(valuteCursMapper,times(1)).toDto(valuteCursEnt);
    }


    @Test
    void testGetCurrencyByValuteWhenFindInDatabase(){

        ValuteEnum valute = ValuteEnum.USD;

        ValuteEnt valuteEnt = new ValuteEnt();

        ValuteTypeEnt valuteTypeEnt = new ValuteTypeEnt();
        valuteTypeEnt.setId(1L);
        valuteTypeEnt.setValutes(Set.of(valuteEnt));
        valuteEnt.setCode("840");
        valuteEnt.setNominal("1");
        valuteEnt.setName("US Dollar");
        valuteEnt.setValue(BigDecimal.valueOf(1.0));

        // Create a mock Set of ValuteTypeEnt
        Set<ValuteTypeEnt> valuteTypes = new HashSet<>();
        valuteTypes.add(valuteTypeEnt);

        when(valuteTypeRepo.findByValute(valute.name())).thenReturn(valuteTypes);

        Set<ValuteType> result = exchangeServiceImpl.getCurrencyByValute(valute);

        assertNotNull(result);
        verify(valuteTypeMapper,times(1)).toDto(valuteTypes);
    }

    @Test
    void testGetCurrencyByValuteThrowExceptionWhenNotExistsDatabase(){

        ValuteEnum valute = ValuteEnum.USD;

        when(valuteTypeRepo.findByValute(valute.name())).thenReturn(Collections.emptySet());

        Assertions.assertThrows(NotFoundCustomException.class, () -> {
            exchangeServiceImpl.getCurrencyByValute(valute);
        });
    }

    @Test
    void checkWeekendDate_dateOnWeekend_shouldReturnFridayDate() {
        // Given
        String weekendDate = "20.03.2022";
        ExchangeRequestDTO dto = new ExchangeRequestDTO();
        dto.setDate(weekendDate);
        LocalDate expectedDate = LocalDate.parse("18.03.2022", DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        // When
        LocalDate actualDate = exchangeServiceImpl.checkWeekendDate(LocalDate.parse(weekendDate, DateTimeFormatter.ofPattern("dd.MM.yyyy")));

        // Then
        assertEquals(expectedDate, actualDate);
    }

//    @Test
//    void testCallFeignCBARClient() throws Exception {
//        // Arrange
//        String date = "01.01.2022";
//        String url = "https://www.cbar.az/currencies/";
//
//        String responseString = "<ValuteCurs Date=\"01.01.2022\" name=\"Currency Exchange Rates\"><Valute Type=\"Foreign Currency\"><Nominal>1</Nominal><Name>US Dollar</Name><Value>1.0</Value><Code>USD</Code></Valute></ValuteCurs>";
//
//        when(httpResponse.body()).thenReturn(responseString);
//
////        when(httpClient.send(any(), any()))
////                .thenReturn(HttpResponse.<String>newBuilder().body("some string", StandardCharsets.UTF_8).build());
////        when(httpClient.send(any(), any()))
////                .thenReturn(HttpResponse.BodyHandlers.ofString("", StandardCharsets.UTF_8));
//
//        // Act
//        ValuteCurs result = exchangeServiceImpl.callFeignCBARClient(date);
//
//        // Assert
//        assertNotNull(result);
//        assertThat(result.getDate()).isEqualTo(date);
//        assertThat(result.getName()).isEqualTo("Currency Exchange Rates");
//        assertThat(result.getValuteType()).hasSize(1);
//        assertThat(result.getValuteType().iterator().next().getType()).isEqualTo("Foreign Currency");
//        assertThat(result.getValuteType().iterator().next().getValute()).hasSize(1);
//        assertThat(result.getValuteType().iterator().next().getValute().iterator().next().getCode()).isEqualTo("USD");
//        assertThat(result.getValuteType().iterator().next().getValute().iterator().next().getName()).isEqualTo("US Dollar");
//        assertThat(result.getValuteType().iterator().next().getValute().iterator().next().getNominal()).isEqualTo("1");
//        assertThat(result.getValuteType().iterator().next().getValute().iterator().next().getValue()).isEqualTo(new BigDecimal("1.0"));
//    }

    @Test
    void shouldThrowExceptionForInvalidDateFormat() {
        String date = "22/03/2023";
        ValuteEnum code = ValuteEnum.USD;
        Assertions.assertThrows(DateTimeParseException.class, () -> {
            exchangeServiceImpl.getCurrencyByDateAndValute(date, code);
        });
    }

    @Test
    void testConvertStringToLocalDate() {
        String date = "2022-05-17";
        String format = "yyyy-MM-dd";
        LocalDate expectedDate = LocalDate.of(2022, 5, 17);
        LocalDate actualDate = exchangeServiceImpl.convertStringToLocalDate(date, format);
        assertEquals(expectedDate, actualDate);
    }

    @Test
    void testSaveValuteCursIfNotExists() {

        Set<ValuteTypeEnt> byDate = Collections.emptySet();
        when(valuteTypeRepo.findByDate(any())).thenReturn(byDate);

        ValuteCursEnt valuteCursEnt = new ValuteCursEnt();
        when(valuteCursMapper.toEntity(valuteCurs)).thenReturn(valuteCursEnt);

        exchangeServiceImpl.saveValuteCursIfNotExists(valuteCurs);

        verify(valuteTypeRepo,times(1)).findByDate(any());
        verify(valuteCursMapper,times(1)).toEntity(valuteCurs);
        verify(valuteCursRepo,times(1)).save(valuteCursEnt);
    }


}