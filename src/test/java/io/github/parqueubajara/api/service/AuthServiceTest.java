package io.github.parqueubajara.api.service;

import io.github.parqueubajara.api.dto.request.LoginRequestDTO;
import io.github.parqueubajara.api.dto.request.UserRequestDTO;
import io.github.parqueubajara.api.dto.response.AuthResponseDTO;
import io.github.parqueubajara.api.model.SystemUser;
import io.github.parqueubajara.api.model.enums.Role;
import io.github.parqueubajara.api.security.CustomUserDetailsService;
import io.github.parqueubajara.api.security.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @InjectMocks
    private AuthService service;

    @Mock
    private UserService userService;

    @Mock
    private JwtService jwtService;

    @Mock
    private PasswordEncoder encoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private CustomUserDetailsService customUserDetailsService;

    @Mock
    private UserDetails userDetails;

    private UserRequestDTO registerDTO;
    private LoginRequestDTO loginDTO;
    private SystemUser user;

    @BeforeEach
    void setUp() {
        registerDTO = new UserRequestDTO(
                "Felipe", "Damasceno", "felipe",
                "felipe@email.com", "123456"
        );

        loginDTO = new LoginRequestDTO("felipe@email.com", "123456");

        user = new SystemUser();
        user.setEmail("felipe@email.com");
        user.setUserRole(Role.ADMIN);
    }

    @Test
    void register_ReturnsAuthResponse() {
        when(encoder.encode(any())).thenReturn("senhaCriptografada");
        when(customUserDetailsService.loadUserByUsername(any())).thenReturn(userDetails);
        when(jwtService.generateToken(userDetails)).thenReturn("token.jwt.aqui");

        AuthResponseDTO result = service.register(registerDTO);

        assertThat(result.token()).isEqualTo("token.jwt.aqui");
        assertThat(result.email()).isEqualTo("felipe@email.com");
        verify(userService).save(any(SystemUser.class));
    }

    @Test
    void login_WhenValidCredentials_ReturnsAuthResponse() {
        when(userService.findByEmail(loginDTO.email())).thenReturn(user);
        when(customUserDetailsService.loadUserByUsername(user.getEmail()))
                .thenReturn(userDetails);
        when(jwtService.generateToken(userDetails)).thenReturn("token.jwt.aqui");

        AuthResponseDTO result = service.login(loginDTO);

        assertThat(result.token()).isEqualTo("token.jwt.aqui");
        assertThat(result.email()).isEqualTo("felipe@email.com");
        verify(authenticationManager).authenticate(any());
    }

    @Test
    void login_WhenInvalidCredentials_ThrowsException() {
        doThrow(new BadCredentialsException("Credenciais inválidas"))
                .when(authenticationManager).authenticate(any());

        assertThatThrownBy(() -> service.login(loginDTO))
                .isInstanceOf(BadCredentialsException.class);
    }
}
