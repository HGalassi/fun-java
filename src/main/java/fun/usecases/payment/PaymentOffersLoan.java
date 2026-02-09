package fun.usecases.payment;

import org.springframework.stereotype.Service;

@Service("paymentOffersLoan")
public class PaymentOffersLoan implements MakePayment {

    private MakePayment makePayment;
    private PaymentInfo paymentInfo;

    public Double balance;

    public PaymentOffersLoan() {
    }

    public PaymentOffersLoan(MakePayment makePayment, PaymentInfo paymentInfo) {
        this.makePayment = makePayment;
        this.paymentInfo = paymentInfo;
    }

    @Override
    public MakePayment executePayment(PaymentInfo paymentInfo) {
        Double loanValue;
        //TODO: doThings
        return this;
    }

    //TODO: Ajustar quando empréstimo não é aceito valor não reflete negativo.
    @Override
    public MakePayment executePaymentWithLoan(Double loanValue, PaymentInfo paymentInfo) {
        //TODO: Find Fees
        System.out.println("Payment finished without loan and actual balance is:" + paymentInfo.getActualBalance() );

        //TODO: Saves loan in loan table and repository
        makePayment.executePaymentWithLoan(loanValue, paymentInfo);
        setBalance(makePayment.getBalance());

        return this;
    }

    @Override
    public Double getBalance() {
        return this.balance;
    }

    private void setBalance(Double newBalance){
        this.balance = newBalance;
    }
}
