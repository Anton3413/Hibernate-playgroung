package com.anton31413.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity()
@Table(name = "users", schema = "public")
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@ToString(of = {"id","username","firstName","lastName","birthDate"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Integer age;
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne(optional = false,fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "company_id")
    private Company company;
}
