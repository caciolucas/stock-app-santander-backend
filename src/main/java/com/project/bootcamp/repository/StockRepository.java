package com.project.bootcamp.repository;

import com.project.bootcamp.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findByNameAndDate(String name, LocalDate date);

    // Busca por uma ação de mesmo nome, no mesmo dia, mas que não seja a passada
    @Query("SELECT stock FROM Stock stock WHERE stock.name = :name AND stock.date = :date AND stock.id <> :id")
    Optional<Stock> findByStockUpdate(String name, LocalDate date, Long id);

    // CURRENT_DATE específico do Postgre
    // @Query("SELECT stock from Stock stock WHERE stock.date = CURRENT_DATE ")
    @Query("SELECT stock from Stock stock WHERE stock.date = :date ")
    Optional<List<Stock>> findByToday(LocalDate date);
}
