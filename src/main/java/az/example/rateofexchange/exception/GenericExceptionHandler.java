package az.example.rateofexchange.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

import static org.springframework.http.HttpHeaders.ACCEPT_LANGUAGE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GenericExceptionHandler {

    private final TranslationRepoService translationRepoService;

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ErrorResponseDto> exceptionHandle(GenericException ex, WebRequest webRequest){
//        ex.printStackTrace();
        ErrorResponseDto response = createResponseDto(ex, webRequest);
        log.error("!!! ERROR !!!");
        log.error("RESPONSE IS {} ",response);
        return ResponseEntity.status(ex.getStatus()).body(response);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> generalExceptionHandle(Exception ex, WebRequest webRequest){
        ex.printStackTrace();
        String path = ((ServletWebRequest) webRequest).getRequest().getRequestURL().toString();
        String message = NestedExceptionUtils.getMostSpecificCause(ex).getMessage();
        String lang = webRequest.getHeader(ACCEPT_LANGUAGE);

        ErrorResponseDto response = ErrorResponseDto.builder()
                .status(INTERNAL_SERVER_ERROR.value())
//                .code(ex.getCode())
                .message(translationRepoService.findByKey(message,lang))
                .detail(translationRepoService.findByKey(ex.getMessage()+"_DETAIL",lang))
                .timeStamp(LocalDateTime.now().toString())
                .path(path)
                .build();
        log.error("!!! ERROR !!!");
        log.error("RESPONSE IS {} ",response);
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(response);
    }

//    @ExceptionHandler({ AuthenticationException.class })
//    @ResponseBody
//    public ResponseEntity<ErrorResponseDto> handleAuthenticationException(Exception ex,WebRequest webRequest) {
//
//        log.info("**** AuthenticationException OCCOURED ****");
//        ErrorResponseDto response = createResponseDto(ex, webRequest);
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//    }

    @ExceptionHandler(AuthRequestExcetion.class)
    public ResponseEntity<ErrorResponseDto> AuthRequestExcetionHandle(AuthRequestExcetion ex, WebRequest webRequest){
        ex.printStackTrace();
        ErrorResponseDto response = createResponseDto(ex, webRequest);
        log.error("!!! ERROR !!!");
        log.error("RESPONSE IS {} ",response);
        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

    public ErrorResponseDto createResponseDto(GenericException ex,WebRequest webRequest){

        String path = ((ServletWebRequest) webRequest).getRequest().getRequestURL().toString();
        String message = NestedExceptionUtils.getMostSpecificCause(ex).getMessage();
        String lang = webRequest.getHeader(ACCEPT_LANGUAGE);

        return ErrorResponseDto.builder()
                .status(ex.getStatus())
                .code(ex.getCode())
                .message(translationRepoService.findByKey(message,lang))
                .detail(ex.getDetails())
                .timeStamp(LocalDateTime.now().toString())
                .path(path)
                .build();
    }


}
