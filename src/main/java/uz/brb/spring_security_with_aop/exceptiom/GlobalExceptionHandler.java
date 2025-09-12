package uz.brb.spring_security_with_aop.exceptiom;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.brb.spring_security_with_aop.dto.response.Response;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

import static uz.brb.spring_security_with_aop.util.Util.localDateTimeFormatter;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response<?> exception(MethodArgumentNotValidException e) {
        Map<String, String> errors = e.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        DefaultMessageSourceResolvable::getDefaultMessage));
        return Response.builder()
                .code(HttpStatus.BAD_REQUEST.value())  // Bad request kodi
                .status(HttpStatus.BAD_REQUEST)
                .message("Validation error")
                .errors(errors)
                .success(false)
                .timestamp(localDateTimeFormatter(LocalDateTime.now()))
                .build();
    }

    // Not found (404)
    @ExceptionHandler(ResourceNotFoundException.class)
    public Response<?> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
        return Response.builder()
                .code(HttpStatus.NOT_FOUND.value())  // Not found request kodi
                .status(HttpStatus.NOT_FOUND)
                .message(resourceNotFoundException.getMessage())
                .success(false)
                .timestamp(localDateTimeFormatter(LocalDateTime.now()))
                .build();
    }

    @ExceptionHandler(Exception.class)
    public Response<?> handleException(Exception exception) {
        return Response.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())   // Internal Server Error request kodi
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message("Something wrong -> " + exception.getMessage())
                .success(false)
                .timestamp(localDateTimeFormatter(LocalDateTime.now()))
                .build();
    }

    // 🔹 Login / token xatoliklari (401)
    @ExceptionHandler({AuthenticationException.class, BadCredentialsException.class})
    public Response<?> handleAuthenticationException(Exception ex) {
        return Response.builder()
                .code(HttpStatus.UNAUTHORIZED.value())
                .status(HttpStatus.UNAUTHORIZED)
                .success(false)
                .message("Avtorizatsiya xatosi: " + ex.getMessage())
                .timestamp(localDateTimeFormatter(LocalDateTime.now()))
                .build();
    }

    // 🔹 Ruxsat yo‘q (403)
    @ExceptionHandler(AccessDeniedException.class)
    public Response<?> handleAccessDenied(AccessDeniedException ex) {
        return Response.builder()
                .code(HttpStatus.FORBIDDEN.value())
                .status(HttpStatus.FORBIDDEN)
                .message("Ruxsat yo‘q: " + ex.getMessage())
                .success(false)
                .timestamp(localDateTimeFormatter(LocalDateTime.now()))
                .build();
    }
}
