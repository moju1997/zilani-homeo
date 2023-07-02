package com.zhomeo.zilani.repository;

import com.zhomeo.zilani.entity.MedicineBrand;
import com.zhomeo.zilani.entity.MedicinePower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MedicineBrandRepository extends JpaRepository<MedicineBrand, Long>, JpaSpecificationExecutor<MedicineBrand> {
}
