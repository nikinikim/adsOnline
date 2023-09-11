package com.example.adsonline.services.impl;



import com.example.adsonline.DTOs.NewPasswordDTO;
import com.example.adsonline.DTOs.RegisterReqDTO;
import com.example.adsonline.DTOs.UserDTO;
import com.example.adsonline.entity.Users;
import com.example.adsonline.exception.BusinessLogicException;
import com.example.adsonline.exception.NotFoundInDataBaseException;
import com.example.adsonline.mappers.UserMapper;
import com.example.adsonline.repository.UserRepository;
import com.example.adsonline.services.FileService;
import com.example.adsonline.services.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final FileService fileService;


    @Override
    @Transactional
    public UserDTO create(RegisterReqDTO registerReqDTO) {
        Users user = userMapper.fromRegisterReqDTO(registerReqDTO);
        return userMapper.toDto(userRepository.saveAndFlush(user));
    }

    @Override
    @Transactional(readOnly = true)
    public Users findUserByLogin(String username) {
        logger.debug("Find user entity by login, {}", username);
        return userRepository.findUsersByUserName(username)
                .orElseThrow(NotFoundInDataBaseException::new);
    }


    @Override
    public void updateUserPassword(NewPasswordDTO newPassword, Principal principal) {
        UserDetails userDetails = loadUserByUsername(principal.getName());
        if (!newPassword.getCurrentPassword().equals(userDetails.getPassword())) {
            throw new BusinessLogicException("Current password incorrect", HttpStatus.BAD_REQUEST);
        }
        Users userEntity = findUserByLogin(principal.getName());
        userEntity.setPassword(newPassword.getNewPassword());
        logger.debug("User password updated");
    }

    @Override
    @Transactional
    public void updateUserImage(Principal principal, MultipartFile multipartFile) {
        Users user = userRepository.findUsersByUserName(principal.getName()).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Пользователь '%s' не найден", principal.getName())));
        String ref;
        try {
            ref = fileService.upload(String.format("images-%s",user.getClass().getSimpleName()), String.valueOf(user.getId()), multipartFile.getOriginalFilename(), multipartFile.getBytes());
        } catch (IOException e) {
            throw new BusinessLogicException("Файл не загружен", HttpStatus.BAD_REQUEST);
        }
        user.setImage(ref);
        userRepository.saveAndFlush(user);

    }

    @Override
    public UserDTO getUser(Principal principal) {
        return userMapper.toDto(
                findUserByLogin(
                        principal.getName()));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isExist(String userName) {
        return userRepository.existsUsersByUserName(userName);

    }

    @Override
    @Transactional
    public UserDTO updateUser(UserDTO userDTO, Principal principal) {
        Users user = userRepository.findUsersByUserName(principal.getName()).orElseThrow();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPhone(userDTO.getPhone());
        return userMapper.toDto(userRepository.saveAndFlush(user));
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findUsersByUserName(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Пользователь '%s' не найден", username)));
        return new User(
                user.getUserName(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole().toString()))
        );

    }
}


