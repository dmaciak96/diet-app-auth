package pl.daveproject.webdiet.authorizationservice.applicationuser.service;

import pl.daveproject.webdiet.authorizationservice.applicationuser.model.ApplicationUser;

public interface ApplicationUserService {
    ApplicationUser registerNewUser(ApplicationUser applicationUser);

    boolean existsByUsername(String username);

    boolean existsByEmail(String username);
}
