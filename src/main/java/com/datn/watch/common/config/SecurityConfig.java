//package com.datn.watch.common.config;
//
//import com.pet.market.api.common.security.SecurityConstant;
//import com.pet.market.api.common.security.handler.CustomBearerTokenAccessDeniedHandler;
//import jakarta.servlet.http.Cookie;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.annotation.web.configurers.RequestCacheConfigurer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.header.writers.XXssProtectionHeaderWriter;
//import org.springframework.web.cors.CorsConfiguration;
//
//import java.util.stream.Stream;
//
//
//@Configuration
//@EnableMethodSecurity(securedEnabled = true)
//@Slf4j
//@ComponentScan("com.pet.market.api.common.security")
//public class SecurityConfig {
//
//    @Value("${server.servlet.session.cookie.domain}")
//    private String cookieDomain;
//
//    @Value("${app.allowed-origins}")
//    private String[] allowedOrigins;
//
//    @Bean
//    @Order(0)
//    public SecurityFilterChain resourcesSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
//
//        return httpSecurity.securityMatchers(matchers -> matchers.requestMatchers(
//            "/v2/api-docs",
//            "/v3/api-docs",
//            "/v3/api-docs/**",
//            "/swagger-resources",
//            "/swagger-resources/**",
//            "/configuration/ui",
//            "/configuration/security",
//            "/webjars/**",
//            "/swagger-ui.html", "/swagger-ui/**", "/static/**", "/favicon.ico", "/error")).authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll()).requestCache(RequestCacheConfigurer::disable).securityContext(AbstractHttpConfigurer::disable).sessionManagement(AbstractHttpConfigurer::disable).build();
//    }
//
//    @Bean
//    @Order(1)
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//
//        httpSecurity.csrf(AbstractHttpConfigurer::disable)
//            .authorizeHttpRequests(authorize ->
//                authorize
//                    .requestMatchers("/api/v1/admin/**").authenticated()
//                    .requestMatchers("/admin/login","/admin/logout","api/v1/**").permitAll())
//            .exceptionHandling(exceptions -> exceptions
//                .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
//                .accessDeniedHandler(new CustomBearerTokenAccessDeniedHandler())
//            )
//            .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
//
//        httpSecurity.cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(request -> {
//            CorsConfiguration corsConfiguration = new CorsConfiguration();
//            corsConfiguration.setAllowCredentials(true);
//            corsConfiguration.addAllowedHeader("*");
//            corsConfiguration.addAllowedMethod("*");
//            if (allowedOrigins.length == 0) {
//                corsConfiguration.addAllowedOrigin("*");
//            } else {
//                Stream.of(allowedOrigins).forEach(corsConfiguration::addAllowedOrigin);
//            }
//            return corsConfiguration;
//        }));
//
//        httpSecurity.headers(header -> header
//                .xssProtection(xXssConfig -> xXssConfig.headerValue(XXssProtectionHeaderWriter.HeaderValue.ENABLED))
//                .contentSecurityPolicy(contentSecurityPolicyConfig -> contentSecurityPolicyConfig.policyDirectives("font-src  * data:; img-src *; default-src 'self' 'unsafe-inline' 'unsafe-eval' *")));
//        httpSecurity.logout(logout -> logout
//                .logoutUrl("/admin/logout")
//                .logoutSuccessUrl("/api/v1/logout")
//                .addLogoutHandler((request, response, auth) -> {
//                    for (Cookie cookie : request.getCookies()) {
//                        String cookieName = cookie.getName();
//                        if(SecurityConstant.COOKIE_NAME.equals(cookieName)){
//                            Cookie cookieToDelete = new Cookie(cookieName, null);
//                            cookieToDelete.setMaxAge(0);
//                            response.addCookie(cookieToDelete);
//                        }
//                    }
//                })
//        );
//        return httpSecurity.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//}
