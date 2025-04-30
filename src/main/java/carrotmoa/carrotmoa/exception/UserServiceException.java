package carrotmoa.carrotmoa.exception;

import lombok.Getter;

@Getter
public class UserServiceException extends RuntimeException {
    private final String errorCode;
    public UserServiceException(String errorCode,String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
