package az.example.rateofexchange.exception;

public class AuthRequestExcetion extends GenericException{
    public AuthRequestExcetion(int status, String code, String message, Object... arguments) {
        super(status, code, message, message, arguments);
    }
}
