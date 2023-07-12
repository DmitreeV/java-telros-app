package dmitreev.testwork.java.telrosapp.service.impl;

import dmitreev.testwork.java.telrosapp.dto.UserDto;
import dmitreev.testwork.java.telrosapp.error.exception.NotFoundException;
import dmitreev.testwork.java.telrosapp.mapper.UserMapper;
import dmitreev.testwork.java.telrosapp.model.User;
import dmitreev.testwork.java.telrosapp.repository.UserRepository;
import dmitreev.testwork.java.telrosapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto getUserByEmail(String email) {
        String query = email.toLowerCase();

        User user = userRepository.findUserByEmail(query);
        if (user.getName().isEmpty()) {
            throw new NotFoundException("User not found.");
        }
        log.info("Received a user with email {}.", user.getEmail());
        return userMapper.toUserDto(user);
    }
}
