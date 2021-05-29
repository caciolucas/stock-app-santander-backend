package com.project.bootcamp.mapper;

import com.project.bootcamp.model.Stock;
import com.project.bootcamp.model.dto.StockDTO;
import org.springframework.stereotype.Component;

@Component
public class StockMapper {
    public Stock toEntity(StockDTO stockDTO) {
        Stock stock = new Stock();
        stock.setId(stockDTO.getId());
        stock.setPrice(stockDTO.getPrice());
        stock.setDate(stockDTO.getDate());
        stock.setVariation(stockDTO.getVariation());
        stock.setName(stockDTO.getName());
        return stock;
    }

    public StockDTO toDTO(Stock stock) {
        StockDTO stockDTO = new StockDTO();
        stockDTO.setId(stock.getId());
        stockDTO.setPrice(stock.getPrice());
        stockDTO.setDate(stock.getDate());
        stockDTO.setVariation(stock.getVariation());
        stockDTO.setName(stock.getName());
        return stockDTO;
    }
}
