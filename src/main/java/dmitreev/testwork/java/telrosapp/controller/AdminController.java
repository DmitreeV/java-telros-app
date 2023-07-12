package dmitreev.testwork.java.telrosapp.controller;

import dmitreev.testwork.java.telrosapp.dto.UserDto;
import dmitreev.testwork.java.telrosapp.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/admin/users")
@Tag(name = "Operations with users available to the administrator.")
public class AdminController {

    private final AdminService adminService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Create a new user.")
    public UserDto saveUser(@RequestBody UserDto userDto) {
        return adminService.saveUser(userDto);
    }

    @PatchMapping("/{userId}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Editing user information.")
    public UserDto updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        return adminService.updateUser(userId, userDto);
    }

    @GetMapping("/{userId}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Getting information about the user by his id.")
    public UserDto getUserById(@PathVariable Long userId) {
        return adminService.getUserById(userId);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Returns a list of all users.")
    public List<UserDto> getAllUsers(@PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                     @Positive @RequestParam(defaultValue = "10") Integer size) {
        return adminService.getAllUsers(from, size);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete user.")
    public void deleteUser(@PathVariable Long userId) {
        adminService.deleteUser(userId);
    }

    @PatchMapping("/{userId}/photo")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Adding a photo to a user card.")
    public UserDto savePhotoToUser(@PathVariable Long userId, @RequestParam(value = "photo") MultipartFile photo) throws IOException {
        return adminService.savePhotoToUser(userId, photo);
    }
}
