package com.example.team_project.domain.domain.user.domain;
import com.example.team_project.enums.Role;
import com.example.team_project.enums.UserGrade;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
@Entity
@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String userName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserGrade userGrade;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    /**
     * 회원가입 시 유저의 정보를 담는 생성자
     */
    public User(String userId, String password, String userName, String email, String phoneNumber) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userGrade = UserGrade.SILVER;
        this.role = Role.USER;
    }

    public User(Long id, String password, String phoneNumber) {
        this.id = id;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public User(String userId, String password, String userName, String phoneNumber) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
    }

    /**
     * 유저의 패스워드를 암호화
     */
    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

    /**
     * 입력 받은 비밀번호를 검증하는 메서드
     */
    public boolean isValidPassword(String password, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches((password), this.password);
    }

    /**
     * 유저의 정보를 변경하는 메서드들
     */
    public void modify(String password, String userName, String email, String PhoneNumber) {
        this.password = password;
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }




}