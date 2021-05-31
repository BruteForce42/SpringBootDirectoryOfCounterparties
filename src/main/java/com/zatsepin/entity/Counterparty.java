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

    @NotNull(message = "Поле не должно быть пустым")
    @Size(min = 3, max = 20, message = "Наименование должно иметь длину от 3 до 20 символов")
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull(message = "Поле не должно быть пустым")
    @Size(min = 10, max = 12, message = "ИНН должен содержать 10 цифр для физ.лиц и 12 цифр для юр.лиц")
    @Column(name = "inn", nullable = false)
    private String inn;

    @NotNull(message = "Поле не должно быть пустым")
    @Size(min = 9, max = 9, message = "КПП должен содержать 9 цифр")
    @Column(name = "kpp", nullable = false)
    private String kpp;

    @NotNull(message = "Поле не должно быть пустым")
    @Size(min = 20, max = 20, message = "Номер счета должен содержать 20 цифр")
    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @NotNull(message = "Поле не должно быть пустым")
    @Size(min = 9, max = 9, message = "БИК должен содержать 9 цифр")
    @Column(name = "bic", nullable = false)
    private String bic;

    @Size(max = 255, message = "Комментарий должен быть не более 255 символов")
    @Column(name = "comment")
    private String comment;
}
