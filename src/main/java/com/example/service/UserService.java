package com.example.service;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.util.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public void register(String username, String password, Role role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        user.setRole(role);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new CustomUserDetails(u);
    }
}
