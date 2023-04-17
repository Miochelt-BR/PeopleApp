package com.mercadolibre.peopleapp.core.service;

import com.mercadolibre.peopleapp.core.entity.domain.Pessoa;
import com.mercadolibre.peopleapp.core.entity.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;



@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa cadastrar(Pessoa pessoa) {

        if (pessoa.getNomeDoPai().isEmpty() && pessoa.getNomeDaMae().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (pessoaRepository.findByCpf(pessoa.getCpf()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            return pessoaRepository.save(pessoa);
        }
    }


    public Pessoa atualizar(@RequestBody Pessoa pessoa) {
        Pessoa pessoaExistente = pessoaRepository.findById(pessoa.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada", null));

        // Verifica se o CPF já está cadastrado para outra pessoa
        if (!pessoa.getCpf().equals(pessoaExistente.getCpf())
                && pessoaRepository.findByCpf(pessoa.getCpf()) != null) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF já cadastrado para outra pessoa!", null);
        }
        if (pessoa.getNomeDoPai().isEmpty() && pessoa.getNomeDaMae().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        /** Não permite alteração do nome e CPF da pessoa*/

        pessoa.setCpf(pessoaExistente.getCpf());

        return pessoaRepository.save(pessoa);
    }

    public void deletar(@PathVariable Long id) {

        Optional<Pessoa> pessoa = pessoaRepository.findById(id);

        if (pessoa.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada", null);

        pessoaRepository.deleteById(id);
    }

}