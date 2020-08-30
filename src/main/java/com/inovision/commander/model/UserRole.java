package com.inovision.commander.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;

@Entity
@Table(name = "user_roles")
@Data
@EqualsAndHashCode(of = "id")
public class UserRole {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "userrole_seq")
    @SequenceGenerator(name = "userrole_seq", sequenceName = "user_roles_id_seq")
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    @Column(name = "role_name")
    private String role;

}
