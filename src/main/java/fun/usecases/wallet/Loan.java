package fun.usecases.wallet;

import fun.usecases.WalletEnum;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@Service
@DynamoDbBean
public class Loan implements Wallet{
    private Double balance;

    public Loan() {
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String getDatabaseEntity(Wallet payment) {
        return "#"+ WalletEnum.LOAN.getPaymentType() + "#";
    }
}
