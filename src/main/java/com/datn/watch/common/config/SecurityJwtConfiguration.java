//package com.datn.watch.common.config;
//
//import com.nimbusds.jose.jwk.source.ImmutableSecret;
//import com.nimbusds.jose.util.Base64;
//import com.pet.market.api.common.security.filter.CustomBearerTokenResolver;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.jwt.JwtEncoder;
//import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
//import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
//import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
//
//import javax.crypto.SecretKey;
//import javax.crypto.spec.SecretKeySpec;
//
//import static com.pet.market.api.common.security.SecurityUtils.AUTHORITIES_KEY;
//import static com.pet.market.api.common.security.SecurityUtils.JWT_ALGORITHM;
//
//@Configuration
//public class SecurityJwtConfiguration {
//
//    @Value("${app.security.jwt.secret-key}")
//    private String jwtKey;
//
//    @Bean
//    public JwtDecoder jwtDecoder() {
//        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withSecretKey(getSecretKey()).macAlgorithm(JWT_ALGORITHM).build();
//        return token -> {
//            try {
//                return jwtDecoder.decode(token);
//            } catch (Exception e) {
//                throw e;
//            }
//        };
//    }
//
//    @Bean
//    public JwtEncoder jwtEncoder() {
//        return new NimbusJwtEncoder(new ImmutableSecret<>(getSecretKey()));
//    }
//
//    @Bean
//    public JwtAuthenticationConverter jwtAuthenticationConverter() {
//        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
//        grantedAuthoritiesConverter.setAuthorityPrefix("");
//        grantedAuthoritiesConverter.setAuthoritiesClaimName(AUTHORITIES_KEY);
//
//        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
//        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
//        return jwtAuthenticationConverter;
//    }
//
//    @Bean
//    public BearerTokenResolver bearerTokenResolver(JwtDecoder jwtDecoder) {
//        return new CustomBearerTokenResolver(jwtDecoder);
//    }
//
//    private SecretKey getSecretKey() {
//        byte[] keyBytes = Base64.from(jwtKey).decode();
//        return new SecretKeySpec(keyBytes, 0, keyBytes.length, JWT_ALGORITHM.getName());
//    }
//}
