package services.impl;

<<<<<<< HEAD:src/main/java/com/example/adsonline/services/impl/UserServiceImpl.java
import DTOs.NewPasswordDTO;
import DTOs.UserDTO;
import services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
=======
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import repository.UserRepository;


import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
>>>>>>> Kristina_feature_23.08:src/main/java/services/impl/UserServiceImpl.java

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
        userDTO.setImageRef(user.getImageRef());
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
        user.setImageRef(userDTO.getImageRef());
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
    public void setPassword(NewPasswordDTO newPassword) {
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
