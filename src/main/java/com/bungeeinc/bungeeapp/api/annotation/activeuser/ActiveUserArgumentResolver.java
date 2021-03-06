package com.bungeeinc.bungeeapp.api.annotation.activeuser;

import com.bungeeinc.bungeeapp.api.exception.authentication.FailedToGetUserFromSecurityContextException;
import com.bungeeinc.bungeeapp.api.exception.authentication.UnsupportedAuthenticationTypeException;
import com.bungeeinc.bungeeapp.database.models.account.BungeeUserDetails;
import org.springframework.core.MethodParameter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class ActiveUserArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(ActiveUser.class)
                && methodParameter.getParameterType().equals(BungeeUserDetails.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof UsernamePasswordAuthenticationToken)) {
            throw new UnsupportedAuthenticationTypeException();
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                (UsernamePasswordAuthenticationToken)authentication;

        Object principal = usernamePasswordAuthenticationToken.getPrincipal();

        if (!(principal instanceof BungeeUserDetails)) {
            throw new FailedToGetUserFromSecurityContextException();
        }

        BungeeUserDetails user = (BungeeUserDetails)principal;

        return user;
    }
}
