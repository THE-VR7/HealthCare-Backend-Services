package backend.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {
	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(ApiSecurityConfig.class);
	
	@Autowired
	private ApiAuthenticationEntryPoint authenticationEntryPoint;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtAuthenticationFilter authenticationFilter;
	
	@Autowired
	public void ConfigureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		System.out.println("in ConfigureGlobal method");
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		System.out.println("in passwordEncoder method");
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
			.authorizeRequests().antMatchers("/register","/signin","/h2-console").permitAll()
			.anyRequest().authenticated().and()
			.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		
//		http.anonymous().and().authorizeRequests().anyRequest().permitAll().and().formLogin().and().httpBasic();
//		.antMatchers("/login").permitAll()
//		.antMatchers("/register").permitAll()
//		.anyRequest().authenticated();
//		this.logger.info(" customized based configure(HttpSecurity)" + http);

	}

//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/patients/**");
//	}
}