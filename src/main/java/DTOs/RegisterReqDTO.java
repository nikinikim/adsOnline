package DTOs;

import lombok.Data;

@Data
public class RegisterReqDTO {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private DTOs.RoleDTO roleDTO;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public DTOs.RoleDTO getRoleDTO() {
        return roleDTO;
    }

    public void setRoleDTO