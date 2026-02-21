package fun.usecases.wallet;

import fun.usecases.WalletEnum;
import org.springframework.stereotype.Service;
 import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@Service
@DynamoDbBean
public class Credit implements Wallet {

    private Double balance;
    private String id;
    //TODO: Isso vai para uma classe abstrata.
    private String cardNumber;
    private String cardHolderName;
    private String expirationDate;
    private String cvv;

     public Credit(Double balance,String id) {
         this.balance = balance;
         this.id = id;
     }

    @Override
    public String getId() {
        return id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String getDatabaseEntity(Wallet payment) {
        return "#"+ WalletEnum.CREDIT_CARD.getPaymentType() + "#";
    }

    @Override
    public WalletEnum getType() {
        return WalletEnum.CREDIT_CARD;
    }
}
