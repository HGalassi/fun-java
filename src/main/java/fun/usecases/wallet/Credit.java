package fun.usecases.wallet;

import fun.usecases.WalletEnum;
import org.springframework.stereotype.Service;

@Service
public class Credit implements Wallet {

    private Double balance;

     public Credit(){
     }

    @Override
    public String getDatabaseEntity(Wallet payment) {
        return "#"+ WalletEnum.CREDIT_CARD.getPaymentType() + "#";
    }
}
