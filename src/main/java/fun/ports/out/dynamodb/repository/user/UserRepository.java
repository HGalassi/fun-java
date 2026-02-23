package fun.ports.out.dynamodb.repository.user;

import fun.infrastructure.dynamodb.factory.DynamoFactory;
import fun.ports.out.dynamodb.repository.CRUDOperations;
import fun.usecases.WalletEnum;
import fun.usecases.user.entity.UserEntity;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class UserRepository implements CRUDOperations<UserEntity> {

    private DynamoDbClient dynamoDbClient;

    public UserRepository(DynamoFactory factory) {
        this.dynamoDbClient = factory.dynamoDbClient();
    }

    private DynamoDbTable<UserEntity> getTable() {
        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
        return enhancedClient.table("Payment", TableSchema.fromBean(UserEntity.class));
    }

    @Override
    public UserEntity save(UserEntity entity) {
        DynamoDbTable<UserEntity> table = getTable();
        entity.setId(entity.buildPk(UUID.randomUUID()));
        table.putItem(entity);
        return entity;
    }

    /**
     * Finds a UserEntity by its PK (entity.getId() must be set with the full PK, e.g. "#USER#<uuid>").
     */
    @Override
    public UserEntity find(UserEntity entity) {
        DynamoDbTable<UserEntity> table = getTable();

        QueryConditional queryConditional = QueryConditional.keyEqualTo(
                Key.builder()
                        .partitionValue(entity.getId())
                        .build()
        );

        List<UserEntity> results = table.query(
                QueryEnhancedRequest.builder()
                        .queryConditional(queryConditional)
                        .build()
        ).items().stream().collect(Collectors.toList());

        return results.isEmpty() ? null : results.get(0);
    }

    public void findByUserAndWallet(UserEntity user, WalletEnum walletEnum) {
    }
}
