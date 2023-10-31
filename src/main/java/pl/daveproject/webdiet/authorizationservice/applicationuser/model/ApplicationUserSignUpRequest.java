package pl.daveproject.webdiet.authorizationservice.applicationuser.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.daveproject.webdiet.authorizationservice.validation.email.EmailAlreadyExists;
import pl.daveproject.webdiet.authorizationservice.validation.password.Password;
import pl.daveproject.webdiet.authorizationservice.validation.password.PasswordMatch;
import pl.daveproject.webdiet.authorizationservice.validation.username.UsernameAlreadyExists;

@Getter
@Setter
@Builder
@PasswordMatch
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationUserSignUpRequest {

    @Email
    @NotBlank
    @EmailAlreadyExists
    private String email;

    @NotBlank
    @UsernameAlreadyExists
    @Size(min = 6, max = 255, message = "{username.validation.error-message.length}")
    private String username;

    @NotBlank
    @Password
    @Size(min = 6, max = 255, message = "{password.validation.error-message.length}")
    private String password;

    private String confirmPassword;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private byte[] avatar;
}
