package com.zhomeo.zilani.service;


import com.zhomeo.zilani.entity.Buyer;
import com.zhomeo.zilani.repository.BuyerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BuyerService {
    private final Logger logger = LoggerFactory.getLogger(BuyerService.class);

    private BuyerRepository buyerRepository;

    public BuyerService(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
    }


    public List<Buyer> findAll() {
        return buyerRepository.findAll();
    }

    public Page<Buyer> findAll(Pageable pageable) {
        return buyerRepository.findAll(pageable);
    }

    public Page<Buyer> findAll(Specification<Buyer> spec, Pageable pageable) {
        return buyerRepository.findAll(spec, pageable);
    }

    public Optional<Buyer> findOne(Long id) {
        return buyerRepository.findById(id);
    }

    @Transactional
    public Buyer save(Buyer buyer) {
        return buyerRepository.save(buyer);
    }

    @Transactional
    public Buyer update(Buyer buyer) {
        Buyer mBuyer = buyerRepository.findById(buyer.getId()).orElseThrow(() -> new RuntimeException("Buyer not found."));
        mBuyer.setName(buyer.getName());
        return mBuyer;
    }

    @Transactional
    public void delete(Long id) {
        buyerRepository.deleteById(id);
    }

    public boolean exists(Long id) {
        return buyerRepository.existsById(id);
    }
}
