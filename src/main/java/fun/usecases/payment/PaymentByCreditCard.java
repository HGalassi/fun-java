package fun.usecases.payment;

import fun.usecases.payment.entity.PaymentInfo;
import fun.usecases.wallet.Credit;
import org.springframework.stereotype.Service;

import static fun.usecases.payment.PaymentCalculation.subBill;
import static fun.usecases.payment.PaymentCalculation.sum;

@Service("paymentByCreditCard")
public class PaymentByCreditCard implements MakePayment{

    private Double balance;
    private Credit credit;

    public PaymentByCreditCard(){
    }

    public PaymentByCreditCard(Credit credit){
        this.credit=credit;
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
