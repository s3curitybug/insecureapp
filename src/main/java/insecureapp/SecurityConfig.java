package insecureapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@Configuration
public class SecurityConfig
        extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(
            HttpSecurity http)
            throws Exception {

        http

                // Login.
                .formLogin().disable()

                // Logout.
                .logout().disable()

                // Unauthorized Handling.
                .exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))

                // Allow Public Endpoints.
                .and().authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/favicon.ico").permitAll()

                // Require Authentication for any other Endpoint.
                .anyRequest().fullyAuthenticated()

                // CSRF.
                .and().csrf().disable();

    }

}
