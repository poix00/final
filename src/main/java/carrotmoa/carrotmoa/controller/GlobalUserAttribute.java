package carrotmoa.carrotmoa.controller;

import carrotmoa.carrotmoa.config.security.CustomUserDetails;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalUserAttribute {
    @ModelAttribute("user")
    public CustomUserDetails user(@AuthenticationPrincipal CustomUserDetails user) {return  user;}
}
