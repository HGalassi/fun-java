package fun.usecases.user;

import fun.ports.in.httpcontroller.dto.UserWithWalletsResponse;
import fun.ports.in.httpcontroller.dto.WalletDTO;
import fun.ports.out.dynamodb.repository.user.UserRepository;
import fun.ports.out.dynamodb.repository.wallet.WalletRepository;
import fun.usecases.user.entity.UserEntity;
import fun.usecases.wallet.entity.WalletEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    public UserService(UserRepository userRepository, WalletRepository walletRepository) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
    }

    /**
     * Returns the user and all their wallets by the user UUID.
     *
     * @param uuid the UUID part of the user PK (without the "#USER#" prefix)
     * @return UserWithWalletsResponse DTO
     */
    public UserWithWalletsResponse findUserWithWallets(String uuid) {
        String userPk = "#USER#" + uuid;

        UserEntity userQuery = new UserEntity();
        userQuery.setId(userPk);

        UserEntity user = userRepository.find(userQuery);
        if (user == null) {
            return null;
        }

        List<WalletEntity> walletEntities = walletRepository.findByUserId(userPk);
        List<WalletDTO> walletDTOs = walletEntities.stream()
                .map(WalletDTO::from)
                .collect(Collectors.toList());

        return UserWithWalletsResponse.from(user, walletDTOs);
    }
}

