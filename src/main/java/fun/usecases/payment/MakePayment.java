package fun.usecases.payment;

import fun.usecases.payment.entity.PaymentInfo;

public interface MakePayment {
    MakePayment executePayment(PaymentInfo paymentInfo);

    MakePayment executePaymentWithLoan(Double loanValue, PaymentInfo paymentInfo);

    Double getBalance();


}
