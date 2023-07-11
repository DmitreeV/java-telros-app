package dmitreev.testwork.java.telrosapp.service.impl;

import dmitreev.testwork.java.telrosapp.dto.UserDto;
import dmitreev.testwork.java.telrosapp.error.exception.ConflictException;
import dmitreev.testwork.java.telrosapp.error.exception.NotFoundException;
import dmitreev.testwork.java.telrosapp.mapper.UserMapper;
import dmitreev.testwork.java.telrosapp.model.User;
import dmitreev.testwork.java.telrosapp.model.UserRole;
import dmitreev.testwork.java.telrosapp.repository.UserRepository;
import dmitreev.testwork.java.telrosapp.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Value("/Users/dmitreevalerko/dima/telros-app/photo")
    private String uploadPath;

    @Override
    public UserDto saveUser(UserDto userDto) {
        User user = userMapper.toUser(userDto);
        user.setRole(UserRole.USER);
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("The user already exists.");
        }
        log.info("Saved new user {}.", userDto);
        return userMapper.toUserDto(user);
    }

    @Override
    public UserDto savePhotoToUser(Long userId, MultipartFile photo) throws IOException {
        User user = getUser(userId);

        if (photo != null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + photo.getOriginalFilename();
            photo.transferTo(new File(uploadPath + "/" + resultFilename));
            user.setFilename(resultFilename);
        }
        log.info("Saved new photo to user with id {}.", userId);
        return userMapper.toUserDto(userRepository.save(user));
    }

    @Override
    public UserDto updateUser(Long userId, UserDto userDto) {
        User userToUpdate = getUser(userId);
        User user = userMapper.toUser(userDto);
        if (user.getSurname() != null) {
            userToUpdate.setSurname(user.getSurname());
        }
        if (user.getName() != null) {
            userToUpdate.setName(user.getName());
        }
        if (user.getPatronymic() != null) {
            userToUpdate.setPatronymic(user.getPatronymic());
        }
        if (user.getBirthdate() != null) {
            userToUpdate.setBirthdate(user.getBirthdate());
        }
        if (user.getEmail() != null) {
            userToUpdate.setEmail(user.getEmail());
        }
        if (user.getTelNumber() != null) {
            userToUpdate.setTelNumber(user.getTelNumber());
        }
        log.info("The user with id {} has been edited.", userId);
        return userMapper.toUserDto(userRepository.save(userToUpdate));
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getUserById(Long userId) {
        log.info("User with id {} data received.", userId);
        return userMapper.toUserDto(getUser(userId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers(Integer from, Integer size) {
        Pageable pageable = PageRequest.of(from, size);
        log.info("Received a list of all users with size of {}.", size);
        return userRepository.findAll(pageable).stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
        log.info("The user with id {} has been deleted.", userId);
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException(String.format("User with id=%d not found", userId)));
    }
}
