package az.example.rateofexchange.services;

import az.example.rateofexchange.rest.dtos.response.ExchangeDTO;
import az.example.rateofexchange.rest.dtos.request.ExchangeRequestDTO;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public interface ExchangeService {
    ExchangeDTO getRateOfExchange(ExchangeRequestDTO dto) throws TransformerException, IOException, InterruptedException, JAXBException;
}
