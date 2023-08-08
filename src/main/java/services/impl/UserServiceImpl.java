package services.impl;

import DTOs.*;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import repository.UserRepository;
import services.UserService;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDTO mapToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setCity(user.getCity());
        userDTO.setPhone(user.getPhone());
        userDTO.setImage(user.getImage());
        userDTO.setRegDate(user.getRegDate());

        return userDTO;
    }

    @Override
    public User mapToEntity(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setCity(userDTO.getCity());
        user.setPhone(userDTO.getPhone());
        user.setImage(userDTO.getImage());
        user.setRegDate(userDTO.getRegDate());

        return user;
    }

    @Override
    public Optional<User> getUserById(Long id) {
       return userRepository.findById(id);
    }

    @Override
    public User updateUser(User user) {


        return user;
    }

    @Override
    public void setPassword(PasswordDTO newPassword) {
        System.out.println("Пароль успешно изменен");
    }

    @Override
    public void updateUserImage(Long id, MultipartFile multipartFile) {
        String imageUser = "image.jpg";
        try {
            File imageFile = new File(imageUser);
            byte[] imageData = Files.readAllBytes(imageFile.toPath());
            ImageIcon imageIcon = new ImageIcon(imageData);
            JLabel imageLabel = new JLabel(imageIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
