package com.project.bootcamp.service;

import com.project.bootcamp.exceptions.BusinessException;
import com.project.bootcamp.exceptions.NotFoundException;
import com.project.bootcamp.mapper.StockMapper;
import com.project.bootcamp.model.Stock;
import com.project.bootcamp.model.dto.StockDTO;
import com.project.bootcamp.repository.StockRepository;
import com.project.bootcamp.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

//o service decide se tem ou nao que chamar o repository
//indica pro spring que ele vai gerenciar as dependencias
@Service
public class StockService {

    //pra que o StockService conheca o StockRepository
    //autowired pra que o spring gerencie as instancias
    @Autowired
    private StockRepository repository;

    //pra que o StockService conheca o StockMapper
    //autowired pra que o spring gerencie as instancias
    @Autowired
    private StockMapper mapper;

    //o tipo de objeto retornado tem que atender ao definido no controller
    //transactional gerencia a abertura e fechamento da conexao com o banco de dados apos a transacao
    //transactional tambem gerencia rollback em caso de falha
    @Transactional
    public StockDTO save(StockDTO dto) {
        //procura se ja existe stock por nome e data
        Optional<Stock> optionalStock = repository.findByNameAndDate(dto.getName(),dto.getTrandingDate());
        if(optionalStock.isPresent()){
            //lanca uma exeception
            throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);
        }


        //converte o objeto recebido para Stock
        Stock active = mapper.toEntity(dto);
        System.out.println("DATA SERVICE: "+active.getTrandingDate());
        repository.save(active);
        //converte stock para stockDTO e retorna já com o ID cadastrado
        return mapper.toDto(active);
    }

    public StockDTO update(StockDTO dto) {
        //procura se ja existe stock por nome e data
        Optional<Stock> optionalStock = repository.findByStockUpdate(dto.getName(),dto.getTrandingDate(), dto.getId());
        if(optionalStock.isPresent()){
            //lanca uma exeception
            throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);
        }

        //converte o objeto recebido para Stock
        Stock stock = mapper.toEntity(dto);
        repository.save(stock);
        //converte stock para stockDTO e retorna já com o ID cadastrado
        return mapper.toDto(stock);
    }

    @Transactional
    public StockDTO delete(Long id) {
        //verifica se existe o id no banco
        //armazena o StockDTO retornado
        StockDTO dto = this.findById(id);
        //exclui o dto pelo id
        repository.deleteById(dto.getId());
        //retorna o dto
        return dto;
    }

    //@annotation significa transacao somente leitura, somente select, nao vai alterar nada
    @Transactional(readOnly = true)
    public List<StockDTO> findAll() {
        return mapper.toDto(repository.findAll());
    }

    //@annotation significa transacao somente leitura, somente select, nao vai alterar nada
    @Transactional(readOnly = true)
    public StockDTO findById(Long id) {
        //.map separa o entity para converter para DTO toDto
        //caso nao encontre lanca a excecao NotFound
        return repository.findById(id).map(mapper::toDto).orElseThrow(NotFoundException::new);
    }

    //@annotation significa transacao somente leitura, somente select, nao vai alterar nada
    @Transactional(readOnly = true)
    //retorna as stockdto de hoje
    public List<StockDTO> findByToday() {
        //.map separa o entity para converter para DTO toDto
        //caso nao encontre lanca a excecao NotFound
        return repository.findByToday(LocalDate.now()).map(mapper::toDto).orElseThrow(NotFoundException::new);
    }
}
