package com.project.bootcamp.service;

import com.project.bootcamp.exceptions.BusinessException;
import com.project.bootcamp.exceptions.NotFoundException;
import com.project.bootcamp.mapper.StockMapper;
import com.project.bootcamp.model.Stock;
import com.project.bootcamp.model.dto.StockDTO;
import com.project.bootcamp.repository.StockRepository;
import com.project.bootcamp.util.MessageUtils;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
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

    @Transactional
    public StockDTO update(StockDTO stockDTO) {
        // Busca por uma ação de mesmo nome, no mesmo dia, mas que não seja a passada
        Optional<Stock> optionalStock = repository.findByStockUpdate(stockDTO.getName(),stockDTO.getDate(), stockDTO.getId());
        // Se tiver alguma com mesmo nome nessa data, não é possível salvar, então uma exception é raised
        if (optionalStock.isPresent()){
            throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);
        }
        Stock stock = mapper.toEntity(stockDTO);
        repository.save(stock);
        return mapper.toDTO(stock);
    }

    @Transactional
    public List<StockDTO> findAll() {
        return mapper.toDTO(repository.findAll());
    }

    @Transactional
    public StockDTO findById(Long id) {
        return repository.findById(id).map(mapper::toDTO).orElseThrow(NotFoundException::new);
    }

    public StockDTO delete(Long id) {
        StockDTO stockDTO = this.findById(id);
        repository.deleteById(stockDTO.getId());
        return stockDTO;
    }

    public List<StockDTO> findByToday() {
        return repository.findByToday(LocalDate.now()).map(mapper::toDTO).orElseThrow(NotFoundException::new);
    }
}
