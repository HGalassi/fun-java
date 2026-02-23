package fun.ports.in.httpcontroller.dto;

import fun.usecases.WalletEnum;
import fun.usecases.wallet.entity.WalletEntity;

import java.time.Instant;

public class WalletDTO {

    private String id;
    private WalletEnum type;
    private Double balance;
    private Instant createdAt;

    public WalletDTO() {
    }

    public static WalletDTO from(WalletEntity entity) {
        WalletDTO dto = new WalletDTO();
        dto.setId(entity.getId());
        dto.setType(entity.getType());
        dto.setBalance(entity.getBalance());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public WalletEnum getType() {
        return type;
    }

    public void setType(WalletEnum type) {
        this.type = type;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}

