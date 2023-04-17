package com.mercadolibre.peopleapp.core.entity.repository;

import com.mercadolibre.peopleapp.core.entity.domain.Pessoa;
import com.mercadolibre.peopleapp.core.entity.repository.custom.PessoaRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import java.util.Date;
import java.util.List;


public interface PessoaRepository extends JpaRepository<Pessoa, Long>, PessoaRepositoryCustom {

    Pessoa findByCpf(String cpf);


    List<Pessoa> findByNameContaining(@Param("name") String name);

    List<Pessoa> findByCpfContaining(@Param("cpf") String cpf);

    List<Pessoa> findByDataDeNascimento(@Param("dataDeNascimento") Date dataDeNascimento);




}
