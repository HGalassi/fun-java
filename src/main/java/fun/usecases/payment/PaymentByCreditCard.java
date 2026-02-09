package fun.usecases.payment;

import org.springframework.stereotype.Service;

import static fun.usecases.payment.PaymentCalculation.subBill;
import static fun.usecases.payment.PaymentCalculation.sum;

@Service("paymentByCreditCard")
public class PaymentByCreditCard implements MakePayment{

    public PaymentByCreditCard(){
    }
    @Override
    public Double executePayment(PaymentInfo paymentInfo) {
        //TODO: Save new balance. subBill
        return subBill(paymentInfo);
    }

    public Double executePaymentWithLoan(Double loanValue, PaymentInfo paymentInfo){
        // TODO: increments card bill
        //TODO: setnew balance sum
        return subBill(paymentInfo);
    }
}
