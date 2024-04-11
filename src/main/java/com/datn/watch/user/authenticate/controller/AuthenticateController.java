package com.datn.watch.user.authenticate.controller;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pet.market.api.common.model.json.Result;
import com.pet.market.api.common.model.json.ResultData;
import com.pet.market.api.common.security.SecurityConstant;
import com.pet.market.api.common.utils.RequestUtils;
import com.pet.market.api.user.authenticate.model.LoginDTO;
import com.pet.market.api.user.authenticate.model.UserDTO;
import com.pet.market.api.user.authenticate.service.UserAuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import static com.pet.market.api.common.security.SecurityUtils.AUTHORITIES_KEY;
import static com.pet.market.api.common.security.SecurityUtils.JWT_ALGORITHM;


/**
 * Controller to authenticate users.
 */
@RestController
@RequestMapping("/api/v1")
@Slf4j
@Tag(name = "Authentication API v1")
public class AuthenticateController {

    private final JwtEncoder jwtEncoder;

    @Value("${app.security.jwt.token-validity-in-seconds:0}")
    private long tokenValidityInSeconds;

    @Value("${app.security.jwt.token-validity-in-seconds-for-remember-me:0}")
    private long tokenValidityInSecondsForRememberMe;

    @Value("${app.cookie.domain}")
    private String cookieDomain;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final PasswordEncoder passwordEncoder;

    private final UserAuthService userAuthService;

    public AuthenticateController(JwtEncoder jwtEncoder, AuthenticationManagerBuilder authenticationManagerBuilder, PasswordEncoder passwordEncoder, UserAuthService userAuthService) {
        this.jwtEncoder = jwtEncoder;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.passwordEncoder = passwordEncoder;
        this.userAuthService = userAuthService;
    }

    /**
     * Authenticate Username & password
     * @param vm Login DTO: ussername, password
     * @return token in cookie
     */
    @PostMapping("/authenticate")
    public ResponseEntity authorize(@Valid @RequestBody LoginDTO vm) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            vm.getUsername(),
            vm.getPassword()
        );

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = this.createToken(authentication, vm.isRememberMe());

        ResponseCookie jwtTokenCookie = ResponseCookie.from(SecurityConstant.COOKIE_NAME, jwt).maxAge(86400)
                .httpOnly(true).secure(true).sameSite("None").build();
        ResultData<JWTToken> response = new ResultData<>();
        response.setResultData(new JWTToken(jwt, 86400));

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtTokenCookie.toString())
                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS,"true")
                .body(response);
    }

    @GetMapping("/logout")
    public ResponseEntity logout(){
        return ResponseEntity.ok().body(new Result());
    }

    //TODO Remove
    /**
     * generate password
     *
     * @param vm
     * @return
     */
    @PostMapping("/password")
    public ResultData<String> createPassword(@Valid @RequestBody LoginDTO vm) {
        ResultData<String> response = new ResultData();
        String pass = passwordEncoder.encode(vm.getPassword());
        log.debug("Generating password: " + pass);
        response.setResultData(pass);
        return response;
    }

    /**
     * {@code GET /authenticate} : check if the user is authenticated, and return its login.
     *
     * @param request the HTTP request.
     * @return the login if the user is authenticated.
     */
    @GetMapping("/authenticate")
    public String isAuthenticated(HttpServletRequest request) {
        log.debug("REST request to check if the current user is authenticated");
        return request.getRemoteUser();
    }

    //TODO Remove
    @GetMapping("/authenticate/cookie")
    public ResultData<String> showCookie(@CookieValue(SecurityConstant.COOKIE_NAME) String cookie) {
        ResultData<String> response = new ResultData<>();
        response.setResultData(cookie);
        return response;
    }

    @GetMapping("/authenticate/userInfo")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResultData<UserDTO.UserRes> getCurrentUserInfo() {
        ResultData<UserDTO.UserRes> response = new ResultData<>();
//        response.setResultData(userAuthService.getUserInfo());
        return response;
    }

    public String createToken(Authentication authentication, boolean rememberMe) {
        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));

        Instant now = Instant.now();
        Instant validity;
        if (rememberMe) {
            validity = now.plus(this.tokenValidityInSecondsForRememberMe, ChronoUnit.SECONDS);
        } else {
            validity = now.plus(this.tokenValidityInSeconds, ChronoUnit.SECONDS);
        }

        // @formatter:off
        var claims = JwtClaimsSet.builder()
            .issuedAt(now)
            .expiresAt(validity)
            .subject(authentication.getName())
            .claim(AUTHORITIES_KEY, authorities)
            .build();

        var jwsHeader = JwsHeader.with(JWT_ALGORITHM).build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();
    }

    /**
     * Stricky - Pass cookie for development
     * @return cookie domain
     */
    private String handleCookieDomain() {
        if (StringUtils.hasText(cookieDomain)){
            return cookieDomain;
        } else {
            return RequestUtils.getIp();
        }
    }

    /**
     * Object to return as body in JWT Authentication.
     */
    static class JWTToken {

        private String idToken;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Integer maxAge;

        JWTToken(String idToken) {
            this.idToken = idToken;
        }

        JWTToken(String idToken, Integer maxAge) {
            this.idToken = idToken;
            this.maxAge = maxAge;
        }

        @JsonProperty("token_id")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("max_age")
        Integer getMaxAge() {
            return maxAge;
        }
        void setMaxAge(Integer maxAge) {
            this.maxAge = maxAge;
        }
    }
}
