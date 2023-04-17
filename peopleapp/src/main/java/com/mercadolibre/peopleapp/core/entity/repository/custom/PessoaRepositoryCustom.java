package com.mercadolibre.peopleapp.core.entity.repository.custom;

import com.mercadolibre.peopleapp.core.entity.domain.Pessoa;

import java.util.Date;
import java.util.List;

public interface PessoaRepositoryCustom {
    List<Pessoa> findByAtributos(String nome, String cpf, Date dataDeNascimento);

    }
