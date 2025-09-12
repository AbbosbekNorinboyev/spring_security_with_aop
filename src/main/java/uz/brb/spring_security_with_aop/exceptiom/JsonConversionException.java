package uz.brb.spring_security_with_aop.exceptiom;

public class JsonConversionException extends RuntimeException {
    public JsonConversionException(String message, Throwable cause) {
        super(message, cause);
    }
}
