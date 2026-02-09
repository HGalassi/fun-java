package fun.usecases.payment;

public interface MakePayment {
    Double executePayment(PaymentInfo paymentInfo);

    Double executePaymentWithLoan(Double loanValue, PaymentInfo paymentInfo);
}
