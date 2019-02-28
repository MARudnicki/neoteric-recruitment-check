package pl.filipdawidczyk.application.exception;

public class MissingDataException extends RuntimeException{
    public MissingDataException(String message) {
        super(message);
    }
}
