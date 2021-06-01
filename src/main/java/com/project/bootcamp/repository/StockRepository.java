package com.project.bootcamp.repository;

import com.project.bootcamp.model.Stock;
import com.project.bootcamp.model.dto.StockDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

//essa anotation ajuda o spring a gerenciar as instancias
@Repository
//esse repository é do Stock, tipo de ID é do tipo Long
//ele extende de JpaRepository que já implementa alguns metodos de CRUD
public interface StockRepository extends JpaRepository<Stock, Long> {


    Optional<Stock> findByNameAndDate(String name, LocalDate date);

    @Query("SELECT stock " +
            "FROM Stock stock "+
            "WHERE stock.name = :name AND stock.date = :date AND stock.id <> :id ")
    Optional<Stock> findByStockUpdate(String name, LocalDate date, Long id);

    @Query("SELECT stock " +
            "FROM Stock stock "+
            "WHERE stock.date = :date")
    Optional<List<Stock>> findByToday(LocalDate date);
}
