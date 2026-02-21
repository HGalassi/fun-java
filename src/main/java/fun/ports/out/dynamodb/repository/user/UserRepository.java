package fun.ports.out.dynamodb.repository.user;

import fun.infrastructure.dynamodb.factory.DynamoFactory;
import fun.ports.out.dynamodb.repository.CRUDOperations;
import fun.usecases.WalletEnum;
import fun.usecases.user.entity.UserEntity;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.UUID;

@Repository
public class UserRepository implements CRUDOperations<UserEntity> {

private DynamoDbClient dynamoDbClient;

    public UserRepository(DynamoFactory factory){
        this.dynamoDbClient = factory.dynamoDbClient();
    }

    @Override
    public UserEntity save(UserEntity entity) {
        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();

        DynamoDbTable<UserEntity> table =
                enhancedClient.table("Payment", TableSchema.fromBean(UserEntity.class));
        entity.setId(entity.buildPk(UUID.randomUUID()));
        table.putItem(entity);
        return entity;
    }

    @Override
    public UserEntity find(UserEntity entity) {
        return null;
    }

    public void findByUserAndWallet(UserEntity user, WalletEnum walletEnum) {
    }
}
