package com.project.bootcamp.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_stock")
public class Stock {

    @Id // Primary Key no banco
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Ou GenerationType.IDENTITY se a coluna no banco tiver AUTO_INCREMENT
    private Long id;

    private String name;

    private Double price;

    private Double variation;

    public Stock(){

    }
    public Stock(String name, Double price, Double variation, LocalDate date) {
        this.name = name;
        this.price = price;
        this.variation = variation;
        this.date = date;
    }

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
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
