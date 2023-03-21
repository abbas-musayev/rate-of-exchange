package az.example.rateofexchange.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
@Slf4j
public class CustomAuthenticationEntryPoint extends GenericExceptionHandler implements AuthenticationEntryPoint{

    public CustomAuthenticationEntryPoint(TranslationRepoService translationRepoService) {
        super(translationRepoService);
    }

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse response,
                         AuthenticationException ex) throws IOException, ServletException {
        ObjectMapper mapper = new ObjectMapper();
        String message = NestedExceptionUtils.getMostSpecificCause(ex).getMessage();

//        createResponseDto(ex,)
        ErrorResponseDto errorDetails = ErrorResponseDto.builder()
                .status(404)
                .detail(message)
                .message(message)
                .path(httpServletRequest.getRequestURI())
                .timeStamp(DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss", Locale.ENGLISH)
                        .format(LocalDateTime.now()))
                .build();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        OutputStream responseStream = response.getOutputStream();
        mapper.writeValue(responseStream, errorDetails);
        response.setCharacterEncoding("UTF-8");
        responseStream.flush();
    }
}
