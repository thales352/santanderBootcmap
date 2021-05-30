package com.project.bootcamp.service;

import com.project.bootcamp.exceptions.BusinessException;
import com.project.bootcamp.exceptions.NotFoundException;
import com.project.bootcamp.mapper.StockMapper;
import com.project.bootcamp.model.Stock;
import com.project.bootcamp.model.dto.StockDTO;
import com.project.bootcamp.repository.StockRepository;
import com.project.bootcamp.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {
    @Autowired
    private  StockRepository repository;
    @Autowired
    private  StockMapper stockMapper;

    @Transactional(readOnly = true)
    public List<StockDTO> findAll() {
        return stockMapper.toDTO(repository.findAll());
    }

    @Transactional
    public StockDTO save(StockDTO stockDTO) {
        Optional<Stock> optionalStock = repository.findByNameAndDate(stockDTO.getName(),stockDTO.getDate());
        if(optionalStock.isPresent()){
            throw new BusinessException(MessageUtils.STOCK_ALREDY_EXISTS);
        }
        Stock stock = stockMapper.toEntity(stockDTO);
        repository.save(stock);
        return stockMapper.toDTO(stock);
    }
    @Transactional
    public StockDTO update(StockDTO stockDTO) {
        Optional<Stock> optionalStock = repository.findByStockUpdate(stockDTO.getName(),stockDTO.getDate(),stockDTO.getId());
        if(optionalStock.isPresent()){
            throw new BusinessException(MessageUtils.STOCK_ALREDY_EXISTS);
        }
        Stock stock = stockMapper.toEntity(stockDTO);
        repository.save(stock);
        return stockMapper.toDTO(stock);
    }
    @Transactional
    public StockDTO findById(Long id) {
        return repository.findById(id).map(stockMapper::toDTO).orElseThrow(NotFoundException::new);
    }
    @Transactional
    public StockDTO deleteById(Long id) {
        StockDTO dto = findById(id);
        repository.deleteById(dto.getId());
        return dto;
    }
    @Transactional
    public List<StockDTO> findByToday() {
        return repository.findByToday(LocalDate.now()).map(stockMapper::toDTO).orElseThrow(NotFoundException::new);
    }
}
