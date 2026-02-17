package fun.usecases.payment.entity;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import java.util.Date;
import java.util.UUID;

@DynamoDbBean()
public class PaymentInfo {

    private Double actualBalance;
    private Double paymentValue;
    private boolean loanTaken;
    private UUID id;
    private Date createdAt;

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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        if (this.createdAt.after(createdAt)) {
            throw new IllegalArgumentException("createdAt cannot be set to a date before the existing createdAt");
        }
        this.createdAt = createdAt;
    }

    public void setActualBalance(Double actualBalance){
        this.actualBalance = actualBalance;
    }

    public void setPaymentValue(Double paymentValue){
        if (paymentValue < 0.00){
            throw new IllegalArgumentException("paymentValue cannot be negative");
        }
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
        if(this.id != null){
            throw new IllegalStateException("id is already set and cannot be changed");
        }
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
