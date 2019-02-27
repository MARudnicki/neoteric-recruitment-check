package pl.filipdawidczyk.application.client;


import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.filipdawidczyk.application.exception.NbpApiClientException;
import pl.filipdawidczyk.application.model.NbpCurrencyResponse;

import java.time.LocalDate;

import static org.springframework.http.HttpMethod.GET;

@AllArgsConstructor
@Component
public class NbpApiClient {

    private static final String NBP_API_URL = "http://api.nbp.pl/api/exchangerates/rates/C/{code}/{date}";

    private final RestTemplate restTemplate;

    public NbpCurrencyResponse getCurrencyResponse(final String code, final LocalDate date){
        final ResponseEntity<NbpCurrencyResponse> responseEntity = restTemplate.exchange(
                getPreparedUrl(code, date),
                GET,
                getPreparedHeaders(),
                NbpCurrencyResponse.class
        );

        if(!responseEntity.getStatusCode().equals(HttpStatus.OK)){
            throw new NbpApiClientException("Error during call to NBP api, status: " + responseEntity.getStatusCode());
        }

        return responseEntity.getBody();
    }

    private HttpEntity getPreparedHeaders() {
        final HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity(headers);
    }

    private String getPreparedUrl(final String code, final LocalDate date){
        return NBP_API_URL
                .replace("{code}", code)
                .replace("{date}", date.toString());
    }

}
