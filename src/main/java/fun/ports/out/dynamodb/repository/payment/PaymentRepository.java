package fun.ports.out.dynamodb.repository.payment;

import fun.infrastructure.dynamodb.factory.DynamoFactory;
import fun.usecases.payment.ExecutePayment;
import fun.usecases.payment.entity.PaymentInfo;
import fun.usecases.wallet.Wallet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static fun.ports.out.dynamodb.repository.dynamodbAdapter.DynamoDBAdapter.getPutItemRequest;
import static fun.ports.out.dynamodb.repository.dynamodbAdapter.DynamoDBAdapter.returnAttributeValue;

@Repository
public class PaymentRepository {

    private final DynamoDbClient dynamoDbClient;
    public PaymentRepository(DynamoFactory factory){
        this.dynamoDbClient = factory.dynamoDbClient();
    }

    public void sendRequest(PaymentInfo paymentInfo, ExecutePayment makePayment, Wallet wallet) {
        Logger logger = LoggerFactory.getLogger(PaymentRepository.class);

        logger.info("calling the DynamoDB API to get a list of existing tables");
        ListTablesResponse response = dynamoDbClient.listTables();

        if (!response.hasTableNames()) {
            logger.info("No existing tables found for the configured account & region");
        } else {
            response.tableNames().forEach(tableName -> logger.info("Table: " + tableName));
        }
        addPaymentRegistry(paymentInfo, makePayment, wallet);
    }

    public PutItemResponse addPaymentRegistry(PaymentInfo paymentInfo, ExecutePayment makePayment, Wallet wallet ){
        Map<String, AttributeValue> attrs = new HashMap<>();
        attrs.put("actualBalance", returnAttributeValue(paymentInfo.getActualBalance()));
        attrs.put("paymentValue", returnAttributeValue(paymentInfo.getPaymentValue()));
        attrs.put("isLoanToken", returnAttributeValue(paymentInfo.isLoanTaken()));
        attrs.put("id", returnAttributeValue(wallet.getDatabaseEntity(wallet)));
        PutItemRequest putItemRequest = getPutItemRequest(attrs);
        try{
            System.out.println(putItemRequest.item().get("id").s());
            return dynamoDbClient.putItem(putItemRequest);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error adding payment registry to DynamoDB", e);
        }
    }



    public PaymentInfo getPaymentRegistry(UUID uuid){

        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();

        DynamoDbTable<PaymentInfo> table =
                enhancedClient.table("Payment", TableSchema.fromBean(PaymentInfo.class));

        Key key = Key.builder()
                .partitionValue(uuid.toString())
                .build();

        return table.getItem(key);
    }

    public void deleteAll() {
        ScanRequest scanRequest = ScanRequest.builder()
                .tableName("Payment")
                .build();

        ScanResponse scanResponse = dynamoDbClient.scan(scanRequest);

        for (Map<String, AttributeValue> item : scanResponse.items()) {
            DeleteItemRequest deleteRequest = DeleteItemRequest.builder()
                    .tableName("Payment")
                    .key(Map.of(
                            "PK", item.get("PK"),
                            "SK", item.get("SK")
                    ))
                    .build();
            dynamoDbClient.deleteItem(deleteRequest);
        }
    }




}
