package ISA.BloodBank.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import ISA.BloodBank.model.RestAuthenticationEntryPoint;
import ISA.BloodBank.model.TokenAuthenticationFilter;
import ISA.BloodBank.model.TokenUtils;
import ISA.BloodBank.service.CustomUserDetailsService;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private CustomUserDetailsService jwtUserDetailsService;
	
	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	@Autowired
	private TokenUtils tokenUtils;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling()
		.authenticationEntryPoint(restAuthenticationEntryPoint).and().authorizeRequests()
		.antMatchers().permitAll()
		.anyRequest().authenticated().and().httpBasic().and().cors().and()
		.addFilterBefore(new TokenAuthenticationFilter(tokenUtils, jwtUserDetailsService),
				BasicAuthenticationFilter.class);
		http.cors();
		http.csrf().disable();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.POST, "/auth/login", "/user/registerUser", "/medicalCenter/createCenter", "/centerAdministrator/registerCenterAdministrator");
		web.ignoring().antMatchers(HttpMethod.PUT, "/user/update",  "/medicalCenter/updateCenter",  "/centerAdministrator/update");
		web.ignoring().antMatchers(HttpMethod.GET, "/", "/user/getAll", "/user/getAllRegistredUsers", "/medicalCenter/getAll", 
				"/centerAdministrator/getAll", "/user/getUserById/{userId}","/user//getUserByEmail/{email}",
				"/medicalCenter/getMedicalCenterById/{centerId}", 
				"/medicalCenter/searchMedicalCenterByNameAndPlace/{name}/{place}", 
				"/medicalCenter/filterMedicalCenter/{name}/{place}/{grade}", 
				"/centerAdministrator/getCenterAdministratorById/{adminId}",
				"/centerAdministrator/getCenterAdministratorByEmail/{email}",
				"/centerAdministrator/getMedicalCenterByAdminEmail/{email}",
				"/centerAdministrator/getCenterAdministratorsByCenterId/{centerId}",
				"/webjars/**", "/*.html", "/favicon.ico",
				"/**/*.html", "/**/*.css", "/**/*.js");

	}
}
