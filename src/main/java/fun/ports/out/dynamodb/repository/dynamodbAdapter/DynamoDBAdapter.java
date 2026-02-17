package fun.ports.out.dynamodb.repository.dynamodbAdapter;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.util.Map;

public class DynamoDBAdapter {

    private static String convertToString(Object value) {
        return String.valueOf(value);
    }

    public static AttributeValue returnAttributeValue(Object value) {
        return AttributeValue.builder().s(convertToString(value)).build();
    }

    public static AttributeValue returnNumericAttributeValue(Object value) {
        return AttributeValue.builder().n(convertToString(value)).build();
    }


    public static PutItemRequest getPutItemRequest(Map<String, AttributeValue> attrs) {
        PutItemRequest putItemRequest = PutItemRequest.builder().tableName("Payment").item(attrs).build();
        return putItemRequest;
    }
}

