package carrotmoa.carrotmoa.service;

import carrotmoa.carrotmoa.entity.User;
import carrotmoa.carrotmoa.exception.UserServiceException;
import carrotmoa.carrotmoa.model.request.UserWithdrawalRequest;
import carrotmoa.carrotmoa.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private HttpSession session;

    @Mock
    private User user;

    @Test
    void shouldThrowWhenUserNotFound(){
        UserWithdrawalRequest request = new UserWithdrawalRequest("poix00@gmail.com","112333");
        when(userRepository.findByEmail(request.getEmail())).thenReturn(null);

        UserServiceException e = assertThrows(UserServiceException.class, () -> {
            userService.userWithdrawal(request,session);
        });
            assertEquals(e.getErrorCode(),e.getMessage());
    }

    @Test
    void shouldThrowWhenPasswordNotMatch(){
        User mockUser = new User();
        mockUser.setEmail("poix00@gmail.com");
        mockUser.setPassword("123");
        UserWithdrawalRequest request = new UserWithdrawalRequest("poix00@gmail.com","123");

        when(userRepository.findByEmail(request.getEmail())).thenReturn(mockUser);
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);
        UserServiceException e = assertThrows(UserServiceException.class, () -> {
            userService.userWithdrawal(request,session);
        });
        assertEquals(e.getErrorCode(),e.getMessage());
    }
}
