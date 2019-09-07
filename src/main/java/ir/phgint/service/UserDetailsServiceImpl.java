package ir.phgint.service;

import ir.phgint.domain.UserProfile;
import ir.phgint.domain.repository.UserProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserProfileDao userProfileDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserProfile user= userProfileDao.findUserByUsername(username);
//        User user = userRepository.findByUsername(username);

        if(user == null){ throw new UsernameNotFoundException(username) ;}

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

//        for (Role role : user.getRole()){

            grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
//        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
