package fun.ports.in.httpcontroller.dto;

import fun.usecases.user.entity.UserEntity;

import java.time.Instant;
import java.util.List;

public class UserWithWalletsResponse {

    private String id;
    private String name;
    private String email;
    private Instant createdAt;
    private List<WalletDTO> wallets;

    public UserWithWalletsResponse() {
    }

    public static UserWithWalletsResponse from(UserEntity user, List<WalletDTO> wallets) {
        UserWithWalletsResponse dto = new UserWithWalletsResponse();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setWallets(wallets);
        return dto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public List<WalletDTO> getWallets() {
        return wallets;
    }

    public void setWallets(List<WalletDTO> wallets) {
        this.wallets = wallets;
    }
}

