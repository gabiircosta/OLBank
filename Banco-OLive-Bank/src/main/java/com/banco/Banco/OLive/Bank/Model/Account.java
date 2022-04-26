package com.banco.Banco.OLive.Bank.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "account_tb")
@Data
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

    @ManyToOne
    @JsonIgnoreProperties ("account")
    private Customer customer;
}
