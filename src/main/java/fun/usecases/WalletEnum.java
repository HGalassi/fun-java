package fun.usecases;

public enum WalletEnum {
        CREDIT_CARD("credit"),
        DEBIT_CARD("debit"),
        LIMIT("limit"),
        LOAN("loan");

    private String paymentType;

    WalletEnum(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public String getPaymentTypeForDatabase() {
        return paymentType;
    }
}
