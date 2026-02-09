package fun.usecases.payment;

import fun.ports.out.dynamodb.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

import static fun.usecases.payment.PaymentCalculation.isNotEnoughBalance;

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
        if(isNotEnoughBalance(paymentInfo))
            makePayment = offersLoanIfInsufficientBalance(paymentInfo, makePayment); //instanceOf OffersLoan
        makePayment = new SavePaymentInMemory(makePayment);
        System.out.println("Before executePayment" + paymentInfo + "Instance Of" + makePayment.getClass().getName());
        makePayment.executePayment(paymentInfo);
        System.out.println("After executePayment" + paymentInfo);
        repository.sendRequest(paymentInfo);
    }
    private MakePayment offersLoanIfInsufficientBalance(PaymentInfo paymentInfo, MakePayment makePayment) {
          System.out.println("Do you want to take out a loan to pay this bill? ");
          paymentInfo.setLoanTaken(isLoanAccepted());
          makePayment = new PaymentOffersLoan(makePayment,paymentInfo);
          //TODO: save new balance
          return makePayment; // instance of OffersLoan
    }

    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }

    private boolean isLoanAccepted() {
        Random random = new Random();
        return random.nextInt(0, 10) /2 == 0;
    }
}
