package fun.usecases.payment;

import fun.usecases.payment.entity.PaymentInfo;

public class PaymentByDebit implements MakePayment{

    private PaymentInfo paymentInfo;
    @Override
    public MakePayment executePayment(PaymentInfo paymentInfo) {
        return null;
    }

    @Override
    public MakePayment executePaymentWithLoan(Double loanValue, PaymentInfo paymentInfo) {
        return null;
    }

    @Override
    public Double getBalance() {
        return null;
    }
}
