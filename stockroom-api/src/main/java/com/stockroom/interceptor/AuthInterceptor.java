package com.stockroom.interceptor;

import com.stockroom.annotation.NonAuth;
import com.stockroom.domain.model.User;
import com.stockroom.domain.service.UserService;
import com.stockroom.util.Const;
import com.stockroom.util.Function;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;
import java.util.Objects;

public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    /**
     * 各controllerに行く前に通る認証メソッド.
     * NonAuthアノテーションを使っている場合この認証ロジックを飛ばす.
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerInterceptor) {
            HandlerMethod hm = (HandlerMethod) handler;
            Method method = hm.getMethod();
            NonAuth annotation = AnnotationUtils.findAnnotation(method, NonAuth.class);
            if (Objects.nonNull(annotation)) {
                return true;
            }

            User user = userService.findByToken(Function.readCookie(request, Const.AUTHORIZE_COOKIE_KEY));
            request.setAttribute("currentUser", user);
        }

        return true;
    }
}
