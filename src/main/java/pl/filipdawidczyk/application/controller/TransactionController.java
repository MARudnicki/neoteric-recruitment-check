package pl.filipdawidczyk.application.controller;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.filipdawidczyk.application.model.SimulatedTransactionResponse;
import pl.filipdawidczyk.application.service.TransactionService;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@RequestMapping("/transaction")
@RestController
public class TransactionController {

    private TransactionService transactionService;

    @GetMapping("/simulate")
    public ResponseEntity<SimulatedTransactionResponse> getSimulatedTransaction(
            @RequestParam String code,
            @RequestParam BigDecimal amount,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate buyDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate sellDate
    ) {
        return ResponseEntity.ok(
                SimulatedTransactionResponse.of(
                        transactionService.calculate(code, amount, buyDate, sellDate).toString() + " PLN"
                )
        );
    }

}
