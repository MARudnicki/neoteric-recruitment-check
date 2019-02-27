package pl.filipdawidczyk.application.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.filipdawidczyk.application.client.NbpApiClient;
import pl.filipdawidczyk.application.exception.MissingDataException;
import pl.filipdawidczyk.application.exception.WrongRequestException;
import pl.filipdawidczyk.application.model.NbpCurrencyResponse;
import pl.filipdawidczyk.application.model.Rate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.regex.Pattern;

@AllArgsConstructor
@Service
public class TransactionService {

    private final NbpApiClient nbpApiClient;

    public BigDecimal calculate(final String code, final BigDecimal initialAmount, final LocalDate buyDate, final LocalDate sellDate){

        validateRequest(code, initialAmount, buyDate, sellDate);

        final NbpCurrencyResponse buyResponse = nbpApiClient.getCurrencyResponse(code, buyDate);

        final NbpCurrencyResponse sellResponse = nbpApiClient.getCurrencyResponse(code, sellDate);

        final BigDecimal ask = buyResponse.getRates().stream()
                .findFirst()
                .map(Rate::getAsk)
                .orElseThrow(() -> new MissingDataException("ask value not found"));

        final BigDecimal bid = sellResponse.getRates().stream()
                .findFirst()
                .map(Rate::getBid)
                .orElseThrow(() -> new MissingDataException("bid value not found"));


        return initialAmount
                .divide(ask, 2, RoundingMode.HALF_UP)
                .multiply(bid)
                .subtract(initialAmount);
    }


    private void validateRequest(final String code, final BigDecimal initialAmount, final LocalDate buyDate, final LocalDate sellDate) {

        Pattern pattern = Pattern.compile("^([a-zA-Z]{3})");

        if(!pattern.matcher(code).matches()){
            throw new WrongRequestException("Wrong currency code. Should be three letters");
        }

        if(initialAmount.compareTo(BigDecimal.ZERO) < 0){
            throw new WrongRequestException("Are you crazy ? Please type positive initial amount :) ");
        }

        if(buyDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) ||
                buyDate.getDayOfWeek().equals(DayOfWeek.SUNDAY) ||
                sellDate.isAfter(LocalDate.now()) ||
                buyDate.isAfter(sellDate)){

            throw new WrongRequestException("1.Buy date must be before sell date!. 2.No data for weekends. 3.Both dates should not be from future :)");

        }

    }
}
