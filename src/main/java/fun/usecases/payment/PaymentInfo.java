package fun.usecases.payment;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.util.UUID;
@DynamoDbBean()
public class PaymentInfo {

    private Double actualBalance;
    private Double paymentValue;

    private boolean loanTaken;

    private UUID id;

    public PaymentInfo(Double actualBalance, Double paymentValue){
        this.actualBalance = actualBalance;
        this.paymentValue = paymentValue;
        this.id = UUID.randomUUID();
    }

    public PaymentInfo(){
        this.id = UUID.randomUUID();
    }
    public Double getActualBalance() {
        return actualBalance;
    }

    public Double getPaymentValue() {
        return paymentValue;
    }

    public void setActualBalance(Double actualBalance){
        this.actualBalance = actualBalance;
    }

    public void setPaymentValue(Double paymentValue){
        this.paymentValue = paymentValue;
    }

    public boolean isLoanTaken() {
        return loanTaken;
    }
//    @DynamoDbSortKey
    public void setLoanTaken(boolean loanTaken) {
        this.loanTaken = loanTaken;
    }

    @DynamoDbPartitionKey()
    @DynamoDbAttribute("id")
    public UUID getId() {
        return id;
    }

    @DynamoDbAttribute("id")
    public void setId(UUID id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "PaymentInfo{" +
                "actualBalance=" + actualBalance +
                ", paymentValue=" + paymentValue +
                ", loanTaken=" + loanTaken +
                ", transactionUuid=" + id +
                '}';
    }
}
