package org.launchcode.bookshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().and().csrf().disable();

        http.authorizeRequests()
                .antMatchers("/eventCategories/create","/eventCategories/listCategories","/events/add-tag","/events/create","/events/delete","/events/detail","/events/index","/events/updateEvents","/tags/create","/tags/index","/events/updateEvents").hasAnyAuthority("user","admin")
                .antMatchers("/createUser","/readUser","/delete","/updateUser").hasAuthority("admin")
                .antMatchers("/").permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery(
                        "SELECT username, password,enabled from users where username = ?")
                .authoritiesByUsernameQuery(
                        "SELECT u.username, r.role " +
                                "FROM user_role r, users u " +
                                "WHERE u.username = ? " +
                                "AND r.id = u.user_role_id"
                );
    }

}
