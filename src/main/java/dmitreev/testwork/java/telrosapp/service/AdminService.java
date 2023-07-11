package dmitreev.testwork.java.telrosapp.service;

import dmitreev.testwork.java.telrosapp.dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AdminService {

    //добавление нового пользователя
    UserDto saveUser(UserDto userDto);

    //изменение данных пользователя
    UserDto updateUser(Long userId, UserDto userDto);

    //получение данных о пользователе по его идентификатору
    UserDto getUserById(Long userId);

    //получение данных о всех пользователях
    List<UserDto> getAllUsers(Integer from, Integer size);

    //удаление пользователя
    void deleteUser(Long userId);

    //добавление фотографии к карточке пользователя
    UserDto savePhotoToUser(Long userId, MultipartFile photo) throws IOException;
}
