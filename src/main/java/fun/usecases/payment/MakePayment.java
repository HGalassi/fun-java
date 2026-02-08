package fun.usecases.payment;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public interface MakePayment {

    Double executePayment();

    Double executePaymentWithLoan(Double loanValue);

}
