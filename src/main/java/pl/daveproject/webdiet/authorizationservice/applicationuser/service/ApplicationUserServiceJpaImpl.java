package pl.daveproject.webdiet.authorizationservice.applicationuser.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    private final ApplicationUserRepository applicationUserRepository;

    @Override
    public ApplicationUser registerNewUser(ApplicationUser applicationUser) {
        if (existsByUsername(applicationUser.getUsername())) {
            throw new UserAlreadyExistsException(applicationUser.getUsername());
        }
        applicationUser.setPassword("{noop}" + applicationUser.getPassword());
        applicationUser.setId(UUID.randomUUID());
        applicationUser.setAuthorities(List.of(Authority.builder()
                .role(Role.USER)
                .username(applicationUser.getUsername())
                .applicationUser(applicationUser)
                .build()));
        applicationUser.setEnabled(true);
        return applicationUserRepository.save(applicationUser);
    }

    @Override
    public boolean existsByUsername(String username) {
        return applicationUserRepository.existsByUsername(username);
    }
}
