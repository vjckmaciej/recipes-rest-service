package recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import recipes.dto.UserDTO;
import recipes.service.UserService;

import javax.validation.Valid;

@RestController
@Validated
public class UserController {
    private final UserService userService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    public UserController(UserService userService) {this.userService = userService;}

    @PostMapping("/api/register")
    public void register(@Valid @RequestBody UserDTO userDTO) {
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        userService.register(userDTO);
    }
}
