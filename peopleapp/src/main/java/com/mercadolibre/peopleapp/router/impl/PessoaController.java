package com.mercadolibre.peopleapp.router.impl;

import com.mercadolibre.peopleapp.core.entity.domain.Pessoa;
import com.mercadolibre.peopleapp.core.entity.repository.PessoaRepository;
import com.mercadolibre.peopleapp.core.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



@RestController
@RequestMapping("/pessoa")
public class PessoaController {


    @Autowired
    private PessoaService pessoaService;


    @Autowired
    private PessoaRepository pessoaRepository;


    @GetMapping
    public ResponseEntity<List<Pessoa>> buscarTudo() {
        return ResponseEntity.ok(pessoaRepository.findAll());
    }

    @GetMapping("/buscarnome")
    public ResponseEntity<List<Pessoa>> buscarName(@RequestParam(name = "name") String name) {
        return ResponseEntity.ok(pessoaRepository.findByNameContaining(name));
    }

    @GetMapping("/buscarcpf")
    public ResponseEntity<List<Pessoa>> buscarcpf(@RequestParam(name = "cpf") String cpf) {
        return ResponseEntity.ok(pessoaRepository.findByCpfContaining(cpf));


    }

    @GetMapping("/buscarnascimento")
    public ResponseEntity<List<Pessoa>> buscarNascimento(Date dataDeNascimento) {
        return ResponseEntity.ok(pessoaRepository.findByDataDeNascimento(dataDeNascimento));

    }

    @GetMapping("/buscarpersonalizada")
    public ResponseEntity<List<Pessoa>> buscarPersozalizada(@RequestParam(name = "name", required = false) String name,
                                                            @RequestParam(name = "cpf", required = false) String cpf,
                                                            @RequestParam(name = "dataDeNascimento", required = false)  String  dataDeNascimento) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date dataFormatada = null;
        if (dataDeNascimento!=null){
            try {
                dataFormatada = format.parse(dataDeNascimento);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        return ResponseEntity.ok(pessoaRepository.findByAtributos(name, cpf, dataFormatada));

    }


    @PostMapping
    public ResponseEntity<Pessoa> cadastrar(@RequestBody @Valid Pessoa pessoa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.cadastrar(pessoa));

    }

    @PutMapping
    public ResponseEntity<Pessoa> atualizar(@RequestBody Pessoa pessoa) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.atualizar(pessoa));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {

        return pessoaRepository.findById(id)
                .map(var -> {
                    pessoaService.deletar(id);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                })
                .orElse(ResponseEntity.notFound().build());

    }


}
