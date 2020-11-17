package nl.avthart.todo.app.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure( HttpSecurity http )
            throws Exception {
        http
                .authorizeRequests()
                .antMatchers( "/css/**", "/js/**", "/img/**", "/actuator/**", "/h2-console", "/h2-console/**" ).permitAll()
                .anyRequest().authenticated();
        http
				.headers()
				.frameOptions().disable();

        http
                .csrf()
                .disable()
                .formLogin()
                .defaultSuccessUrl( "/index.html" )
                .loginPage( "/login.html" )
                .failureUrl( "/login.html?error" )
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl( "/login.html?logout" )
                .permitAll()
        ;
    }

    @Override
    protected void configure( AuthenticationManagerBuilder auth )
            throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser( "albert" ).password( "{noop}1234" ).roles( "USER" ).and()
                .withUser( "foo" ).password( "{noop}bar" ).roles( "USER" );
    }
}
