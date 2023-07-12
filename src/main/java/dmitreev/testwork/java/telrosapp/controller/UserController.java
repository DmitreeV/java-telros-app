package dmitreev.testwork.java.telrosapp.controller;

import dmitreev.testwork.java.telrosapp.dto.UserDto;
import dmitreev.testwork.java.telrosapp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
@Tag(name = "Operations available to the authorized user.")
public class UserController {

    private final UserService userService;

    @GetMapping("/email")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Getting information about the user by his email.")
    UserDto getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }
}
