package org.anirudha.watersupplier.securityconf;

import org.anirudha.watersupplier.tokenfilter.AuthTokenFilter;
import org.anirudha.watersupplier.tokenfilter.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	   private final TokenProvider tokenProvider;

	    public WebSecurityConfig(TokenProvider tokenProvider) {
	        this.tokenProvider = tokenProvider;
	    }
	    
	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
	            .authorizeRequests(authorizeRequests ->
	                authorizeRequests
	                    .antMatchers("/api/auth/**").permitAll()
	                    .antMatchers("/api/test/**").permitAll()
	                    .antMatchers("/api/v1/**").permitAll()
	                    .anyRequest().authenticated()
	            )
	            .exceptionHandling()
	                //.authenticationEntryPoint(unauthorizedHandler)
	            .and()
	            .sessionManagement()
	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	            .and()
	            .csrf().disable()
	            .cors(); // You can configure CORS settings here if needed

	        return http.build();
	    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthTokenFilter authTokenFilter() {
        return new AuthTokenFilter(tokenProvider);
    }
}
