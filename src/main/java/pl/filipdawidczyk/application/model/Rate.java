package pl.filipdawidczyk.application.model;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Builder
@Value
@JsonDeserialize(builder = Rate.RateBuilder.class)
public class Rate {
    BigDecimal bid;
    BigDecimal ask;

    @JsonPOJOBuilder(withPrefix = "")
    public static class  RateBuilder{}
}
