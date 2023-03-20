package az.example.rateofexchange.services;

import az.example.rateofexchange.rest.dtos.response.ExchangeDTO;
import az.example.rateofexchange.rest.dtos.request.ExchangeRequestDTO;
import az.example.rateofexchange.rest.dtos.response.Valute;
import az.example.rateofexchange.rest.dtos.response.ValuteType;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.Set;

public interface ExchangeService {
    ExchangeDTO saveCurrency(ExchangeRequestDTO dto) throws TransformerException, IOException, InterruptedException, JAXBException;

    Valute getCurrencyByDateAndValute(String date, String valute);

    Set<ValuteType> getCurrencyByDate(String date);

    void deleteCurrency(String date);
}
