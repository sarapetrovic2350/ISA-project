package ISA.BloodBank.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling()
				.and().authorizeRequests()
				.antMatchers().permitAll()
				.anyRequest().authenticated().and().httpBasic().and().cors().and();
		http.cors();
		http.csrf().disable();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.POST, "/user/registerUser");
		web.ignoring().antMatchers(HttpMethod.PUT, "/user/update");
		web.ignoring().antMatchers(HttpMethod.GET, "/","/user/getAll", "/webjars/**", "/*.html", "/favicon.ico", "/**/*.html",
				"/**/*.css", "/**/*.js");
		web.ignoring().antMatchers(HttpMethod.POST, "/medicalCenter/createCenter");
		web.ignoring().antMatchers(HttpMethod.GET, "/","/medicalCenter/getAll", "/webjars/**", "/*.html", "/favicon.ico", "/**/*.html",
				"/**/*.css", "/**/*.js");
		web.ignoring().antMatchers(HttpMethod.POST, "/centerAdministrator/registerCenterAdministrator");
		web.ignoring().antMatchers(HttpMethod.GET, "/","/centerAdministrator/getAll", "/webjars/**", "/*.html", "/favicon.ico", "/**/*.html",
				"/**/*.css", "/**/*.js");
		web.ignoring().antMatchers(HttpMethod.PUT, "/medicalCenter/updateCenter");
		web.ignoring().antMatchers(HttpMethod.PUT, "/centerAdministrator/update");
		web.ignoring().antMatchers(HttpMethod.GET, "/user/getUserById/{userId}");
		web.ignoring().antMatchers(HttpMethod.GET, "/medicalCenter/searchMedicalCenterByNameAndPlace/{name}/{place}");
	}
}
