package info.keeper.service;

import info.keeper.models.User;
import info.keeper.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public MyUserDetailsService() { }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //get user from the database
        User user = userRepository.findUserByUsername(email);

        if(user == null) {
            throw  new UsernameNotFoundException("User not found");
        }
        MyUserDetails userDetails  = new MyUserDetails(user);
        return userDetails;
    }
}
