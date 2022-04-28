package com.banco.Banco.OLive.Bank.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "account_tb")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Account {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "account_id")
    private Long id;

    @Column (name = "account_name")
    private String name;

    @Column (name = "account_balance")
    private double balance;

    public Account(Long id, String name, double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    @ManyToOne
    @JsonIgnoreProperties ("account")
    private Customer customer;

}
