package fun.config.startupapp;

import fun.infrastructure.dynamodb.factory.DynamoFactory;
import fun.ports.out.dynamodb.repository.user.UserRepository;
import fun.ports.out.dynamodb.repository.wallet.WalletRepository;
import fun.usecases.WalletEnum;
import fun.usecases.user.entity.UserEntity;
import fun.usecases.wallet.Credit;
import fun.usecases.wallet.Debit;
import fun.usecases.wallet.Loan;
import fun.usecases.wallet.entity.WalletEntity;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.time.Instant;
import java.util.Random;
import java.util.logging.Logger;

@Configuration
public class GenerateStartupInfo {

    static Logger logger = Logger.getLogger(GenerateStartupInfo.class.getName());

    private UserEntity userEntity;
    private WalletEntity walletEntity;
    private UserRepository userRepository;
    private WalletRepository walletRepository;
    private DynamoDbClient dynamoDbClient;

    public GenerateStartupInfo(UserEntity user, WalletEntity wallet,
                               UserRepository userRepository, WalletRepository walletRepository, DynamoFactory factory){

        this.userEntity = user;
        this.walletEntity = wallet;
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
        this.dynamoDbClient = factory.dynamoDbClient();

        createUser(user, userRepository);
        createUserDebitWallet(user, wallet, walletRepository);
        createUserCreditWallet(user, wallet, walletRepository);
        createUserLoanWallet(user, wallet, walletRepository);
    }

    private static void createUser(UserEntity user, UserRepository userRepository) {
        user.setEmail("johndoe@doe.com");
        user.setName("John Doe");
        user.setCreatedAt(Instant.now());
        userRepository.save(user);
        logger.info("User created with id: " + user.getId());
    }

    private static void createUserDebitWallet(UserEntity user, WalletEntity walletEntity, WalletRepository walletRepository) {
        walletEntity.setType(WalletEnum.DEBIT_CARD);
        walletEntity.setUserId(user.getId());
        walletEntity.setCreatedAt(Instant.now());
        walletEntity.setWallet(new Debit(new Random().nextDouble(-2_000_0, 2_000_0)));
        walletRepository.save(walletEntity);
        logger.info("Wallet created with id: " + walletEntity.getId());
    }

    private static void createUserCreditWallet(UserEntity user, WalletEntity walletEntity, WalletRepository walletRepository) {
        walletEntity.setType(WalletEnum.CREDIT_CARD);
        walletEntity.setUserId(user.getId());
        walletEntity.setCreatedAt(Instant.now());
        walletEntity.setWallet(new Credit(new Random().nextDouble(-2_000_0, 2_000_0)));
        walletRepository.save(walletEntity);
        logger.info("Wallet created with id: " + walletEntity.getId());
    }

    private static void createUserLoanWallet(UserEntity user, WalletEntity walletEntity, WalletRepository walletRepository) {
        walletEntity.setType(WalletEnum.LOAN);
        walletEntity.setUserId(user.getId());
        walletEntity.setCreatedAt(Instant.now());
        walletEntity.setWallet(new Loan(new Random().nextDouble(-2_000_0, 2_000_0)));
        walletRepository.save(walletEntity);
        logger.info("Wallet created with id: " + walletEntity.getId());
    }


}
