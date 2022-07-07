package org.acme;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter 
@Table(name = "users")
public class User extends PanacheEntityBase {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    @Column(name = "username", length = 50, nullable = false)
    private String username;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "password", length = 8, nullable = false)
    private String password;

    @Column(name = "firstname", length = 50, nullable = false)
    private String firstName;

    @Column(name = "lastname", length = 50, nullable = false)
    private String lastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "birthDate")
    private Date birthDate;

    @Column(name = "country")
    private String country;

    public User() {
    }

    public User(String username, String email, String password, String firstName, String lastName, Date birthDate, String country) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.country = country;
    }

}