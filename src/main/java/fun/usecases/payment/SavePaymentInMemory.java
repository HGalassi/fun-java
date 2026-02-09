package fun.usecases.payment;

import org.springframework.stereotype.Service;

import java.util.*;

@Service("savePaymentInMemory")
public class SavePaymentInMemory implements MakePayment{

    private MakePayment makePayment;
    public SavePaymentInMemory(MakePayment makePayment){
        this.makePayment = makePayment;
    }

    public SavePaymentInMemory(){

    }
    @Override
    public Double executePayment(PaymentInfo paymentInfo) {
        System.out.println("MakePayment inside savepaymentinmemory instance of " + makePayment.getClass().getName());
        Double finalBalance = this.makePayment.executePayment(paymentInfo);
        HashMap<UUID,Double> hashMap = new HashMap();
        if (Objects.nonNull(finalBalance)){
            hashMap.put(UUID.randomUUID(), finalBalance);
        }
        hashMap.forEach((k,v)-> {
            System.out.println("payment id: " + k + "\n");
            System.out.println("actual balance: " + v);
        } );


        return finalBalance;
    }

    @Override
    public Double executePaymentWithLoan(Double loanValue,PaymentInfo paymentInfo) {
        makePayment.executePaymentWithLoan(loanValue,paymentInfo);
        //TODO: Do things in memory for executed withLoan
        return null;
    }


}

