package com.zhomeo.zilani.service;


import com.zhomeo.zilani.entity.Vendor;
import com.zhomeo.zilani.repository.VendorRepository;
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
public class VendorService {
    private final Logger logger = LoggerFactory.getLogger(VendorService.class);

    private VendorRepository vendorRepository;

    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }


    public List<Vendor> findAll() {
        return vendorRepository.findAll();
    }

    public Page<Vendor> findAll(Pageable pageable) {
        return vendorRepository.findAll(pageable);
    }

    public Page<Vendor> findAll(Specification<Vendor> spec, Pageable pageable) {
        return vendorRepository.findAll(spec, pageable);
    }

    public Optional<Vendor> findOne(Long id) {
        return vendorRepository.findById(id);
    }

    @Transactional
    public Vendor save(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    @Transactional
    public Vendor update(Vendor vendor) {
        Vendor mvendor = vendorRepository.findById(vendor.getId()).orElseThrow(() -> new RuntimeException("vendor not found."));
        mvendor.setName(vendor.getName());
        mvendor.setDescription(vendor.getDescription());
        return mvendor;
    }

    @Transactional
    public void delete(Long id) {
        vendorRepository.deleteById(id);
    }

    public boolean exists(Long id) {
        return vendorRepository.existsById(id);
    }
}
