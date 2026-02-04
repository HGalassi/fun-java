package galassi.fun.payment;

public class PaymentByCreditCard implements MakePayment{

    Double paymentValue = null;
    Double actualBalance = null;

    PaymentInfo paymentInfo = null;
    public PaymentByCreditCard(Double paymentValue, Double actualBalance){
        this.paymentValue = paymentValue;
        this.actualBalance = actualBalance;
    }

    public PaymentByCreditCard(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    @Override
    public Double executePayment() {
        Double result = null;
        if (paymentInfo.getActualBalance() >= paymentInfo.getPaymentValue()){
            result = paymentInfo.getActualBalance() - paymentInfo.getPaymentValue();
            System.out.println("Payment executed with success final balance is: " + result);
        }
        return result;
    }

    public Double executePaymentWithLoan(Double loanValue){
        paymentInfo.setActualBalance(this.paymentInfo.getActualBalance() + loanValue);
        return this.executePayment();
    }
}
