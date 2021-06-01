package com.project.bootcamp.controller;

import com.project.bootcamp.model.dto.StockDTO;
import com.project.bootcamp.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

//aceita requisicoes de origens distintas
@CrossOrigin
//servidor que segue as questoes de REST
@RestController
//quem quiser acessar este controlador tem que ser atraves dessa URL
@RequestMapping(value = "/stock")
public class StockController {

    //o service decide se tem ou nao que chamar o repository
    //autowired spring decide quando instanciar ou nao o service
    @Autowired
    private StockService service;

    //tem que me enviar atraves de um metodo POST
    //vou consumir esses dados no formato JSON e devolver no formato JSON
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    //estou dizendo que vou retornar pra quem me chamou o objeto que salvei
    //quem está me requisitando vai ter que me enviar um StockDTO atraves de um body
    public ResponseEntity<StockDTO> save(@Valid @RequestBody StockDTO dto){
        System.out.println("DATA CONTROLLER: "+dto.getDate());
        //retorna o status 200 e o objeto recebido
        return ResponseEntity.ok(service.save(dto));
    }

    //tem que me enviar atraves de um metodo PUT
    //vou consumir esses dados no formato JSON e devolver no formato JSON
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    //estou dizendo que vou retornar pra quem me chamou o objeto que salvei
    //quem está me requisitando vai ter que me enviar um StockDTO com as alteracoes atraves de um body
    public ResponseEntity<StockDTO> update(@Valid @RequestBody StockDTO dto){
        //retorna o status 200 e o objeto recebido
        return ResponseEntity.ok(service.update(dto));
    }

    //tem que me solicitar atraves de um metodo GET
    //vou responder no formato JSON
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    //retorna uma lista de Stock DTO
    public ResponseEntity<List<StockDTO>> findAll(){
        //retorna o status 200 e a lista
        return ResponseEntity.ok(service.findAll());
    }

    //necessario solicitar usando o metodo GET passando o id na URL
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    //retorna uma stock pelo seu ID
    //PathVariable extrai o id da URL
    public ResponseEntity<StockDTO> findById(@PathVariable Long id){
        //retorna a stock que possui o id recebido
        return ResponseEntity.ok(service.findById(id));

    }

    //necessario solicitar usando o metodo DELETE passando o id na URL
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    //exclui uma stock pelo seu ID
    //PathVariable extrai o id da URL
    public ResponseEntity<StockDTO> delete(@PathVariable Long id){
        return ResponseEntity.ok(service.delete(id));
    }

    //retorna em JSON as stock de hoje atraves do endpoint today
    @GetMapping(value = "/today", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StockDTO>> findByToday(){
        return ResponseEntity.ok(service.findByToday());
    }


}
