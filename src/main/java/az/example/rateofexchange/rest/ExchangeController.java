package az.example.rateofexchange.rest;

import az.example.rateofexchange.rest.dtos.request.ExchangeRequestDTO;
import az.example.rateofexchange.rest.dtos.response.ExchangeDTO;
import az.example.rateofexchange.services.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class ExchangeController {

    private final ExchangeService exchangeService;

    @GetMapping
    public ResponseEntity<ExchangeDTO> getExcaDto(@RequestBody @Validated ExchangeRequestDTO dto) throws TransformerException, IOException, InterruptedException, JAXBException {
        return ResponseEntity.ok(exchangeService.getRateOfExchange(dto));
    }
}

