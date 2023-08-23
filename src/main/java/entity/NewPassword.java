package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "new_password")
public class NewPassword {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "current_password", nullable = false)
    private String currentPassword;
    @Column(name = "newPassword", nullable = false)
    private String newPassword;

    public NewPassword() {
    }

    public NewPassword(String currentPassword, String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }
}
