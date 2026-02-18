package fun.usecases.payment;

import fun.usecases.payment.entity.PaymentInfo;
import fun.usecases.user.entity.UserEntity;

public interface MakePayment<T> {
    MakePayment executePayment(PaymentInfo paymentInfo);

    MakePayment executePaymentWithLoan(Double loanValue, PaymentInfo paymentInfo);

    Double getBalance();

    T getWallet(T payment, UserEntity user);
}
