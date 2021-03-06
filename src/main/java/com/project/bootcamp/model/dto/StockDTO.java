package com.project.bootcamp.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class StockDTO {
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @DecimalMin(value = "0.00")
    //aceita 6 casas antes da virgula e 2 apos
    @Digits(integer = 6, fraction = 2)
    private Double price;

    @NotNull
    //aceita 3 casas antes da virgula e 2 apos
    @Digits(integer = 3, fraction = 2)
    private Double variation;

    @NotNull
    //converte a data que vem em JSON para o padrao brasileiro
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate date;

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

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
