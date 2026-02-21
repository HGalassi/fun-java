package fun.usecases.payment;

import fun.ports.out.dynamodb.repository.payment.PaymentRepository;
import fun.ports.out.dynamodb.repository.user.UserRepository;
import fun.usecases.payment.entity.PaymentInfo;
import fun.usecases.user.entity.UserEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;
import java.util.UUID;

@RequestScope
@Component
public class BasePayment {
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;

    private final List<ExecutePayment> executePayment;
    public BasePayment(PaymentRepository paymentRepository, UserRepository userRepository, List<ExecutePayment> makePayment){
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
        this.executePayment = makePayment;
    }
    public void doPayment(PaymentInfo paymentInfo){
        UserEntity user = new UserEntity.UserEntityBuilder().withId(paymentInfo.getUser()).build();
        userRepository.find(user);
        executePayment.forEach(ep -> ep.executePayment(paymentInfo));
    }

    public PaymentInfo getPayment(UUID paymentId){
        return paymentRepository.getPaymentRegistry(paymentId);
    }

    public void deleteAllPayments(){
        paymentRepository.deleteAll();
    }
}
