package dmitreev.testwork.java.telrosapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dmitreev.testwork.java.telrosapp.model.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(example = "user's last name")
    private String surname;

    @NotBlank(message = "'name' can not be blank")
    @Schema(example = "user's first name")
    private String name;

    @NotBlank(message = "'patronymic' can not be blank")
    @Schema(example = "user's patronymic")
    private String patronymic;

    @NotBlank(message = "'birthdate' can not be blank")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Schema(example = "2000-02-02")
    private LocalDate birthdate;

    @Email
    @NotBlank(message = "'email' can not be blank")
    @Schema(example = "telros-good-job@mail.ru")
    private String email;

    @NotBlank(message = "'telNumber' can not be blank")
    @Schema(example = "88002000600")
    private Long telNumber;

    private String filename;

    @NotNull
    private UserRole role;
}
