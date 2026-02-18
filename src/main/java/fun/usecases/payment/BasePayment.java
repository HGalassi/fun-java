package fun.usecases.payment;

import fun.ports.out.dynamodb.repository.payment.PaymentRepository;
import fun.usecases.payment.entity.PaymentInfo;
import fun.usecases.wallet.Debit;
import fun.usecases.wallet.Wallet;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import static fun.usecases.payment.PaymentCalculation.isNotEnoughBalance;
import static fun.usecases.payment.PaymentCalculation.subBill;

@RequestScope
@Component
public class BasePayment {
    private PaymentInfo paymentInfo;

    private final PaymentRepository repository;

    private List<MakePayment> makePayment;
    public BasePayment(PaymentRepository repository, List<MakePayment> makePayment){
        this.repository = repository;
        this.makePayment = makePayment;
    }
    public void doPayment(PaymentInfo paymentInfo){
        //TODO: Implementar switch case para o tipo de pagamento escolhido, e injetar o makePayment dinamicamente de acordo com o tipo escolhido
        //TODO: Alterar o qualifier para ser dinamico de acordo com o tipo de pagamento escolhido
        // get wallets by client
        Wallet wallet = new Debit(200.0);

        makePayment.forEach(f-> {
            checkIfEnoughBalance(paymentInfo, f.ge);
            f.executePayment(paymentInfo);

        });

        if(makePayment == null)
            makePayment = new PaymentByCreditCard();
        makePayment = offersLoanIfInsufficientBalance(paymentInfo, makePayment); //instanceOf OffersLoan

        System.out.println("Before executePayment" + paymentInfo + "Instance Of" + makePayment.getClass().getName());

        makePayment = new SavePaymentInMemory(makePayment);
        if(isNotEnoughBalance(paymentInfo))
            makePayment = makePayment.executePaymentWithLoan(subBill(paymentInfo),paymentInfo);
        else
            makePayment = makePayment.executePayment(paymentInfo);

        repository.sendRequest(paymentInfo, makePayment, wallet);
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

    public PaymentInfo getPayment(UUID paymentId){
        return repository.getPaymentRegistry(paymentId);
    }
}
