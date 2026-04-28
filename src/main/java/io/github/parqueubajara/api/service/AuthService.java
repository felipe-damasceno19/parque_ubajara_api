package io.github.parqueubajara.api.service;

import io.github.parqueubajara.api.dto.request.LoginRequestDTO;
import io.github.parqueubajara.api.dto.request.UserRequestDTO;
import io.github.parqueubajara.api.dto.response.AuthResponseDTO;
import io.github.parqueubajara.api.model.SystemUser;
import io.github.parqueubajara.api.model.enums.Role;
import io.github.parqueubajara.api.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;

    public AuthResponseDTO register(UserRequestDTO requestDTO){

        SystemUser user = new SystemUser();
        user.setFirstName(requestDTO.firstName());
        user.setLastName(requestDTO.lastName());
        user.setUsername(requestDTO.username());
        user.setEmail(requestDTO.email());
        user.setPassword(encoder.encode(requestDTO.password()));
        user.setUserRole(Role.USER);

        userService.save(user);

        UserDetails userDetails = customUserDetailsService
                .loadUserByUsername(user.getEmail());

        String token = jwtService.generateToken(userDetails);

        return new AuthResponseDTO(token, user.getEmail(), user.getUserRole().name());
    }

    public AuthResponseDTO login(LoginRequestDTO requestDTO){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDTO.email(), requestDTO.password()
                )
        );

        SystemUser user = userService.findByEmail(requestDTO.email());

        UserDetails userDetails = customUserDetailsService
                .loadUserByUsername(user.getEmail());

        String token = jwtService.generateToken(userDetails);

        return new AuthResponseDTO(token, user.getEmail(), user.getUserRole().name());
    }
}
