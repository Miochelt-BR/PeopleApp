package com.mercadolibre.peopleapp.core.entity.repository;


import com.mercadolibre.peopleapp.core.entity.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Pessoa findByCpf(String cpf);

    @Query(value = "select p from Pessoa p where p.name like %?1%")
    List<Pessoa> buscarPorNome(String name);

    @Query(value = "select p from Pessoa p where p.cpf like %?1%")
    List<Pessoa> buscarPorCpf(String cpf);

    @Query(value = "select p from Pessoa p where p.cpf like %?1%")
    List<Pessoa> buscarPornascimento(Date dataDeNascimento);

    

    List<Pessoa> findByNameContainingOrCpfContainingOrDataDeNascimentoContaining(@Param("name") String name, @Param("cpf") String cpf, @Param("date") Date dataDeNascimento);
}
