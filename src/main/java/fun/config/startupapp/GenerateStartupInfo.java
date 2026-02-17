package fun.config.startupapp;

import fun.infrastructure.dynamodb.factory.DynamoFactory;
import fun.ports.out.dynamodb.repository.user.UserRepository;
import fun.ports.out.dynamodb.repository.wallet.WalletRepository;
import fun.usecases.WalletEnum;
import fun.usecases.user.entity.UserEntity;
import fun.usecases.wallet.entity.WalletEntity;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Logger;

import static fun.ports.out.dynamodb.repository.dynamodbAdapter.DynamoDBAdapter.*;
import static java.util.UUID.randomUUID;

@Configuration
public class GenerateStartupInfo {

    Logger logger = Logger.getLogger(GenerateStartupInfo.class.getName());

    private UserEntity userEntity;
    private WalletEntity walletEntity;
    private UserRepository userRepository;
    private WalletRepository walletRepository;
    private DynamoDbClient dynamoDbClient;

    public GenerateStartupInfo(UserEntity user, WalletEntity wallet,
                               UserRepository userRepository, WalletRepository walletRepository, DynamoFactory factory){

        //TODO: Refactor: 1st user opens account with debit wallet. Then save user and wallet info in dynamodb.
        //TODO: Then user can create more wallets, then, create otter wallets and then update user setting his wallets.

        this.userEntity = user;
        this.walletEntity = wallet;
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
        this.dynamoDbClient = factory.dynamoDbClient();

        createUser(user, userRepository);

        createUserWallet(user, wallet, walletRepository);
    }

    private static void createUserWallet(UserEntity user, WalletEntity wallet, WalletRepository walletRepository) {
        wallet.setBalance(Random.from(new Random()).nextDouble(2_000_0));
        wallet.setType(WalletEnum.DEBIT_CARD);
        wallet.setUserId(user.getId());
        wallet.setCreatedAt(new Date());
        walletRepository.save(wallet);
    }

    private static void createUser(UserEntity user, UserRepository userRepository) {
        user.setEmail("johndoe@doe.com");
        user.setName("John Doe");
        user.setCreatedAt(Instant.now());
        userRepository.save(user);
    }
}
