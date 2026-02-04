package galassi.fun.payment;

public interface MakePayment {

    Double executePayment();

    Double executePaymentWithLoan(Double loanValue);

}
