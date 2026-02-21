package fun.usecases.wallet;

import fun.usecases.WalletEnum;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@Service
@DynamoDbBean
public class Debit implements Wallet{

    private Double balance;
    private Debit debit;
    private final String id;
    private String type;

    public Debit(Double debit, String id) {
        this.balance = debit;
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getDatabaseEntity(Wallet payment) {
        return "#"+ WalletEnum.DEBIT_CARD.getPaymentType() + "#";
    }

    @Override
    public WalletEnum getType() {
        return WalletEnum.DEBIT_CARD;
    }
}
