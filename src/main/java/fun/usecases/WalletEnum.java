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

    public static WalletEnum fromPaymentType(String value) {
        for (WalletEnum w : values()) {
            if (w.paymentType.equalsIgnoreCase(value)) {
                return w;
            }
        }
        throw new IllegalArgumentException("Unknown WalletEnum paymentType: " + value);
    }
}
