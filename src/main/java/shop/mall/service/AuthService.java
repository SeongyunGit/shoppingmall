package shop.mall.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mall.entity.User;
import shop.mall.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public User signup(User user) {
        String rawpassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawpassword);
        user.setPassword(encPassword);

        User userEntity =userRepository.save(user);
        return userEntity;
    }

    public void logout(HttpSession session) {
        if (session == null) {
            session.invalidate();
        }
        System.out.println("user logout");
    }

    public User signin(String username, String password) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        boolean isPasswordMatch = bCryptPasswordEncoder.matches(password, user.getPassword());
        log.info("로그인");
        if (isPasswordMatch) {
            System.out.println("로그인 성공");
            return User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .role(user.getRole())
                    .build();
        } else {
            return null;
        }
    }
}
