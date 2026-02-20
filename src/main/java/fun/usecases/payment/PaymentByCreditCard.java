package fun.usecases.payment;

import fun.usecases.payment.entity.PaymentInfo;
import fun.usecases.user.entity.UserEntity;
import fun.usecases.wallet.Credit;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import static fun.usecases.payment.PaymentCalculation.subBill;
import static fun.usecases.payment.PaymentCalculation.sum;

@Order(2)
@Service("paymentByCreditCard")
public class PaymentByCreditCard implements ExecutePayment, WalletOperations<PaymentByCreditCard> {

    private Double balance;
    private Credit credit;

    public PaymentByCreditCard(){
    }

    public PaymentByCreditCard(Credit credit){
        this.credit=credit;
    }
    @Override
    public void executePayment(PaymentInfo paymentInfo) {
        //TODO: Refatorar tudo isso
    }


    @Override
    public Double getBalance() {
        return this.balance;
    }

    @Override
    public PaymentByCreditCard getWallet(PaymentByCreditCard payment, UserEntity user) {
        return null;
    }

    private void setBalance(Double balance){
        this.balance= balance;
    }
}
