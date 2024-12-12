package shop.mall.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mall.config.PrincipalDetails;
import shop.mall.entity.User;
import shop.mall.service.AuthService;
import shop.mall.service.ItemService;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final ItemService itemService;

    @GetMapping("/")
    public String mainPage(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails, Authentication authentication) {
        if (principalDetails != null && authentication!=null && authentication.isAuthenticated()) {
            model.addAttribute("username", principalDetails.getUsername());
        }
        model.addAttribute("itemAll", itemService.allItemView());
        return "main";
    }

    @GetMapping("/signin")
    public String signinForm() {
        return "members/signin";
    }

    @GetMapping("/signup")
    public String signupForm() {
        return "members/signup";
    }

    @PostMapping("/signup")
    public String signup(User user) {
        User newUser = user;
        User userEntity = authService.signup(user);

        return "members/signin";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) throws Exception {
        authService.logout(session);
        return "redirect:/";
    }

    @GetMapping("/seller")
    public String seller() {
        return "seller/mainSeller";
    }
}
