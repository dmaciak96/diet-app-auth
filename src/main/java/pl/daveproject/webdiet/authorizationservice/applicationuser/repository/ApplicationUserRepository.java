package pl.daveproject.webdiet.authorizationservice.applicationuser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.daveproject.webdiet.authorizationservice.applicationuser.model.ApplicationUser;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, UUID> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<ApplicationUser> findByUsername(String username);
}
