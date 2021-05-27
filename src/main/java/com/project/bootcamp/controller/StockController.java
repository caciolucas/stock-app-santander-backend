package com.project.bootcamp.controller;

import com.project.bootcamp.model.dto.StockDTO;
import org.apache.coyote.Response;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.print.attribute.standard.Media;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/stock")
public class StockController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockDTO> save(@Valid @RequestBody StockDTO dto){
        return ResponseEntity.ok(dto);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockDTO> update(@Valid @RequestBody StockDTO dto){
        return ResponseEntity.ok(dto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StockDTO>> findAll(){
        List<StockDTO> list = new ArrayList<>();
        StockDTO stock1 = new StockDTO();
        stock1.setId(1L);
        stock1.setName("Magazine Luiza");
        stock1.setPrice(100D);
        stock1.setVariation(10D);
        stock1.setDate(LocalDate.now());
        list.add(stock1);

        StockDTO stock2 = new StockDTO();
        stock2.setId(2L);
        stock2.setName("Ponto Frio");
        stock2.setPrice(200D);
        stock2.setVariation(5D);
        stock2.setDate(LocalDate.now());
        list.add(stock2);

        return ResponseEntity.ok(list);

    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockDTO> findById(@PathVariable Long id){
        List<StockDTO> list = new ArrayList<>();
        StockDTO stock1 = new StockDTO();
        stock1.setId(1L);
        stock1.setName("Magazine Luiza");
        stock1.setPrice(100D);
        stock1.setVariation(10D);
        stock1.setDate(LocalDate.now());
        list.add(stock1);

        StockDTO stock2 = new StockDTO();
        stock2.setId(2L);
        stock2.setName("Ponto Frio");
        stock2.setPrice(200D);
        stock2.setVariation(5D);
        stock2.setDate(LocalDate.now());
        list.add(stock2);

        StockDTO selectedStock = list.stream().filter(stock -> stock.getId().compareTo(id) == 0).findFirst().get();

        return ResponseEntity.ok(selectedStock);
    }

}
