package pl.zajavka.CodeBridge.infrastructure.security;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;

import java.util.Set;


@Entity
@Table(name = "code_bridge_user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(name = "user_name")
    @Length(min = 5)
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private Boolean active;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "code_bridge_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles;

    public UserEntity(int id, String userName, String email, String password,
                      Boolean active, Set<RoleEntity> roles) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.active = active;
        this.roles = roles;
    }

    public UserEntity() {
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getActive() {
        return active;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }
}
