package pl.daveproject.webdiet.authorizationservice.applicationuser.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.daveproject.webdiet.authorizationservice.applicationuser.exception.UserAlreadyExistsException;
import pl.daveproject.webdiet.authorizationservice.applicationuser.model.Authority;
import pl.daveproject.webdiet.authorizationservice.applicationuser.repository.ApplicationUserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationUserDetailsService implements UserDetailsService {
    private final ApplicationUserRepository applicationUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var applicationUser = applicationUserRepository.findByUsername(username)
                .orElseThrow(() -> new UserAlreadyExistsException("User %s not exists in database".formatted(username)));
        return new User(applicationUser.getUsername(),
                applicationUser.getPassword(),
                mapToSimpleGrantedAuthorities(applicationUser.getAuthorities()));
    }

    private List<SimpleGrantedAuthority> mapToSimpleGrantedAuthorities(List<Authority> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRole().name()))
                .toList();
    }
}
