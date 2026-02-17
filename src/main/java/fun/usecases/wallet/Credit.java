package fun.usecases.wallet;

import fun.usecases.WalletEnum;
import org.springframework.stereotype.Service;
 import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@Service
@DynamoDbBean
public class Credit implements Wallet {

    private Double balance;

     public Credit(){
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
}
