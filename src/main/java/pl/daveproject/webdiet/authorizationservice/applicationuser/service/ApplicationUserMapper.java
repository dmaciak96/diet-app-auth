package pl.daveproject.webdiet.authorizationservice.applicationuser.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.daveproject.webdiet.authorizationservice.applicationuser.model.ApplicationUser;
import pl.daveproject.webdiet.authorizationservice.applicationuser.model.ApplicationUserSignUpRequest;

@Mapper
public interface ApplicationUserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", constant = "true")
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    ApplicationUser toEntity(ApplicationUserSignUpRequest request);
}
