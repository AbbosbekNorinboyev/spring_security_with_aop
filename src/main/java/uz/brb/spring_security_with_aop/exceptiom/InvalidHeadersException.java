package uz.brb.spring_security_with_aop.exceptiom;

public class InvalidHeadersException extends RuntimeException  {
    public InvalidHeadersException(String message) {
        super(message);
    }

    public InvalidHeadersException(String message, Throwable cause) {
        super(message, cause);
    }
}