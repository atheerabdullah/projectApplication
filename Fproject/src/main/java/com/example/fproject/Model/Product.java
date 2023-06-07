package com.example.fproject.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotEmpty
    @Column(columnDefinition = "varchar(25) not null")
    private String name;

   @NotNull
    @Column(columnDefinition = "varchar(25) not null")
    private Integer price;



    @OneToMany(cascade = CascadeType.ALL,mappedBy = "product")
    private Set<MyOrder> orders;



}
