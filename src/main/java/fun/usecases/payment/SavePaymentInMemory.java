package fun.usecases.payment;

import org.springframework.stereotype.Service;

import java.util.*;

@Service("savePaymentInMemory")
public class SavePaymentInMemory implements MakePayment{

    private MakePayment makePayment;
    private Double newBalance;
    public SavePaymentInMemory(MakePayment makePayment){
        this.makePayment = makePayment;
        this.newBalance = makePayment.getBalance();
    }

    public SavePaymentInMemory(){

    }
    @Override
    public MakePayment executePayment(PaymentInfo paymentInfo) {
        this.makePayment.executePayment(paymentInfo);
        HashMap<UUID,Double> hashMap = new HashMap();

        hashMap.forEach((k,v)-> {
            System.out.println("payment id: " + paymentInfo.getId() + "\n");
            System.out.println("actual balance: " + newBalance);
        } );


        return this;
    }

    @Override
    public MakePayment executePaymentWithLoan(Double loanValue, PaymentInfo paymentInfo) {
        makePayment.executePaymentWithLoan(loanValue,paymentInfo);
        System.out.println("end of stack balance" +  makePayment.getBalance());
        //TODO: Do things in memory for executed withLoan

        return this;
    }

    @Override
    public Double getBalance() {
        return null;
    }


}

