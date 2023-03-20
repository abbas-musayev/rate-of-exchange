package az.example.rateofexchange.rest;

import az.example.rateofexchange.rest.dtos.request.ExchangeRequestDTO;
import az.example.rateofexchange.rest.dtos.response.ExchangeDTO;
import az.example.rateofexchange.rest.dtos.response.Valute;
import az.example.rateofexchange.rest.dtos.response.ValuteType;
import az.example.rateofexchange.services.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/currency")
public class CurrencyController {

    private final ExchangeService exchangeService;

    @PostMapping
    public ResponseEntity<ExchangeDTO> saveCurrencyByDate(@RequestBody @Validated ExchangeRequestDTO dto) throws TransformerException, IOException, InterruptedException, JAXBException {
        return ResponseEntity.ok(exchangeService.saveCurrency(dto));
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteCurrencyByDate(@RequestParam String date){
        exchangeService.deleteCurrency(date);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/by-date-and-valute")
    public ResponseEntity<Valute> getCurrencyByDateAndValute(@RequestParam String date, @RequestParam String valute){
        return ResponseEntity.ok(exchangeService.getCurrencyByDateAndValute(date,valute));
    }

    @GetMapping("/by-date")
    public ResponseEntity<Set<ValuteType>> getCurrencyByDate(@RequestParam String date){
        return ResponseEntity.ok(exchangeService.getCurrencyByDate(date));
    }
}

