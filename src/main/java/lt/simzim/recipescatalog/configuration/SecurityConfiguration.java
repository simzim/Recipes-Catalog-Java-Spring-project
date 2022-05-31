package lt.simzim.recipescatalog.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lt.simzim.recipescatalog.services.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	

	@Autowired
	UserService userService;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		
		
		BCryptPasswordEncoder bc=new BCryptPasswordEncoder();	
		
		auth
		.userDetailsService(this.userService)
		.passwordEncoder(bc);
//		
//		auth
//			.inMemoryAuthentication()
//				.withUser("admin").password(bc.encode("123")).roles("admin");	
	}
	
@Override
protected void configure(HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests()
				
				.antMatchers("/").permitAll()
				.antMatchers("/style/**").permitAll()
				.antMatchers("/recipe/img/**").permitAll()
				.antMatchers("/img/**").permitAll()
				.antMatchers("/recipe/detail/**").permitAll()
				.antMatchers("/recipe/").permitAll()
				//.antMatchers("/product/").permitAll()
								
				.antMatchers("/login*").permitAll()
				//.antMatchers("/register*").permitAll()
				.anyRequest().authenticated()
		
		.and()
		.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/")
			.failureUrl("/login-error")
	.and()
		.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/");
	
}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
