package az.example.rateofexchange.services.impl;

import az.example.rateofexchange.rest.dtos.request.ExchangeRequestDTO;
import az.example.rateofexchange.rest.dtos.response.ExchangeDTO;
import az.example.rateofexchange.rest.dtos.response.ValuteCurs;
import az.example.rateofexchange.services.ExchangeService;
//import org.apache.http.client.HttpClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExchangeServiceImpl implements ExchangeService {



    public ExchangeDTO getRateOfExchange(ExchangeRequestDTO dto) throws TransformerException, IOException, InterruptedException, JAXBException {
        String url = "https://www.cbar.az/currencies/";
        String date = dto.getDate();
        HttpClient httpClient = HttpClient.newBuilder().build();
        try {
            java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
                    .uri(URI.create(url + date + ".xml"))
                    .build();
            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());
            XmlMapper xmlMapper1 = new XmlMapper();
            ValuteCurs valuteCurs = xmlMapper1.readValue(response.body(), ValuteCurs.class);


            return ExchangeDTO.builder()
                    .valCurs(valuteCurs)
                    .build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
