package com.banco.Banco.OLive.Bank.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "customer_tb")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Customer {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "customer_id")
    private Long id;

    @Column (name = "customer_name")
    private String name;

    @Column (name = "customer_cpf")
    private String cpf;

    public Customer(Long id, String name, String cpf) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
    }

    @OneToMany (mappedBy = "customer")
    @JsonIgnoreProperties ("customer")
    private List<Account> balance;

}
