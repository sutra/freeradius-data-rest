package org.oxerr.freeradius;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private DataSource dataSource;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth
			.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("select username, value as password, true as enabled from radcheck where username = ? and attribute = 'Password'")
			.authoritiesByUsernameQuery("select username, upper(groupname) as authority from radusergroup where username = ?")
			.rolePrefix("ROLE_");
	}

	@Configuration
	public static class ApiWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			final String apiPattern = "/services/**";
			http.csrf().ignoringAntMatchers(apiPattern);
			http
				.antMatcher(apiPattern)
				.httpBasic();
		}

	}

}
