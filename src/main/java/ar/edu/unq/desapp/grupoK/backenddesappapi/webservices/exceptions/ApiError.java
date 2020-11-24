package ar.edu.unq.desapp.grupoK.backenddesappapi.webservices.exceptions;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ApiError {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    private HttpStatus status;
    private String message;
    private List<String> errors;

    public ApiError(LocalDateTime timestamp, HttpStatus status, String message, List<String> details) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.errors = details;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public HttpStatus getStatus() {
        return status;
    }
    public String getMessage() {
        return message;
    }
    public List<String> getErrors() {
        return errors;
    }

}
