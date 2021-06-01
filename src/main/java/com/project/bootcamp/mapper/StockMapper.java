package com.project.bootcamp.mapper;

import com.project.bootcamp.model.Stock;
import com.project.bootcamp.model.dto.StockDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

//mapeamento - transformacao de uma coisa pra outra
//component diz pro spring gerenciar as instancias
@Component
public class StockMapper {

    //transforma DTO em Entity
    public Stock toEntity(StockDTO dto) {
        Stock stock = new Stock();
        stock.setId(dto.getId());
        stock.setName(dto.getName());
        stock.setTrandingDate(dto.getTrandingDate());
        stock.setPrice(dto.getPrice());
        stock.setVariation(dto.getVariation());

        System.out.println("DATA MAPPER: "+stock.getTrandingDate());
        return stock;
    }

    //transforma entity em DTO
    public StockDTO toDto(Stock active) {
        StockDTO stockDTO = new StockDTO();
        stockDTO.setId(active.getId());
        stockDTO.setName(active.getName());
        stockDTO.setPrice(active.getPrice());
        stockDTO.setVariation(active.getVariation());
        stockDTO.setTrandingDate(active.getTrandingDate());
        return stockDTO;
    }

    //transforma uma lista de entity em uma lista de DTO
    public List<StockDTO> toDto(List<Stock> listStock){
        //.stream percorre a lista | .map separa entity por entity
        // this::toDto chama o metodo toDto(Stock stock) | .collect adiciona cada DTO a lista para retornar
        return listStock.stream().map(this::toDto).collect(Collectors.toList());
    }

}
