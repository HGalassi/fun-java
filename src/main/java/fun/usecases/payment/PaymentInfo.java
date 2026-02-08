package fun.usecases.payment;

import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import java.util.UUID;
@DynamoDbBean()
public class PaymentInfo {
    private Double actualBalance;
    private Double paymentValue;

    private boolean loanTaken;

    private UUID transactionUuid;
    public PaymentInfo(Double actualBalance, Double paymentValue){
        this.actualBalance = actualBalance;
        this.paymentValue = paymentValue;
    }

    public Double getActualBalance() {
        return actualBalance;
    }

    public Double getPaymentValue() {
        return paymentValue;
    }

    public void setActualBalance(Double newBalance){
        this.actualBalance = newBalance;
    }

    public boolean isLoanTaken() {
        return loanTaken;
    }
    @DynamoDbSortKey
    public void setLoanTaken(boolean loanTaken) {
        this.loanTaken = loanTaken;
    }

    @DynamoDbPartitionKey
    public UUID getTransactionUuid() {
        return transactionUuid;
    }

    public void setTransactionUuid(UUID transactionUuid) {
        this.transactionUuid = transactionUuid;
    }
}
