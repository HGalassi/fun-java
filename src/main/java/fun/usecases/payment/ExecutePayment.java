package fun.usecases.payment;

import fun.usecases.payment.entity.PaymentInfo;

@FunctionalInterface
public interface ExecutePayment {
    void executePayment(PaymentInfo paymentInfo);
}
