package fun.usecases.wallet.entity;

import fun.usecases.EntityType;
import fun.usecases.SingleTableEntity;
import fun.usecases.WalletEnum;
import fun.usecases.wallet.Wallet;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import java.util.Date;
import java.util.UUID;

@Component
@DynamoDbBean()
public class WalletEntity implements SingleTableEntity {

    private String id;
    private Double balance;
    private WalletEnum type;
    private String userId;
    private Date createdAt;
    private Wallet wallet;

    public WalletEntity() {
    }

    @DynamoDbPartitionKey()
    @DynamoDbAttribute("id")
    public String getId() {
        return id;
    }

    @DynamoDbAttribute("id")
    public void setId(String id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public WalletEnum getType() {
        return type;
    }

    public String getStringType() {
        return type.toString();
    }

    public void setType(WalletEnum type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    @Override
    public EntityType entityType() {
        return EntityType.WALLET;
    }

    @Override
    public String buildPk(UUID id) {
        return SingleTableEntity.super.buildPk(id);
    }
}
