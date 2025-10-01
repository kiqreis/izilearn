package br.com.izilearn.izilearn_application.core.domain.model;

import br.com.izilearn.izilearn_application.core.domain.enums.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(nullable = false, length = 180)
    private String email;

    @Column(nullable = false, length = 64)
    private String hashPassword;

    @Column(nullable = false, length = 255)
    private String urlImage;

    @Column(nullable = false, length = 11)
    private String cellphoneNumber;

    @CreationTimestamp
    private LocalDateTime createdAt;

//    private LocalDateTime birthDate;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus = UserStatus.PENDING;

    @OneToMany(mappedBy = "user")
    List<Profile> profiles;

}
