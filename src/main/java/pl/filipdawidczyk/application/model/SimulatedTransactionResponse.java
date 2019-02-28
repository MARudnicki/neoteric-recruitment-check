package pl.filipdawidczyk.application.model;

import lombok.Value;

@Value(staticConstructor = "of")
public class SimulatedTransactionResponse {
    String result;
}
