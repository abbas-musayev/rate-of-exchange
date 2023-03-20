package az.example.rateofexchange.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@RequiredArgsConstructor
@Service
@Slf4j
public class TranslationRepoService {

    private final MessageSource messageSource;

    public String findByKey(String key, String lang, Object... arguments) {
        try {
            return messageSource.getMessage(key,arguments,new Locale(lang));
        }catch (Exception ex){
            ex.printStackTrace();
            return key;
        }
    }
}
