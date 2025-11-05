package com.anton31413.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "company")
@Data
@EqualsAndHashCode(of = {"id","name"})
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {

    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "company")
    private List<User> users = new ArrayList<>();


    public void addUser(User user){
        users.add(user);
        user.setCompany(this);
    }
}
