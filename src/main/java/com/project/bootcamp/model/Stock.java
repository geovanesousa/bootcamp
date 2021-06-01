package com.project.bootcamp.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


//corresponde a tabela tb_stock do banco de dados
@Entity
@Table(name = "tb_stock")
public class Stock {
    //o nome da variavel nao precisa ser igual ao nome da coluna da tabela, isso é definido usando @Column
    @Id
    //se o banco fizer o incremento usar GenerationType.Entity se for o JPA usar GenerationType.AUTO
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "variation")
    private Double variation;

    @Column(name = "date")
    private LocalDate trandingDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getVariation() {
        return variation;
    }

    public void setVariation(Double variation) {
        this.variation = variation;
    }

    public LocalDate getTrandingDate() {
        return trandingDate;
    }

    public void setTrandingDate(LocalDate trandingDate) {
        this.trandingDate = trandingDate;
    }
}
