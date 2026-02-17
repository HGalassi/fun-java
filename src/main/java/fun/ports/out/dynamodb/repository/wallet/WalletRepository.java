package fun.ports.out.dynamodb.repository.wallet;

import fun.infrastructure.dynamodb.factory.DynamoFactory;
import fun.ports.out.dynamodb.repository.CRUDOperations;
import fun.usecases.user.entity.UserEntity;
import fun.usecases.wallet.entity.WalletEntity;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.UUID;

@Repository
public class WalletRepository implements CRUDOperations<WalletEntity> {

    private DynamoDbClient dynamoDbClient;


    public WalletRepository(DynamoFactory factory) {
        this.dynamoDbClient = factory.dynamoDbClient();
    }

    @Override
    public WalletEntity save(WalletEntity entity) {
        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();

        DynamoDbTable<WalletEntity> table =
                enhancedClient.table("Payment", TableSchema.fromBean(WalletEntity.class));
        entity.setId(entity.buildPk(UUID.randomUUID()));

        table.putItem(entity);
        return entity;
    }

    @Override
    public WalletEntity find(WalletEntity entity) {
        return null;
    }
}
