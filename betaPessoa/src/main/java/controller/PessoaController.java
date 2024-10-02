package controller;

import dto.PessoaDto;
import model.Pessoa;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.PessoaRepository;

import java.util.UUID;

@RestController
public class PessoaController {
    @Autowired
    private PessoaRepository pessoaRepository;

    @PostMapping("/Pessoa")
    public ResponseEntity<Pessoa> createPessoa(@RequestBody PessoaDto pessoaDto) {
        Pessoa pessoa = new Pessoa();
        BeanUtils.copyProperties(pessoaDto, pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaRepository.save(pessoa));
    }

    @GetMapping("/Pessoa/{id}")
    public ResponseEntity<Pessoa> getPessoaById(@PathVariable UUID id) {
        Pessoa pessoa = pessoaRepository.findById(id).orElse(null);
        if (pessoa == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(pessoa);
    }
}

