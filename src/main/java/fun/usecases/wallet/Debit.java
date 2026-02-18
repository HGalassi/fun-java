package fun.usecases.wallet;

import fun.usecases.WalletEnum;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@DynamoDbBean
public class Debit implements Wallet{

    private Double balance;
    private Debit debit;

    public Debit(Debit debit) {
        this.debit = debit;
    }

    public Debit(Double balance) {
        this.balance = balance;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String getDatabaseEntity(Wallet payment) {
        return "#"+ WalletEnum.DEBIT_CARD.getPaymentType() + "#";
    }
}
