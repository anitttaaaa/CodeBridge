package pl.zajavka.CodeBridge.infrastructure.security;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "code_bridge_role")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int id;

    @Column(name = "role")
    private String role;

    public RoleEntity(int id, String role) {
        this.id = id;
        this.role = role;
    }

    public RoleEntity() {
    }

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }
}
