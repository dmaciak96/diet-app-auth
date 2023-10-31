package pl.daveproject.webdiet.authorizationservice.applicationuser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.daveproject.webdiet.authorizationservice.applicationuser.model.ApplicationUserSignUpRequest;
import pl.daveproject.webdiet.authorizationservice.applicationuser.service.ApplicationUserMapper;
import pl.daveproject.webdiet.authorizationservice.applicationuser.service.ApplicationUserService;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class ApplicationUserController {
    public static final String SIGN_UP_ENDPOINT = "/sign-up";
    public static final String SIGN_UP_VIEW_NAME = "signup";
    public static final String LOGIN_URL = "/login";
    public static final String LOGIN_VIEW_NAME = "login";
    public static final String APPLICATION_USER_MODEL_NAME = "application_user";
    public static final String LOGIN_ENDPOINT = "/login";

    private final ApplicationUserService applicationUserService;
    private final ApplicationUserMapper mapper;

    @GetMapping(SIGN_UP_ENDPOINT)
    public ModelAndView getSignUpPage() {
        var modelAndView = new ModelAndView();
        modelAndView.setViewName(SIGN_UP_VIEW_NAME);
        modelAndView.getModel().put(APPLICATION_USER_MODEL_NAME, new ApplicationUserSignUpRequest());
        return modelAndView;
    }

    @GetMapping(LOGIN_ENDPOINT)
    public ModelAndView getLoginPage() {
        var modelAndView = new ModelAndView();
        modelAndView.setViewName(LOGIN_VIEW_NAME);
        return modelAndView;
    }

    @PostMapping(SIGN_UP_ENDPOINT)
    public String registerUser(ApplicationUserSignUpRequest applicationUserSignUpRequest) {
        /**
         * Password min length 6
         * 1 lowercase
         * 1 uppercase
         * 1 special character
         */
        var entity = mapper.toEntity(applicationUserSignUpRequest);
        applicationUserService.registerNewUser(entity);
        return "redirect:%s".formatted(LOGIN_ENDPOINT);
    }
}
