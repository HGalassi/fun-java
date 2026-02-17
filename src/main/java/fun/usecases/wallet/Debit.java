package fun.usecases.wallet;

import fun.usecases.WalletEnum;
import org.springframework.stereotype.Service;

@Service
public class Debit implements Wallet{
    @Override
    public String getDatabaseEntity(Wallet payment) {
        return "#"+ WalletEnum.DEBIT_CARD.getPaymentType() + "#";
    }
}
