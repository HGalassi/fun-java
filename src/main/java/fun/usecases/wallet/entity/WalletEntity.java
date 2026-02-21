package fun.usecases.wallet.entity;

import fun.ports.out.dynamodb.repository.dynamodbAdapter.InstantToStringConverter;
import fun.ports.out.dynamodb.repository.dynamodbAdapter.WalletEnumToStringConverter;
import fun.usecases.EntityType;
import fun.usecases.SingleTableEntity;
import fun.usecases.WalletEnum;
import fun.usecases.wallet.Wallet;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

import java.time.Instant;
import java.util.UUID;

@Component
@DynamoDbBean()
public class WalletEntity implements SingleTableEntity {

    private String id;
    private Double balance;
    private WalletEnum type;
    private String userId;
    private Instant createdAt;
    private Wallet wallet;

    public WalletEntity() {
    }

    @DynamoDbPartitionKey()
    @DynamoDbAttribute("PK")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @DynamoDbConvertedBy(WalletEnumToStringConverter.class)
    @DynamoDbSortKey()
    @DynamoDbAttribute("SK")
    public WalletEnum getType() {
        return type;
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

    @DynamoDbConvertedBy(InstantToStringConverter.class)
    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    //TODO: Ajustar parsing do wallet para o banco de dados, talvez seja necessário criar um converter específico para isso
    @DynamoDbIgnore
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
