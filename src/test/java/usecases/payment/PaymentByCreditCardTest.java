package usecases.payment;

import fun.usecases.payment.MakePayment;
import fun.usecases.payment.PaymentByCreditCard;
import fun.usecases.payment.PaymentInfo;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class PaymentByCreditCardTest {

    @Test
    public void executePaymentShouldReturnValidBalance() {
        PaymentInfo paymentInfo = new PaymentInfo(10.00, 5.00);
        MakePayment makePayment = new PaymentByCreditCard();
        makePayment = makePayment.executePayment(paymentInfo);
        Assert.isTrue(
                Double.compare(makePayment.getBalance(), 5.00) == 0,
                "O saldo deveria ser de %.2f: ".formatted(makePayment.getBalance())
        );
    }

    @Test
    public void executePaymentShouldReturnValidInstance() {
        PaymentInfo paymentInfo = new PaymentInfo(10.00, 5.00);
        MakePayment makePayment = new PaymentByCreditCard();
        makePayment = makePayment.executePayment(paymentInfo);
        Assert.isTrue(
                makePayment instanceof PaymentByCreditCard,
                "A instancia deveria ser: ".formatted(PaymentByCreditCard.class.getSimpleName())
        );
    }

}
