package galassi.fun.payment;

public class PaymentInfo {
    private Double actualBalance;
    private Double paymentValue;
    public PaymentInfo(Double actualBalance, Double paymentValue){
        this.actualBalance = actualBalance;
        this.paymentValue = paymentValue;
    }

    public Double getActualBalance() {
        return actualBalance;
    }

    public Double getPaymentValue() {
        return paymentValue;
    }

    public void setActualBalance(Double newBalance){
        this.actualBalance = newBalance;
    }
}
