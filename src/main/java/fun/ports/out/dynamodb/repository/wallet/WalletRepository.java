package fun.ports.out.dynamodb.repository.wallet;

import fun.infrastructure.dynamodb.factory.DynamoFactory;
import fun.ports.out.dynamodb.repository.CRUDOperations;
import fun.usecases.wallet.entity.WalletEntity;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbIndex;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Repository
public class WalletRepository implements CRUDOperations<WalletEntity> {

    private static final Logger logger = Logger.getLogger(WalletRepository.class.getName());
    private DynamoDbClient dynamoDbClient;

    public WalletRepository(DynamoFactory factory) {
        this.dynamoDbClient = factory.dynamoDbClient();
    }

    private DynamoDbTable<WalletEntity> getTable() {
        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
        return enhancedClient.table("Payment", TableSchema.fromBean(WalletEntity.class));
    }

    @Override
    public WalletEntity save(WalletEntity entity) {
        logger.info("[WalletRepository] save -> PK=" + entity.getId()
                + ", type=" + entity.getType()
                + ", userId=" + entity.getUserId()
                + ", wallet=" + (entity.getWallet() != null
                    ? entity.getWallet().getClass().getSimpleName() + "#" + entity.getWallet().getId()
                    : "NULL ← PROBLEMA AQUI!")
                + ", balance=" + entity.getBalance());

        DynamoDbTable<WalletEntity> table = getTable();
        entity.setId(entity.buildPk(UUID.randomUUID()));

        logger.info("[WalletRepository] PK final gerado: " + entity.getId());
        try {
            table.putItem(entity);
            logger.info("[WalletRepository] putItem executado com sucesso para PK=" + entity.getId());
        } catch (Exception e) {
            logger.severe("[WalletRepository] ERRO ao executar putItem: " + e.getMessage());
            throw e;
        }
        return entity;
    }

    /**
     * Finds a WalletEntity by its PK (entity.getId() must be the full PK, e.g. "#WALLET#<uuid>").
     */
    @Override
    public WalletEntity find(WalletEntity entity) {
        DynamoDbTable<WalletEntity> table = getTable();

        QueryConditional queryConditional = QueryConditional.keyEqualTo(
                Key.builder().partitionValue(entity.getId()).build()
        );

        List<WalletEntity> results = table.query(
                QueryEnhancedRequest.builder()
                        .queryConditional(queryConditional)
                        .build()
        ).items().stream().collect(Collectors.toList());

        return results.isEmpty() ? null : results.get(0);
    }

    /**
     * Finds all wallets belonging to a given user PK (e.g. "#USER#<uuid>")
     * by querying the GSI 'userId-index' instead of doing a full table scan.
     */
    public List<WalletEntity> findByUserId(String userPk) {
        DynamoDbTable<WalletEntity> table = getTable();
        DynamoDbIndex<WalletEntity> index = table.index("userId-index");

        QueryConditional queryConditional = QueryConditional.keyEqualTo(
                Key.builder().partitionValue(userPk).build()
        );

        return index.query(
                QueryEnhancedRequest.builder()
                        .queryConditional(queryConditional)
                        .build()
        ).stream()
                .flatMap(page -> page.items().stream())
                .collect(Collectors.toList());
    }
}
