package fun.usecases.payment;

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
