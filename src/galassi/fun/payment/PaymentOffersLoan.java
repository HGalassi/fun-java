package galassi.fun.payment;

public class PaymentOffersLoan implements MakePayment{

    private  MakePayment makePayment;
    private boolean loanAccepted;

    private PaymentInfo paymentInfo;

    private Double paymentValue = null;
    private Double actualBalance = null;
    public PaymentOffersLoan(MakePayment makePayment, boolean loanAccepted, Double paymentValue, Double actualBalance){
        this.makePayment = makePayment;
        this.loanAccepted = loanAccepted;
        this.paymentValue = paymentValue;
        this.actualBalance = actualBalance;
    }

    public PaymentOffersLoan(MakePayment makePayment, boolean loanAccepted, PaymentInfo paymentInfo) {
        this.makePayment = makePayment;
        this.loanAccepted = loanAccepted;
        this.paymentInfo = paymentInfo;
    }

    @Override
    public Double executePayment() {
        Double loanValue = null;
        if (paymentInfo.getPaymentValue() > paymentInfo.getActualBalance()){
            System.out.println("Do you want to take out a loan to pay this bill? ");

            if (loanAccepted){
                loanValue = paymentInfo.getPaymentValue() - paymentInfo.getActualBalance();
                System.out.println("Loan accepted with value: "+ loanValue);
            }
        }

        return this.executePaymentWithLoan(loanValue);

    }

    @Override
    public Double executePaymentWithLoan(Double loanValue) {
        if (loanAccepted){
            Double result = makePayment.executePaymentWithLoan(loanValue);
            System.out.println("Payment finished with loan: "+ loanValue + " and actual balance is: " + result );
            return result;
        }

        if (paymentInfo.getActualBalance() > paymentInfo.getPaymentValue()){
            Double result = makePayment.executePayment();
            System.out.println("Payment finished without loan and actual balance is:" + result );
            return result;
        }

        System.out.println("Payment finished without loan and insufficient balance. Actual balance is:" + paymentInfo.getActualBalance() );
        return paymentInfo.getActualBalance();
    }
}
