package io.github.parqueubajara.api.security;

import io.github.parqueubajara.api.model.SystemUser;
import io.github.parqueubajara.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserService service;
    private final PasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String passwordTyped = authentication.getCredentials().toString();

        SystemUser foundUser = service.findByEmail(email);
        String passwordEncoded = foundUser.getPassword();

        boolean passwordMatches = encoder.matches(passwordTyped, passwordEncoded);

        if(passwordMatches) {
            return new CustomAuthentication(foundUser);
        }

        throw getUserNotFound();
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }

    public UsernameNotFoundException getUserNotFound(){
        return new UsernameNotFoundException("Usuário e/ou senha incorretos!");
    }
}
