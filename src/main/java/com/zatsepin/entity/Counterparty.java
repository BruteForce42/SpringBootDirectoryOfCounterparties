package com.zatsepin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "counterparties")
public class Counterparty implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 20)
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Size(min = 10, max = 12)
    @Column(name = "inn", nullable = false)
    private String inn;

    @NotNull
    @Size(min = 9, max = 9)
    @Column(name = "kpp", nullable = false)
    private String kpp;

    @NotNull
    @Size(min = 20, max = 20)
    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @NotNull
    @Size(min = 9, max = 9)
    @Column(name = "bic", nullable = false)
    private String bic;

    @Size(max = 255)
    @Column(name = "comment")
    private String comment;
}
