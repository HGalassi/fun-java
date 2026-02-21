package fun.config.startupapp;

import fun.ports.out.dynamodb.repository.user.UserRepository;
import fun.ports.out.dynamodb.repository.wallet.WalletRepository;
import fun.usecases.WalletEnum;
import fun.usecases.user.entity.UserEntity;
import fun.usecases.wallet.Credit;
import fun.usecases.wallet.Debit;
import fun.usecases.wallet.Loan;
import fun.usecases.wallet.entity.WalletEntity;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Logger;

@Configuration
public class GenerateStartupInfo {

    static Logger logger = Logger.getLogger(GenerateStartupInfo.class.getName());

    private WalletEntity walletEntity;
    private UserRepository userRepository;
    private WalletRepository walletRepository;
    private final OpenApi chatClient;
    private final ChatModel chatModel;
    private final Ollama ollama;


    public GenerateStartupInfo(WalletEntity wallet,
                               UserRepository userRepository,
                               WalletRepository walletRepository,
                               OpenApi openApi,
                               ChatModel chatModel,
                               Ollama ollama){
        this.walletEntity = wallet;
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
        this.chatClient = openApi;
        this.chatModel = chatModel;
        this.ollama = ollama;
        generate();
    }

    public void generate() {
        UserEntity userEntity = new UserEntity();
        createUser(userEntity, this.userRepository, chatModel, ollama);
        createUserDebitWallet(userEntity, this.walletEntity, this.walletRepository);
        userEntity.setWalletId(this.walletEntity.getId());
        createUser(userEntity, this.userRepository, chatModel, ollama);
        createUserCreditWallet(userEntity, this.walletEntity, this.walletRepository);
        createUserLoanWallet(userEntity, this.walletEntity, this.walletRepository);

    }

    private static void createUser(UserEntity user, UserRepository userRepository, ChatModel chatModel, Ollama ollama) {
        UserEntity userEntity = ollama.userStubbedInfo();
        userEntity.setCreatedAt(Instant.now());
        userEntity.setWalletId("NONE");
        userEntity.setId(UUID.randomUUID().toString());
        userRepository.save(userEntity);
        logger.info("User created with id: " + userEntity.getId());
    }

    private static void createUserDebitWallet(UserEntity user, WalletEntity walletEntity, WalletRepository walletRepository) {
        walletEntity.setId(UUID.randomUUID().toString());
        walletEntity.setType(WalletEnum.DEBIT_CARD);
        walletEntity.setUserId(user.getId());
        walletEntity.setCreatedAt(Instant.now());
        walletEntity.setWallet(new Debit(new Random().nextDouble(-2_000_0, 2_000_0),UUID.randomUUID().toString()));
        walletRepository.save(walletEntity);
        logger.info("Wallet created with id: " + walletEntity.getId());
    }

    private static void createUserCreditWallet(UserEntity user, WalletEntity walletEntity, WalletRepository walletRepository) {
        walletEntity.setType(WalletEnum.CREDIT_CARD);
        walletEntity.setUserId(user.getId());
        walletEntity.setCreatedAt(Instant.now());
        walletEntity.setWallet(new Credit(new Random().nextDouble(-2_000_0, 2_000_0), UUID.randomUUID().toString()));
        walletRepository.save(walletEntity);
        logger.info("Wallet created with id: " + walletEntity.getId());
    }

    private static void createUserLoanWallet(UserEntity user, WalletEntity walletEntity, WalletRepository walletRepository) {
        walletEntity.setType(WalletEnum.LOAN);
        walletEntity.setUserId(user.getId());
        walletEntity.setCreatedAt(Instant.now());
        walletEntity.setWallet(new Loan(new Random().nextDouble(-2_000_0, 2_000_0), UUID.randomUUID().toString()));
        walletRepository.save(walletEntity);
        logger.info("Wallet created with id: " + walletEntity.getId());
    }


}
