package edu.itstep.blockchain;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled=true, prePostEnabled=true)
public class BasicAuthConfiguration {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				(requests) -> 
				requests.requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
				
				.anyRequest().authenticated()).cors(Customizer.withDefaults()).csrf(AbstractHttpConfigurer::disable)
		.httpBasic(Customizer.withDefaults());
		http.headers((headers) -> headers.disable());
		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = User.builder().username("user")
				.password(passwordEncoder().encode("password")).roles("USER")
				.build();
		UserDetails admin = User.builder().username("admin")
				.password(passwordEncoder().encode("password"))
				.roles("USER","ADMIN").build();
		UserDetails root = User.builder().username("root")
				.password(passwordEncoder().encode("root"))
				.roles("ROOT").build();
		return new InMemoryUserDetailsManager(user, admin,root);

	}
	@Bean
	  CorsConfigurationSource corsConfigurationSource() {
	      CorsConfiguration configuration = new CorsConfiguration();
	      configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
	      configuration.setAllowedMethods(Arrays.asList("GET","POST","DELETE","PUT"));
	      configuration.setAllowedHeaders(List.of("*"));
	      configuration.setAllowCredentials(true);
	      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	      source.registerCorsConfiguration("/**", configuration);
	      return source;
	  }
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
