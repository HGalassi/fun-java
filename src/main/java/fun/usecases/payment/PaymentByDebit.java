package fun.usecases.payment;

import fun.ports.out.dynamodb.repository.user.UserRepository;
import fun.usecases.WalletEnum;
import fun.usecases.payment.entity.PaymentInfo;
import fun.usecases.user.entity.UserEntity;
import fun.usecases.wallet.Debit;

public class PaymentByDebit implements MakePayment<PaymentByDebit>{

    private PaymentInfo paymentInfo;
    private Debit debit;
    private UserEntity user;
    private UserRepository userRepository;

     public PaymentByDebit(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public PaymentByDebit() {
    }

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

    @Override
    public PaymentByDebit getWallet(PaymentByDebit payment, UserEntity user) {
        this.userRepository.findByUserAndWallet(user, WalletEnum.DEBIT_CARD);
        return this;
    }

}
