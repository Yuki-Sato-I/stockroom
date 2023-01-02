package com.stockroom.interfaceadapter.controller;

import com.stockroom.annotation.NonAuth;
import com.stockroom.domain.model.User;
import com.stockroom.domain.model.enums.UserActivate;
import com.stockroom.domain.model.enums.UserStatus;
import com.stockroom.domain.model.request.UserRequest;
import com.stockroom.domain.model.response.UserResponse;
import com.stockroom.domain.service.UserService;
import com.stockroom.interfaceadapter.presenter.UserPresenter;
import com.stockroom.util.Const;
import com.stockroom.util.Function;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    private final UserPresenter userPresenter;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(PasswordEncoder passwordEncoder, UserService userService, UserPresenter userPresenter) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.userPresenter = userPresenter;
    }

    @GetMapping("/current_user")
    public UserResponse find(HttpServletRequest request) {
        final User currentUser = (User)request.getAttribute("currentUser");
        return userPresenter.resUser(currentUser);
    }

    @NonAuth
    @PostMapping("/login")
    public ResponseEntity login(HttpServletResponse response, @RequestBody UserRequest request) throws Exception {
        User user = userService.find(request.getEmail(), request.getPassword());
        Cookie cookie = Function.makeCookie(Const.AUTHORIZE_COOKIE_KEY, user.getToken());
        response.addCookie(cookie);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity logout(HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException {
        final User currentUser = (User)request.getAttribute("currentUser");
        currentUser.changeToken();
        userService.updateToken(currentUser);
        response.addCookie(Function.removeCookie(Const.AUTHORIZE_COOKIE_KEY));
        return ResponseEntity.ok().build();
    }

    @NonAuth
    @PostMapping
    public UserResponse signup(@RequestBody UserRequest request) throws NoSuchAlgorithmException {
        final User user = new User(
                UUID.randomUUID(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                UserActivate.INITIAL_ACTIVATE.getValue(),
                UserStatus.VALID.getValue()
        );
        user.changeToken();
        userService.create(user);
        return userPresenter.resUser(user);
    }

}
