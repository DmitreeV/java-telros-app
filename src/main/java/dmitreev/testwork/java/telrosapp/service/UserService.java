package dmitreev.testwork.java.telrosapp.service;

import dmitreev.testwork.java.telrosapp.dto.UserDto;

public interface UserService {

    //получение информации о пользователе по его емейлу
    UserDto getUserByEmail(String email);
}
