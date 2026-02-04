package galassi.fun;

import galassi.fun.payment.MakePayment;
import galassi.fun.payment.PaymentByCreditCard;
import galassi.fun.payment.PaymentInfo;
import galassi.fun.payment.PaymentOffersLoan;

public class Main {
    public static void main(String[] args) {
        PaymentInfo paymentInfo = new PaymentInfo(200.00D, 350.00D);
        MakePayment makePayment = new PaymentByCreditCard(paymentInfo);
        makePayment = new PaymentOffersLoan(makePayment, true, paymentInfo);
        makePayment.executePayment();

    }
}