package fun.usecases.user.entity;

import fun.ports.out.dynamodb.repository.dynamodbAdapter.InstantToStringConverter;
import fun.usecases.EntityType;
import fun.usecases.SingleTableEntity;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@DynamoDbBean()
public class UserEntity implements SingleTableEntity {

    private String id;
    private String name;
    private String email;
    private List walletIds;
    private Instant createdAt;

    public UserEntity() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getWalletIds() {
        return walletIds;
    } //TODO: replicar a wallet.

    public void setWalletIds(List<String> walletIds) {
        this.walletIds = walletIds == null ? null : new ArrayList<>(walletIds);
    }

    @DynamoDbConvertedBy(InstantToStringConverter.class)
    @DynamoDbSortKey
    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant  createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public EntityType entityType() {
        return EntityType.USER;
    }
}
