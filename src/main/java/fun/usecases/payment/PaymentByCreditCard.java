package fun.usecases.payment;

import org.springframework.stereotype.Service;

import static fun.usecases.payment.PaymentCalculation.subBill;
import static fun.usecases.payment.PaymentCalculation.sum;

@Service("paymentByCreditCard")
public class PaymentByCreditCard implements MakePayment{

    private Double balance;

    public PaymentByCreditCard(){
    }
    @Override
    public MakePayment executePayment(PaymentInfo paymentInfo) {
        //TODO: Save new balance. subBill
        balance = subBill(paymentInfo);
        return this;
    }

    public MakePayment executePaymentWithLoan(Double loanValue, PaymentInfo paymentInfo){
        // TODO: increments card bill
        //TODO: setnew balance sum
        setBalance(subBill(paymentInfo) - 10.00);
        System.out.println("paymentcreditcardbalance"+ getBalance());
        return this;
    }

    @Override
    public Double getBalance() {
        return this.balance;
    }

    private void setBalance(Double balance){
        this.balance= balance;
    }
}
