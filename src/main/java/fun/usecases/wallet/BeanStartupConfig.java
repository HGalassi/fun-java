package fun.usecases.wallet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Random;
import java.util.UUID;


@Configuration
public class BeanStartupConfig {

    @RequestScope
    @Bean
    public Debit debit(){
        return new Debit(new Random().nextDouble(-2_000_00, 2_000_00), UUID.randomUUID().toString());
    }

    @RequestScope
    @Bean
    public Credit credit() {
        return new Credit(new Random().nextDouble(0, 2_000_00), UUID.randomUUID().toString());
    }

    @RequestScope
    @Bean
    public Loan loan(){
        return new Loan(0.00, UUID.randomUUID().toString());
    }
}
