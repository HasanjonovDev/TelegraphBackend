package uz.pdp.telegraphbackend.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.telegraphbackend.exceptions.AuthenticationFailedException;
import uz.pdp.telegraphbackend.repository.UserRepository;

@Service
@AllArgsConstructor
public class AuthService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new AuthenticationFailedException("Username Not Found"));
    }
}
