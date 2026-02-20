package fun.usecases.user.entity;

import fun.ports.out.dynamodb.repository.dynamodbAdapter.InstantToStringConverter;
import fun.usecases.EntityType;
import fun.usecases.SingleTableEntity;
import fun.usecases.wallet.Wallet;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

import java.time.Instant;
import java.util.ArrayList;

import java.util.List;

@Component
@DynamoDbBean()
public class UserEntity implements SingleTableEntity {

    private String id;
    private String name;
    private String email;
    private List<Wallet> wallets;
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

    @DynamoDbSortKey
    public List<Wallet> getWallets() {
        return wallets;
    }
    public void setWallets(List<Wallet> wallets) {
        this.wallets = wallets == null ? null : new ArrayList<>(wallets);
    }

    @DynamoDbConvertedBy(InstantToStringConverter.class)
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

    public static class UserEntityBuilder {
        private String id;
        private String name;
        private String email;
        private List<Wallet> wallets;
        private Instant createdAt;

        public UserEntityBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public UserEntityBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public UserEntityBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserEntityBuilder withWallets(List<Wallet> wallets) {
            this.wallets = wallets;
            return this;
        }

        public UserEntityBuilder withCreatedAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public UserEntity build() {
            UserEntity userEntity = new UserEntity();
            userEntity.setId(id);
            userEntity.setName(name);
            userEntity.setEmail(email);
            userEntity.setWallets(wallets);
            userEntity.setCreatedAt(createdAt);
            return userEntity;
        }
    }
}
