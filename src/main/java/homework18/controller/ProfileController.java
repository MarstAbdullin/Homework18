package homework18.controller;

import homework18.security.UserSecurityImpl;
import homework18.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String getProfile(Authentication authentication, Model model) {
        UserSecurityImpl userDetails = (UserSecurityImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        model.addAttribute("user", user);
        return "profile";
    }
}
