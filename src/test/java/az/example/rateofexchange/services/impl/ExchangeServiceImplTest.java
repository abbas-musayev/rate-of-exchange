package az.example.rateofexchange.services.impl;

import az.example.rateofexchange.domain.ValuteCursEnt;
import az.example.rateofexchange.mapper.ValuteCursMapper;
import az.example.rateofexchange.repository.ValuteCursRepo;
import az.example.rateofexchange.rest.dtos.request.ExchangeRequestDTO;
import az.example.rateofexchange.rest.dtos.response.ExchangeDTO;
import az.example.rateofexchange.services.ExchangeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExchangeServiceImplTest {

    @Mock
    ExchangeService exchangeService;

    @Mock
    ExchangeServiceImpl exchangeServiceImpl;

    @Mock
    private  ValuteCursRepo valuteCursRepo;

    @Mock
    private ValuteCursMapper valuteCursMapper;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveCurrencyThrowExceptionWhenDateIsNotValid() {
        ExchangeRequestDTO exchangeRequestDTO = new ExchangeRequestDTO();
        exchangeRequestDTO.setDate("20.02.2023");
//        assertEquals(exchangeRequestDTO.getDate().equals(""));
    }

    @Test
    void saveCurrencySuccess() throws JAXBException, IOException, InterruptedException, TransformerException {
        String date = "20.02.2023";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);

        ExchangeRequestDTO exchangeRequestDTO = new ExchangeRequestDTO();
        exchangeRequestDTO.setDate(date);
        when(valuteCursRepo.findByDate(localDate)).thenReturn(null);

        ExchangeDTO exchangeDTO = exchangeService.saveCurrency(exchangeRequestDTO);

//        verify(exchangeServiceImpl,times(1)).callFeignCBARClient(date);
        verify(valuteCursRepo,times(1)).save(any());
        verify(valuteCursMapper,times(1)).toEntity(any());
    }

    @Test
    void getCurrencyByDateAndValute() {
    }

    @Test
    void getCurrencyByDate() {
    }

    @Test
    void deleteCurrency() {
    }
}