package dmitreev.testwork.java.telrosapp.controller;

import dmitreev.testwork.java.telrosapp.dto.UserDto;
import dmitreev.testwork.java.telrosapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/email")
    @ResponseStatus(value = HttpStatus.OK)
    UserDto getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }
}
