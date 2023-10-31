package pl.daveproject.webdiet.authorizationservice.applicationuser.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.daveproject.webdiet.authorizationservice.applicationuser.exception.UserAlreadyExistsException;
import pl.daveproject.webdiet.authorizationservice.applicationuser.model.ApplicationUser;
import pl.daveproject.webdiet.authorizationservice.applicationuser.model.Authority;
import pl.daveproject.webdiet.authorizationservice.applicationuser.model.Role;
import pl.daveproject.webdiet.authorizationservice.applicationuser.repository.ApplicationUserRepository;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationUserServiceJpaImpl implements ApplicationUserService {
    private static final String BCRYPT_PREFIX = "{bcrypt}";

    private final ApplicationUserRepository applicationUserRepository;

    @Override
    public ApplicationUser registerNewUser(ApplicationUser applicationUser) {
        if (existsByUsername(applicationUser.getUsername())) {
            throw new UserAlreadyExistsException(applicationUser.getUsername());
        }
        applicationUser.setPassword(BCRYPT_PREFIX + encodePassword(applicationUser.getPassword()));
        applicationUser.setId(UUID.randomUUID());
        applicationUser.setAuthorities(List.of(Authority.builder()
                .role(Role.USER)
                .username(applicationUser.getUsername())
                .applicationUser(applicationUser)
                .build()));
        if (applicationUser.getVersion() == null) {
            applicationUser.setVersion(1);
        }
        var savedUser = applicationUserRepository.save(applicationUser);
        log.info("Registered new user {}", savedUser.getUsername());
        return savedUser;
    }

    private String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    @Override
    public boolean existsByUsername(String username) {
        return applicationUserRepository.existsByUsername(username);
    }
}
