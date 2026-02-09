package fun.usecases.payment;

public interface MakePayment {
    MakePayment executePayment(PaymentInfo paymentInfo);

    MakePayment executePaymentWithLoan(Double loanValue, PaymentInfo paymentInfo);

    Double getBalance();
}
