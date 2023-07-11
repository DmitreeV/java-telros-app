package dmitreev.testwork.java.telrosapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dmitreev.testwork.java.telrosapp.model.UserRole;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    @NotBlank(message = "'surname' can not be blank")
    private String surname;

    @NotBlank(message = "'name' can not be blank")
    private String name;

    @NotBlank(message = "'patronymic' can not be blank")
    private String patronymic;

    @NotBlank(message = "'birthdate' can not be blank")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthdate;

    @Email
    @NotBlank(message = "'email' can not be blank")
    private String email;

    @NotBlank(message = "'telNumber' can not be blank")
    private Long telNumber;

    private String filename;

    @NotNull
    private UserRole role;
}
