package br.com.sdconecta.doctorsnetwork.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.sdconecta.doctorsnetwork.domain.Role;
import br.com.sdconecta.doctorsnetwork.security.jwt.AuthEntryPointJwt;
import br.com.sdconecta.doctorsnetwork.security.jwt.AuthTokenFilter;
import br.com.sdconecta.doctorsnetwork.security.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;
 
    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
      return new AuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
      authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
      return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
    }
    
//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
//        UserDetails admin = User
//                .withUsername("admin")
//                .password(new BCryptPasswordEncoder().encode("admin"))
//                .roles(Role.ADMIN.toString())
//                .build();
//         
//        return new InMemoryUserDetailsManager(admin);
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.headers().xssProtection().disable();
    	
    	http.headers().frameOptions().disable();
    	
    	http
	  	.cors()
		.and()
		.csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeRequests()
		.antMatchers("/h2/**").permitAll()
		.antMatchers("/api/v1/login").permitAll()
		.antMatchers(HttpMethod.GET, "/api/v1/users/**").permitAll()
		.anyRequest().authenticated();

      http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}