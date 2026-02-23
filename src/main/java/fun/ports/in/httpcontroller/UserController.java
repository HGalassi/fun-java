package fun.ports.in.httpcontroller;

import fun.config.startupapp.GenerateStartupInfo;
import fun.ports.in.httpcontroller.dto.UserWithWalletsResponse;
import fun.usecases.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final GenerateStartupInfo generateStartupInfo;
    private final UserService userService;

    public UserController(GenerateStartupInfo generateStartupInfo, UserService userService) {
        this.generateStartupInfo = generateStartupInfo;
        this.userService = userService;
    }

    @GetMapping("/stubData")
    public ResponseEntity<String> stubData() {
        generateStartupInfo.generate();
        return ResponseEntity.ok("Stub data generated successfully.");
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UserWithWalletsResponse> findUserWithWallets(@PathVariable String uuid) {
        UserWithWalletsResponse response = userService.findUserWithWallets(uuid);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }
}

