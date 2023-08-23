package entity;

import DTOs.RoleDTO;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "register_req")
public class RegisterReq {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "first_Name", nullable = false)
    private String firstName;
    @Column(name = "last_Name", nullable = false)
    private String lastName;
    @Column(name = "phone", nullable = false)
    private String phone;
    @Column(name = "role", nullable = false)
    private RoleDTO roleDTO;

    public RegisterReq() {
    }

    public RegisterReq(String username, String password, String firstName, String lastName, String phone, RoleDTO roleDTO) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.roleDTO = roleDTO;
    }
}
