package az.example.rateofexchange.exception;

public class CustomThrowException extends GenericException{
    public CustomThrowException(int status, ErrorCodesEnum code, String details, Object... arguments) {
        super(status, code.value, code.value, details, arguments);
    }
}
