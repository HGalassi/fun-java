package fun.usecases.wallet;

import fun.usecases.WalletEnum;

public interface Wallet {


    public String getId();

    public default Double getBalance(Wallet payment){
        return this.getInstance(payment).getBalance(payment);
    }

    public default Double setBalance(Wallet payment){
        return this.getInstance(payment).setBalance(payment);
    }

    public default Wallet getInstance(Wallet payment){
        return payment;
    }

    String getDatabaseEntity(Wallet payment);

    WalletEnum getType();

}
