package ch.zli.m223.ksh20.coworking_project.security;

import java.util.Map;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            JwtControllerUtil jwtControllerUtil = new JwtControllerUtil();
            Map<String, ?> tokenClaims = jwtControllerUtil.getClaimsFromRequest(request);
            if (tokenClaims == null) {
                modelAndView.addObject("jwtToken", null);
                return;
            } else {
                modelAndView.addObject("jwtToken", tokenClaims);
            }

        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) throws Exception {
        // No post-processing required
    }
}