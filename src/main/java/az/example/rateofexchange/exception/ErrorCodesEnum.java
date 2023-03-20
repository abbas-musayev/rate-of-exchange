package az.example.rateofexchange.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ErrorCodesEnum {

    USER_NOT_FOUND("USER-NOT-FOUND"),
    ROLE_NOT_FOUND("ROLE-NOT-FOUND"),
    PASSWORD_IS_NOT_CORRECT("PASSWORD-IS-NOT-CORRECT"),
    VALUTE_CURS_NOT_FOUND("VALUTE-CURS-NOT-FOUND");

    public final String value;
}
