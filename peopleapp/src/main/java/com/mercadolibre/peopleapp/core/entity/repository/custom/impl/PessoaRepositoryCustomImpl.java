package com.mercadolibre.peopleapp.core.entity.repository.custom.impl;

import com.mercadolibre.peopleapp.core.entity.domain.Pessoa;
import com.mercadolibre.peopleapp.core.entity.repository.custom.PessoaRepositoryCustom;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class PessoaRepositoryCustomImpl implements PessoaRepositoryCustom {
    @PersistenceContext
    EntityManager em;

    @Override
    public List<Pessoa> findByAtributos(String nome, String cpf, Date dataDeNascimento) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Pessoa> cq = cb.createQuery(Pessoa.class);
        Root<Pessoa> pessoa = cq.from(Pessoa.class);
        List<Predicate> predicates = new ArrayList<>();
        if (nome != null && !nome.isBlank()) {
            predicates.add(cb.like(pessoa.get("name"), "%" + nome + "%"));

        }
        if (cpf != null && !cpf.isBlank()) {
            predicates.add(cb.equal(pessoa.get("cpf"), cpf));

        }
        if (dataDeNascimento != null) {
            predicates.add(cb.equal(pessoa.get("dataDeNascimento"), dataDeNascimento));

        }
        cq.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(cq).getResultList();

    }
}
