package ru.javamentor.predProject7.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles", schema = "db_example")
public class Role {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rolename")
    private String rolename;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "roles")
    private Set<User> user;

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", rolename='" + rolename + '\'' +
                ", user=" + user +
                '}';
    }
}