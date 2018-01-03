package com.batata.authentication.service;

import com.batata.authentication.model.entity.User;
import com.batata.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Gets a user by username
     * @param username user name
     * @return userDetails (spring security object) or null if do not find
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDetails user = null;
        User usuario = userRepository.findOneByUser(username);
        if(usuario != null){
            user = createUserDetails(usuario);
        }

        return user;
    }

//  TODO get permissions
//    private Collection<GrantedAuthority> getGrantedAuthorities(User user) {
//        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        for (Authority authority : user.getAuthorities()) {
//            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getName());
//            grantedAuthorities.add(grantedAuthority);
//        }
//
//        return grantedAuthorities;
//    }


    private UserDetails createUserDetails(User user){
        return org.springframework.security.core.userdetails.User.withUsername(user.getUser())
                .password(user.getPassword())
                .roles("USER", "BATATA") // this can be get by a query for all roles / authorities for this user
                                        //actually just can be set or a list of user or authorities since they override the same array :(
                .build();
    }

    public User get(String username) {
        return userRepository.findOneByUser(username);
    }
}
