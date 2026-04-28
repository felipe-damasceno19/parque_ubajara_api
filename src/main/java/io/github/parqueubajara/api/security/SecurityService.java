package io.github.parqueubajara.api.security;


import io.github.parqueubajara.api.model.SystemUser;
import io.github.parqueubajara.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityService {

    private final UserService userService;

    public SystemUser getUserLogged(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        // Verifica se é realmente um UserDetails antes de fazer o cast
        if(!(authentication.getPrincipal() instanceof UserDetails yserDetails)){
            return null;
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String login = userDetails.getUsername();
        return userService.findByEmail(login);
    }
}