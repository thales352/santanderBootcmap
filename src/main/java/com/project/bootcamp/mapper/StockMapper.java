package com.project.bootcamp.mapper;

import com.project.bootcamp.model.Stock;
import com.project.bootcamp.model.dto.StockDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StockMapper {
    public StockDTO toDTO(Stock stock) {
        StockDTO stockDTO = new StockDTO();
        stockDTO.setId(stock.getId());
        stockDTO.setName(stock.getName());
        stockDTO.setPrice(stock.getPrice());
        stockDTO.setDate(stock.getDate());
        stockDTO.setVariation(stock.getVariation());
        return stockDTO;
    }
    public List<StockDTO> toDTO(List<Stock> stockList) {
        return stockList.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Stock toEntity(StockDTO stockDTO) {
        Stock stock = new Stock();
        stock.setId(stockDTO.getId());
        stock.setName(stockDTO.getName());
        stock.setPrice(stockDTO.getPrice());
        stock.setDate(stockDTO.getDate());
        stock.setVariation(stockDTO.getVariation());
        return stock;
    }
}
