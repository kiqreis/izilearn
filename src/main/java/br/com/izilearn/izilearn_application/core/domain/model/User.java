package br.com.izilearn.izilearn_application.core.domain.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

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

}
