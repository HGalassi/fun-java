package fun.usecases.wallet;

import fun.usecases.WalletEnum;
import org.springframework.stereotype.Service;

@Service
public class Loan implements Wallet{
    @Override
    public String getDatabaseEntity(Wallet payment) {
        return "#"+ WalletEnum.LOAN.getPaymentType() + "#";
    }
}
