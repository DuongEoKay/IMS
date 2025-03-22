package com.vanduong.ims.config;


import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
@EnableJpaRepositories(basePackages = "com.vanduong.ims")

public class AppConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.
                sessionManagement(manger -> manger.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/admin/**").hasAnyRole("RESTAURANT_OWNER","ADMIN")
                        .requestMatchers("/api/**").authenticated()
                        .anyRequest().permitAll()
                ).addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
                .csrf(csrf->csrf.disable())
                .cors(cors->cors.configurationSource(corsConfigurationSource()));
        return http.build();

    }









    private CorsConfigurationSource corsConfigurationSource()
    {
        return new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration cfg = new CorsConfiguration();
                cfg.setAllowedOrigins(Arrays.asList("http://localhost:3000"));

                cfg.setAllowedMethods(Collections.singletonList("*"));

                cfg.setAllowCredentials(true);

                cfg.setAllowedHeaders(Collections.singletonList("*"));

                cfg.setExposedHeaders(Arrays.asList("Authorization"));

                cfg.setMaxAge(3600L);

                return cfg;
            }
        };
    }



    @Bean
    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();

    }

//
//
//    @Bean
//    public ElasticsearchOperations elasticsearchTemplate() throws  IOException {
//        File tmpDir = File.createTempFile("elastic", Long.toString(System.nanoTime()));
//        System.out.println("Temp directory: " + tmpDir.getAbsolutePath());
//        Settings elasticsearchSettings =
//                Settings.builder()
//                        .put("http.enabled", "true") // 1
//                        .put("index.number_of_shards", "1")
//                        .put("path.data", new File(tmpDir, "data").getAbsolutePath()) // 2
//                        .put("path.logs", new File(tmpDir, "logs").getAbsolutePath()) // 2
//                        .put("path.work", new File(tmpDir, "work").getAbsolutePath()) // 2
//                        .put("path.home", tmpDir) // 3
//                        .build();
//
//        return new ElasticsearchOperations(Node.builder()
//                .local(true)
//                .settings(elasticsearchSettings)
//                .node()
//                .client());
//    }
}