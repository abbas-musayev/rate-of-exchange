package az.example.rateofexchange.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

@Getter
public class GenericException extends RuntimeException{

    private final int status;
    private final String code;
    private final String message;
    private final String details;

    private final transient Object[] arguments;

    public GenericException(int status, String code, String message, String details, Object[] arguments) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.details = details;
        this.arguments = arguments;
    }

    public GenericException(String errorBody, HttpStatus status, String details){
        super(errorBody);
        this.message = errorBody;
        this.status=status.value();
        this.code=errorBody;
        this.details = details;
        this.arguments=null;
    }

    @Override
    public String toString() {
        return "GenericException{" +
                "status=" + status +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", arguments=" + Arrays.toString(arguments) +
                '}';
    }
}