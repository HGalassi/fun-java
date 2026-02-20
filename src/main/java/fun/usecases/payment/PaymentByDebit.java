package fun.usecases.payment;

import fun.ports.out.dynamodb.repository.user.UserRepository;
import fun.usecases.WalletEnum;
import fun.usecases.payment.entity.PaymentInfo;
import fun.usecases.user.entity.UserEntity;
import fun.usecases.wallet.Debit;
import org.springframework.core.annotation.Order;

@Order(1)
public class PaymentByDebit implements ExecutePayment, WalletOperations<PaymentByDebit> {

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
    public void executePayment(PaymentInfo paymentInfo) {
        //TODO: Refatorar tudo isso
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
