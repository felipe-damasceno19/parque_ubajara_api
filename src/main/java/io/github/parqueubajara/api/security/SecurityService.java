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

        if(authentication != null && !authentication.isAuthenticated()) {
            if (authentication instanceof CustomAuthentication customAuth) {
                return customAuth.getUser();
            }
        }
        return null;
    }
}