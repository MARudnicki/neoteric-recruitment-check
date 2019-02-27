package pl.filipdawidczyk.application.model;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.util.List;

@Builder
@Value
@JsonDeserialize(builder = NbpCurrencyResponse.NbpCurrencyResponseBuilder.class)
public class NbpCurrencyResponse {

    @NonNull
    List<Rate> rates;

    @JsonPOJOBuilder(withPrefix = "")
    public static class  NbpCurrencyResponseBuilder{}

}
