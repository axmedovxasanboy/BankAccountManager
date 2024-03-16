package uz.bank.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Admin {
    private Integer id;
    private String username;
    private String password;

    public Admin() {
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
