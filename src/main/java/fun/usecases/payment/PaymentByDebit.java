package fun.usecases.payment;

public class PaymentByDebit implements MakePayment{

    private PaymentInfo paymentInfo;
    @Override
    public Double executePayment(PaymentInfo paymentInfo) {
        return null;
    }

    @Override
    public Double executePaymentWithLoan(Double loanValue,PaymentInfo paymentInfo) {
        return null;
    }
}
