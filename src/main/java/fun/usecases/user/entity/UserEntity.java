package fun.usecases.user.entity;

import fun.ports.out.dynamodb.repository.dynamodbAdapter.InstantToStringConverter;
import fun.usecases.EntityType;
import fun.usecases.SingleTableEntity;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

import java.time.Instant;

@DynamoDbBean()
public class UserEntity implements SingleTableEntity {

    private String id;
    private String name;
    private String email;
    private Instant createdAt;
    private String walletId;

    public UserEntity() {
    }

    @DynamoDbPartitionKey()
    @DynamoDbAttribute("PK")
    public String getId() {
        return id;
    }

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

    @DynamoDbSortKey()
    @DynamoDbAttribute("SK")
    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public static class UserEntityBuilder {
        private String id;
        private String name;
        private String email;
        private Instant createdAt;
        private String walletId;

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

        public UserEntityBuilder withCreatedAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public UserEntityBuilder withWalletIds(String walletId) {
            this.walletId = walletId;
            return this;
        }

        public UserEntity build() {
            UserEntity userEntity = new UserEntity();
            userEntity.setId(id);
            userEntity.setName(name);
            userEntity.setEmail(email);
            userEntity.setCreatedAt(createdAt);
            userEntity.setWalletId(walletId);
            return userEntity;
        }
    }
}
