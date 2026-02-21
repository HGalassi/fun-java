package fun.ports.in.httpcontroller;

import fun.config.startupapp.GenerateStartupInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final GenerateStartupInfo generateStartupInfo;

    public UserController(GenerateStartupInfo generateStartupInfo) {
        this.generateStartupInfo = generateStartupInfo;
    }

    @GetMapping("/stubData")
    public ResponseEntity<String> stubData() {
        generateStartupInfo.generate();
        return ResponseEntity.ok("Stub data generated successfully.");
    }
}

