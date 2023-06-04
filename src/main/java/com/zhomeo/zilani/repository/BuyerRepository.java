package com.zhomeo.zilani.repository;

import com.zhomeo.zilani.entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BuyerRepository extends JpaRepository<Buyer, Long>, JpaSpecificationExecutor<Buyer> {
}
