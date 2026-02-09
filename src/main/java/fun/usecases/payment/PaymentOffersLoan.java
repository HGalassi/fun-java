package fun.usecases.payment;

import org.springframework.stereotype.Service;

@Service("paymentOffersLoan")
public class PaymentOffersLoan implements MakePayment {

    private MakePayment makePayment;
    private PaymentInfo paymentInfo;

    public PaymentOffersLoan() {
    }

    public PaymentOffersLoan(MakePayment makePayment, PaymentInfo paymentInfo) {
        this.makePayment = makePayment;
        this.paymentInfo = paymentInfo;
    }

    @Override
    public Double executePayment(PaymentInfo paymentInfo) {
        Double loanValue;
        //TODO: doThings
        return 0.00;
    }

    //TODO: Ajustar quando empréstimo não é aceito valor não reflete negativo.
    @Override
    public Double executePaymentWithLoan(Double loanValue,PaymentInfo paymentInfo) {
            //TODO: Find Fees
            System.out.println("Payment finished without loan and actual balance is:" + paymentInfo.getActualBalance() );
            //TODO: Saves loan in loan table and repository
            return 0.00;
    }
}
