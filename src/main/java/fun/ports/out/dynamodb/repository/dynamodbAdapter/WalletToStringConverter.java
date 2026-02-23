package fun.ports.out.dynamodb.repository.dynamodbAdapter;

import fun.usecases.WalletEnum;
import fun.usecases.wallet.Credit;
import fun.usecases.wallet.Debit;
import fun.usecases.wallet.Loan;
import fun.usecases.wallet.Wallet;
import software.amazon.awssdk.enhanced.dynamodb.AttributeConverter;
import software.amazon.awssdk.enhanced.dynamodb.AttributeValueType;
import software.amazon.awssdk.enhanced.dynamodb.EnhancedType;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.List;
import java.util.logging.Logger;

public class WalletToStringConverter implements AttributeConverter<Wallet> {

    private static final Logger logger = Logger.getLogger(WalletToStringConverter.class.getName());

    private static Double extractBalance(Wallet input) {
        if (input instanceof Debit d) return d.getBalance();
        if (input instanceof Credit c) return c.getBalance();
        if (input instanceof Loan l) return l.getBalance();
        return 0.0;
    }

    @Override
    public AttributeValue transformFrom(Wallet input) {
        if (input == null) {
            logger.severe("[WalletToStringConverter] transformFrom recebeu input NULL — o campo wallet não será salvo!");
            return AttributeValue.builder().nul(true).build();
        }
        logger.info("[WalletToStringConverter] transformFrom -> id=" + input.getId()
                + ", type=" + input.getType()
                + ", balance=" + extractBalance(input)
                + ", class=" + input.getClass().getSimpleName());
        List<AttributeValue> values = List.of(
                AttributeValue.builder().s(input.getId()).build(),
                AttributeValue.builder().s(input.getType().name()).build(),
                AttributeValue.builder().s(input.getDatabaseEntity(input)).build(),
                AttributeValue.builder().n(String.valueOf(extractBalance(input))).build()
        );
        AttributeValue result = AttributeValue.builder().l(values).build();
        logger.info("[WalletToStringConverter] transformFrom -> AttributeValue gerado: " + result);
        return result;
    }

    @Override
    public Wallet transformTo(AttributeValue attributeValue) {
        logger.info("[WalletToStringConverter] transformTo -> attributeValue=" + attributeValue);
        if (attributeValue == null || attributeValue.l() == null || attributeValue.l().isEmpty()) {
            logger.severe("[WalletToStringConverter] transformTo recebeu attributeValue vazio ou nulo!");
            return null;
        }
        List<AttributeValue> values = attributeValue.l();
        String id = values.get(0).s();
        WalletEnum walletType = WalletEnum.valueOf(values.get(1).s());
        Double balance = values.size() > 3 ? Double.parseDouble(values.get(3).n()) : 0.0;
        logger.info("[WalletToStringConverter] transformTo -> id=" + id + ", type=" + walletType + ", balance=" + balance);

        return switch (walletType) {
            case DEBIT_CARD -> new Debit(balance, id);
            case CREDIT_CARD -> new Credit(balance, id);
            case LOAN -> new Loan(balance, id);
            default -> new Debit(balance, id);
        };
    }

    @Override
    public EnhancedType<Wallet> type() {
        return EnhancedType.of(Wallet.class);
    }

    @Override
    public AttributeValueType attributeValueType() {
        return AttributeValueType.L;
    }
}


