package fun.ports.out.dynamodb.repository.dynamodbAdapter;

import fun.usecases.WalletEnum;
import software.amazon.awssdk.enhanced.dynamodb.AttributeConverter;
import software.amazon.awssdk.enhanced.dynamodb.AttributeValueType;
import software.amazon.awssdk.enhanced.dynamodb.EnhancedType;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.time.Instant;

public class WalletEnumToStringConverter implements AttributeConverter<WalletEnum> {

    @Override
    public AttributeValue transformFrom(WalletEnum input) {
        return AttributeValue.builder()
                .s(input.getPaymentType())
                .build();
    }

    @Override
    public WalletEnum transformTo(AttributeValue attributeValue) {
        return WalletEnum.valueOf(attributeValue.s());
    }

    @Override
    public EnhancedType<WalletEnum> type() {
        return EnhancedType.of(WalletEnum.class);
    }

    @Override
    public AttributeValueType attributeValueType() {
        return AttributeValueType.S;
    }
}
