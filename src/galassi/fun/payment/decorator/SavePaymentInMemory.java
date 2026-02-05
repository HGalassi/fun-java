package galassi.fun.payment.decorator;

import java.util.*;

public class SavePaymentInMemory implements MakePayment{

    private final MakePayment makePayment;
    public SavePaymentInMemory(MakePayment makePayment){
        this.makePayment = makePayment;
    }
    @Override
    public Double executePayment() {
        Double paymentValue = this.makePayment.executePayment();
        HashMap<UUID,Double> hashMap = new HashMap();
        if (Objects.nonNull(paymentValue)){
            hashMap.put(UUID.randomUUID(), paymentValue);
        }
        hashMap.forEach((k,v)-> {
            System.out.println("payment id: " + k + "\n");
            System.out.println("actual balance: " + v);
        } );


        return paymentValue;
    }

    @Override
    public Double executePaymentWithLoan(Double loanValue) {
        // not implemented yet;
        return null;
    }
}
