package fun.ports.out.dynamodb.repository;

import fun.infrastructure.dynamodb.factory.DynamoFactory;
import fun.usecases.payment.PaymentInfo;
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
import java.util.function.Consumer;

@Repository
public class PaymentRepository {

    private final DynamoDbClient dynamoDbClient;
    public PaymentRepository(DynamoFactory factory){
        this.dynamoDbClient = factory.dynamoDbClient();
    }

    public void sendRequest(PaymentInfo paymentInfo) {
        Logger logger = LoggerFactory.getLogger(PaymentRepository.class);

        logger.info("calling the DynamoDB API to get a list of existing tables");
        ListTablesResponse response = dynamoDbClient.listTables();

        if (!response.hasTableNames()) {
            logger.info("No existing tables found for the configured account & region");
        } else {
            response.tableNames().forEach(tableName -> logger.info("Table: " + tableName));
        }

        addPaymentRegistry(paymentInfo);
    }

    public void addPaymentRegistry(PaymentInfo paymentInfo){
        Map<String, AttributeValue> attrs = new HashMap<>();
        attrs.put("actualBalance", AttributeValue.builder().s(String.valueOf(paymentInfo.getActualBalance())).build());
        attrs.put("paymentValue", AttributeValue.builder().s(String.valueOf(paymentInfo.getPaymentValue())).build());
        attrs.put("isLoanToken", AttributeValue.builder().s(String.valueOf(paymentInfo.isLoanTaken())).build());
        attrs.put("id", AttributeValue.builder().s(String.valueOf(paymentInfo.getTransactionUuid()  )).build());
        PutItemRequest putItemRequest = PutItemRequest.builder().tableName("Payment").item(attrs).build();
        try{
            PutItemResponse response = dynamoDbClient.putItem(putItemRequest);
        }catch (Exception e){
            System.out.println("oops, there's an unexpected error" +  e);
        }
    }

    public PaymentInfo getPaymentRegistry(UUID uuid){
        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
        DynamoDbTable<PaymentInfo> paymentInfoDynamoDbTable = enhancedClient.table("Payment", TableSchema.fromClass(PaymentInfo.class));
        Key key = Key.builder()
                .partitionValue(uuid.toString())
                .build();

        return paymentInfoDynamoDbTable.getItem(key);
    }


}
