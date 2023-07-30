package com.iprofile.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    private AuthenticationSuccessHandler successHandler;

    // Enable jdbc authentication
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }

    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager() throws Exception {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
        jdbcUserDetailsManager.setDataSource(dataSource);
        return jdbcUserDetailsManager;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/register").permitAll()
                .antMatchers("/welcome").hasAnyRole("USER", "ADMIN")
                .antMatchers("/list-todos").hasAnyRole("USER")
                .antMatchers("/listall-todos").hasAnyRole("USER")
                .antMatchers("/add-todo").hasAnyRole("USER")
                .antMatchers("/delete-todo").hasAnyRole("USER")
                .antMatchers("/update-todo").hasAnyRole("USER")

                .antMatchers("/list-scheduletodos").hasAnyRole("USER")
                .antMatchers("/add-scheduletodo").hasAnyRole("USER")
                .antMatchers("/delete-scheduletodo").hasAnyRole("USER")
                .antMatchers("/update-scheduletodo").hasAnyRole("USER")

                .antMatchers("/list-diary").hasAnyRole("USER")
                .antMatchers("/add-diary").hasAnyRole("USER")
                .antMatchers("/delete-diary").hasAnyRole("USER")
                .antMatchers("/update-diary").hasAnyRole("USER")

                .antMatchers("/download").hasAnyRole("ADMIN")
                .antMatchers("/export-to-excel").hasAnyRole("ADMIN")
                .antMatchers("/upload").hasAnyRole("ADMIN")
                .antMatchers("/uploadFile").hasAnyRole("ADMIN")
                .antMatchers("/saveData").hasAnyRole("ADMIN")
                .anyRequest().authenticated().and().formLogin().successHandler(successHandler)
                .loginPage("/login").permitAll().and().logout().permitAll();

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
    }
}