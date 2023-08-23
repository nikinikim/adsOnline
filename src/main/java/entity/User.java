package entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "first_Name", nullable = false)
    private String firstName;
    @Column(name = "last_Name", nullable = false)
    private String lastName;
    @Column(name = "phone", nullable = false)
    private String phone;
    @Column(name = "registration_Date", nullable = false)
    private String regDate;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "image", nullable = false)
    private String image;
    @ManyToOne
    @JoinColumn(name = "new_password_id")
    private NewPassword newPassword;
    @ManyToOne
    @JoinColumn(name = "register_req_id")
    private RegisterReq registerReq;

    public User(String email, String firstName, String lastName, String phone, String regDate, String city, String image,
                NewPassword newPassword, RegisterReq registerReq) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.regDate = regDate;
        this.city = city;
        this.image = image;
        this.newPassword = newPassword;
        this.registerReq = registerReq;
    }

    public User() {
    }
}
