package fun.usecases.payment;

import fun.ports.out.dynamodb.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BasePayment {
    private PaymentInfo paymentInfo;

    private final PaymentRepository repository;

    private MakePayment makePayment;
    public BasePayment(PaymentRepository repository, @Qualifier("paymentByCreditCard") MakePayment makePayment){
        this.repository = repository;
        this.makePayment = makePayment;
    }
    public void doPayment(PaymentInfo paymentInfo){
        paymentInfo.setTransactionUuid(UUID.randomUUID());
        makePayment = new PaymentByCreditCard(paymentInfo);
        if(paymentInfo.getPaymentValue() > paymentInfo.getActualBalance()) {
            makePayment = new PaymentOffersLoan(makePayment, paymentInfo);
            Double actualBalance = makePayment.executePayment();
            if( actualBalance > 0)
                paymentInfo.setLoanTaken(true);
        }
        makePayment = new SavePaymentInMemory(makePayment);
        makePayment.executePayment();
        repository.sendRequest(paymentInfo);
    }

}
