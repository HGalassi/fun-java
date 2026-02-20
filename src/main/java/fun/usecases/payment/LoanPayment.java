package fun.usecases.payment;

import fun.usecases.payment.entity.PaymentInfo;
import fun.usecases.user.entity.UserEntity;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Random;

@Order(3)
@Service("paymentOffersLoan")
public class LoanPayment implements ExecutePayment, WalletOperations<LoanPayment> {

    private ExecutePayment makePayment;
    private PaymentInfo paymentInfo;

    public Double balance;

    public LoanPayment() {
    }

    public LoanPayment(ExecutePayment makePayment, PaymentInfo paymentInfo) {
        this.makePayment = makePayment;
        this.paymentInfo = paymentInfo;
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
    public LoanPayment getWallet(LoanPayment payment, UserEntity user) {
        return null;
    }

    private void setBalance(Double newBalance){
        this.balance = newBalance;
    }

    private ExecutePayment offersLoanIfInsufficientBalance(PaymentInfo paymentInfo, ExecutePayment makePayment) {
        System.out.println("Do you want to take out a loan to pay this bill? ");
        paymentInfo.setLoanTaken(isLoanAccepted());
        makePayment = new LoanPayment(makePayment,paymentInfo);
        //TODO: save new balance
        return makePayment; // instance of OffersLoan
    }

    private boolean isLoanAccepted() {
        Random random = new Random();
        return random.nextInt(0, 10) /2 == 0;
    }
}
