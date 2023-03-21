package com.mercadolibre.peopleapp.router.impl;

import com.mercadolibre.peopleapp.core.entity.domain.Pessoa;
import com.mercadolibre.peopleapp.core.entity.repository.PessoaRepository;
import com.mercadolibre.peopleapp.core.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/pessoa")
public class PessoaRouter {


    @Autowired
    private PessoaService pessoaService;


    @Autowired
    private PessoaRepository pessoaRepository;


    @GetMapping
    public ResponseEntity<List<Pessoa>> buscarTudo() {
        return ResponseEntity.ok(pessoaRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Pessoa> cadastrar(@RequestBody Pessoa pessoa){
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.cadastrar(pessoa));

    }

   @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){

       return pessoaRepository.findById(id)
                .map(var -> {
                    pessoaService.deletar(id);
                    return ResponseEntity.status(HttpStatus.FOUND).build();
                })
               .orElse(ResponseEntity.notFound().build());

    }



}
