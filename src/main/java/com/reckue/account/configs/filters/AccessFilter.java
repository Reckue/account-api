//package com.reckue.account.configs.filters;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.reckue.account.exceptions.AuthenticationException;
//import com.reckue.account.transfers.ErrorTransfer;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * Class AccessFilter aims to guarantee a single execution per request
// * dispatch, on any servlet container.
// *
// * @author Kamila Meshcheryakova
// */
//@Slf4j
////@Component
//@RequiredArgsConstructor
//public class AccessFilter extends OncePerRequestFilter {
//
//    private final TokenProvider tokenProvider;
//
//    /**
//     * This method is used to cause the next filter in the chain to be invoked.
//     *
//     * @param httpServletRequest  request information for HTTP servlets
//     * @param httpServletResponse HTTP-specific functionality in sending a response
//     * @param filterChain         several filters
//     * @throws ServletException if the processing fails for any other reason
//     * @throws IOException      if an I/O error occurs during the processing of the request
//     */
//    @Override
//    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
//                                    FilterChain filterChain) throws ServletException, IOException {
//        String token = tokenProvider.extractToken(httpServletRequest);
//        try {
//            if (token != null && tokenProvider.validateToken(token)) {
//                Authentication auth = tokenProvider.authenticateToken(token);
//                SecurityContextHolder.getContext().setAuthentication(auth);
//            }
//        } catch (AuthenticationException e) {
//            SecurityContextHolder.clearContext();
//            ErrorTransfer responseInfo = new ErrorTransfer(e.getMessage(), e.getHttpStatus(), e.getHttpStatus().value());
//            httpServletResponse.resetBuffer();
//            httpServletResponse.setStatus(e.getHttpStatus().value());
//            httpServletResponse.setHeader("Content-Type", "application/json");
//            httpServletResponse.getOutputStream().print(new ObjectMapper().writeValueAsString(responseInfo));
//            httpServletResponse.flushBuffer();
//            return;
//        }
//        filterChain.doFilter(httpServletRequest, httpServletResponse);
//    }
//}
