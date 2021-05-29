package com.project.bootcamp.service;

import com.project.bootcamp.exceptions.BusinessException;
import com.project.bootcamp.mapper.StockMapper;
import com.project.bootcamp.model.Stock;
import com.project.bootcamp.model.dto.StockDTO;
import com.project.bootcamp.repository.StockRepository;
import com.project.bootcamp.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository repository;

    @Autowired
    private StockMapper mapper;

    @Transactional
    public StockDTO save(StockDTO stockDTO) {
        Optional<Stock> optionalStock = repository.findByNameAndDate(stockDTO.getName(),stockDTO.getDate());
        if (optionalStock.isPresent()){
            throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);
        }
        Stock stock = mapper.toEntity(stockDTO);
        repository.save(stock);
        return mapper.toDTO(stock); // Retorno o stock convertido para que venha com o id
    }

//    public List<StockDTO> findAll() {
//
//        return mapper.toEntities(repository.findAll());
//    }
}
