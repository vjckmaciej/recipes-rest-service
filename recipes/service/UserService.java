package recipes.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import recipes.domain.User;
import recipes.dto.UserDTO;
import recipes.repository.UserRepository;
import recipes.service.UserService;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (userRepository.existsByEmail(email)) {
            return userRepository.findByEmail(email);
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }

    public UserService(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public User toUserFromDTO(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    public UserDTO toUserDTO(Optional<User> user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public void register(UserDTO userDTO) {
        User user = toUserFromDTO(userDTO);
        if (!userRepository.existsByEmailIgnoreCase(user.getEmail())) {
            userRepository.save(user);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

}
