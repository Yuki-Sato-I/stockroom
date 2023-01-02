package com.stockroom.domain.service;

import com.stockroom.domain.model.User;
import com.stockroom.domain.repository.UserRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public User findByToken(final String token) {
        return userRepository.findByToken(token);
    }

    public User find(final String email, final String password) throws Exception {
        User user = userRepository.findByEmail(email);
        if (Objects.isNull(user) || passwordEncoder.matches(password, user.getPasswordDigest())) {
            // FIXME ちゃんとした例外を投げる.
            throw new NotFoundException("emailもしくはパスワードが違います.");
        }
        return user;
    }

    public User create(User user) {
        userRepository.create(user);
        return user;
    }

    public User updateToken(User user) {
        userRepository.updateToken(user);
        return user;
    }
}
