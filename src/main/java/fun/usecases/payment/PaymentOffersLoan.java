package fun.usecases.payment;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service("paymentOffersLoan")
public class PaymentOffersLoan implements MakePayment{

    private  MakePayment makePayment;
    private PaymentInfo paymentInfo;

    private Double paymentValue = null;
    private Double actualBalance = null;
    public PaymentOffersLoan(MakePayment makePayment, Double paymentValue, Double actualBalance){
        this.makePayment = makePayment;
        this.paymentValue = paymentValue;
        this.actualBalance = actualBalance;
    }

    public PaymentOffersLoan(){

    }

    public PaymentOffersLoan(MakePayment makePayment, PaymentInfo paymentInfo) {
        this.makePayment = makePayment;
        this.paymentInfo = paymentInfo;
    }

    @Override
    public Double executePayment() {
        Double loanValue = null;
        if (paymentInfo.getPaymentValue() > paymentInfo.getActualBalance()){
            System.out.println("Do you want to take out a loan to pay this bill? ");

            if (isLoanAccepted()){
                loanValue = paymentInfo.getPaymentValue() - paymentInfo.getActualBalance();
                System.out.println("Loan accepted with value: "+ loanValue);
                return this.executePaymentWithLoan(loanValue);
            }
        }
        System.out.println("Loan not taken, balance will be negative: ");
        return paymentInfo.getActualBalance() - paymentInfo.getPaymentValue();

    }

    private boolean isLoanAccepted() {
        Random random = new Random();
        return random.nextBoolean();
    }

    //TODO: Ajustar quando empréstimo não é aceito valor não reflete negativo.
    @Override
    public Double executePaymentWithLoan(Double loanValue) {
        if (isLoanAccepted()){
            Double result = makePayment.executePaymentWithLoan(loanValue);
            System.out.println("Payment finished with loan: "+ loanValue + " and actual balance is: " + result );
            return result;
        }

        if (paymentInfo.getActualBalance() > paymentInfo.getPaymentValue()){
            Double result = makePayment.executePayment();
            System.out.println("Payment finished without loan and actual balance is:" + result );
            return result;
        }

        System.out.println("Payment finished without loan and insufficient balance. Actual balance is:"  + (paymentInfo.getActualBalance() - paymentInfo.getPaymentValue()));
        return paymentInfo.getActualBalance();
    }
}
