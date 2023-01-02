package com.stockroom.interfaceadapter.presenter;

import com.stockroom.domain.model.User;
import com.stockroom.domain.model.response.UserResponse;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserPresenter {
    public UserResponse resUser(User user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getToken(),
                user.getActivated(),
                user.getStatus(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
