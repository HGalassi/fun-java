package fun.usecases.user.entity;

import fun.ports.out.dynamodb.repository.dynamodbAdapter.InstantToStringConverter;
import fun.usecases.EntityType;
import fun.usecases.SingleTableEntity;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbConvertedBy;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.time.Instant;
import java.util.ArrayList;
import java.util.UUID;

@Component
@DynamoDbBean()
public class UserEntity implements SingleTableEntity {

    private String id;
    private String name;
    private String email;
    private ArrayList<String> walletIds;
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

    public ArrayList<String> getWalletIds() {
        return walletIds;
    }

    public void setWalletIds(ArrayList<String> walletIds) {
        this.walletIds = walletIds;
    }

    @DynamoDbConvertedBy(InstantToStringConverter.class)
    public Instant  getCreatedAt() {
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
