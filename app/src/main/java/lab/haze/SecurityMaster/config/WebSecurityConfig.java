package lab.haze.SecurityMaster.config;

import javax.activation.DataSource;
import javax.swing.tree.ExpandVetoException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ch.qos.logback.core.net.SyslogOutputStream;
import lab.haze.SecurityMaster.Repository.UserRepository;
import lab.haze.SecurityMaster.Service.UserDetailsServiceImpl;
import lab.haze.SecurityMaster.Service.UserServiceImpl;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl useeDetailsService;

    

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/regist", "/regcon","/getuser", "/login", "/login-error", "/css/**" , "/js/**").permitAll()
                .antMatchers("/hidden").hasAuthority("USER")
                .and()
            .formLogin()
                .loginPage("/login")
                .failureUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/hidden");
                }

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    void configureAuthenticationManager(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}