package com.udacity.jdnd.course3.critter.entity;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue
    private long id;

    @Nationalized
    private String name;

    @Nationalized //some phone number may need to include their country/state code using () or +
    private String phoneNumber;

    @Nationalized
    private String notes;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pet> pets;
}
