package xmu.crms.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import xmu.crms.entity.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author LuLongfei
 * @date 2017-12-22
 */
@Component
@Qualifier("customAuthenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private static final String ROLE_STUDENT = "ROLE_STUDENT";
    private static final String ROLE_TEACHER = "ROLE_TEACHER";

    @Autowired
    @Qualifier("customUserDetailsService")
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        User user = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken auth;
        if (username.startsWith("wechat")) {
            auth = new UsernamePasswordAuthenticationToken(username, null, getAuthorities(user.getType()));
            auth.setDetails(user);
        } else if (username.startsWith("phone")
                && comparePassword(authentication.getCredentials().toString(), user.getPassword())) {
            auth = new UsernamePasswordAuthenticationToken(username, null, getAuthorities(user.getType()));
            auth.setDetails(user);
        } else {
            throw new BadCredentialsException("");
        }
        return auth;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        boolean a =  authentication.equals(
                UsernamePasswordAuthenticationToken.class);
        return a;
    }

    private boolean comparePassword(String input, String trusted) {
        return trusted.equals(input);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(
            int type) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (type == 0) {
            authorities.add(new SimpleGrantedAuthority(ROLE_STUDENT));
        } else if (type == 1) {
            authorities.add(new SimpleGrantedAuthority(ROLE_TEACHER));
        }
        return authorities;
    }
}
