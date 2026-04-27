package io.github.parqueubajara.api.security;

import io.github.parqueubajara.api.model.SystemUser;
import io.github.parqueubajara.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser user = userService.findByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException("Usuário não encontrado!");
        }

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getUserRole().name())
                .build();

    }
}
