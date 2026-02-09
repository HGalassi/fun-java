package fun.usecases.payment;


public class PaymentCalculation {

    public static final Double ZERO = 0.00;

    public static final Double sum(Double actualBalance, Double paymentValue){
                return Double.sum(actualBalance , paymentValue * returnSignal(actualBalance,paymentValue));
    }
    public static final Double sum(PaymentInfo paymentInfo){
        return Double.sum(paymentInfo.getActualBalance() , paymentInfo.getPaymentValue() * returnSignal(paymentInfo));
    }

    public static final Double subBill(PaymentInfo paymentInfo){
        System.out.println("balance: " + paymentInfo.getActualBalance() + "paymentValue: " + paymentInfo.getPaymentValue() + "in subBill");
        Double balance = paymentInfo.getActualBalance();
        Double paymentValue = paymentInfo.getPaymentValue();
        return balance - paymentValue;
    }

    public static final boolean isNotEnoughBalance(PaymentInfo paymentInfo){
        return !isGreatherThen(paymentInfo.getActualBalance(), paymentInfo.getPaymentValue());
    }

    public static int returnSignal(Double actualBalance, Double paymentValue) {
        return actualBalance < paymentValue? -1: 1;
    }

    public static int returnSignal(PaymentInfo paymentInfo) {
        return paymentInfo.getActualBalance() < paymentInfo.getPaymentValue()? -1: 1;
    }

    public static boolean isValuePositive(Double val){
        return val > 0.00;
    }

    public static boolean isValueNegative(Double val){
        return val < 0.00;
    }

    public static boolean isGreatherThen(Double val1, Double val2){
        return Double.compare(val1,val2) >= 0;
    }
}
