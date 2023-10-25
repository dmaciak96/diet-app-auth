package pl.daveproject.webdiet.authorizationservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pl.daveproject.webdiet.authorizationservice.applicationuser.model.ApplicationUser;
import pl.daveproject.webdiet.authorizationservice.applicationuser.service.ApplicationUserService;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserCreator implements ApplicationRunner {

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    private final ApplicationUserService applicationUserService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (applicationUserService.existsByUsername(USERNAME)) {
            log.info("User {} already exists", USERNAME);
            return;
        }
        var createdUser = applicationUserService.registerNewUser(ApplicationUser.builder()
                .email("admin@admin.com")
                .username(USERNAME)
                .password(PASSWORD)
                .firstName(USERNAME)
                .lastName(USERNAME)
                .version(1)
                .build());
        log.info("Created new user {} - {}", createdUser.getUsername(), createdUser.getId());
    }
}
