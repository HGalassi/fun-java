package fun.usecases.payment;

import fun.usecases.user.entity.UserEntity;

public interface WalletOperations<T> {

    Double getBalance();

    T getWallet(T payment, UserEntity user);
}
