package pl.filipdawidczyk.application.model;

import lombok.Value;

@Value(staticConstructor = "of")
public class Error {
    String message;
}
