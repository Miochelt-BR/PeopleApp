package com.mercadolibre.peopleapp.core.entity.repository;


import com.mercadolibre.peopleapp.core.entity.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Pessoa findByCpf(String cpf);
}
