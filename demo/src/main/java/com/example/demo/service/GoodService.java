package com.example.demo.service;

import com.example.demo.model.Good;
import com.example.demo.repository.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GoodService {

    private final GoodRepository goodRepository;

    @Autowired
    public GoodService(GoodRepository goodRepository) {
        this.goodRepository = goodRepository;
    }

    public Good saveGood(Good good) {
        return goodRepository.save(good);
    }

    public List<Good> getAllGoods() {
        return goodRepository.findAll();
    }

    public Optional<Good> getGoodById(Long id) {
        return goodRepository.findById(id);
    }

    public Good updateGood(Long id, Good updatedGood) {
        Good good = goodRepository.findById(id).orElseThrow(() -> new RuntimeException("Good not found"));
        good.setName(updatedGood.getName());
        return goodRepository.save(good);
    }

    public void deleteGood(Long id) {
        goodRepository.deleteById(id);
    }
}

