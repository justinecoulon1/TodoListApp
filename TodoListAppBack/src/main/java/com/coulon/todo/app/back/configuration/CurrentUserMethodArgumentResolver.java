package com.coulon.todo.app.back.configuration;

import com.coulon.todo.app.back.configuration.annotation.CurrentUser;
import com.coulon.todo.app.back.db.model.User;
import com.coulon.todo.app.back.web.user.UserService;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private final UserService userService;

    public CurrentUserMethodArgumentResolver(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType() == User.class && parameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        String sessionToken = webRequest.getHeader("Session-Token");
        return userService.getUserBySessionToken(sessionToken);
    }
}
