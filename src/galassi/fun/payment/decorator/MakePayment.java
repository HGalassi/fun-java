package galassi.fun.payment.decorator;

public interface MakePayment {

    Double executePayment();

    Double executePaymentWithLoan(Double loanValue);

}
