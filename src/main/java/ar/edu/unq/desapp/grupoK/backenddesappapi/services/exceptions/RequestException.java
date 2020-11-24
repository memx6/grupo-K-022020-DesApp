package ar.edu.unq.desapp.grupoK.backenddesappapi.services.exceptions;

public class RequestException extends Exception {
    private String err;

    public static RequestException createWith(String ex) {
        return new RequestException(ex);
    }

    private RequestException(String ex) {
        this.err = ex;
    }

    @Override
    public String getMessage() {
        return err;
    }
}
