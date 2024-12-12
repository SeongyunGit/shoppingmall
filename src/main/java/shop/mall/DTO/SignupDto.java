package shop.mall.DTO;

import lombok.Data;
import shop.mall.entity.User;

@Data
public class SignupDto {
    private String username;
    private String password;
    private String email;
    private String name;
    private String role;

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .name(name)
                .role(UserRole.ROLE_USER)
                .build();
    }
}
