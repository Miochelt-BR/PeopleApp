package com.mercadolibre.peopleapp.core.entity.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;

    @CPF(message = " Cpf invalido")
    private String cpf;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataDeNascimento;


    private String nomeDoPai;


    private String nomeDaMae;

    private String naturalidade;

}
