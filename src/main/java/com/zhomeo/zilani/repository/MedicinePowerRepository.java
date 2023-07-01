package com.zhomeo.zilani.repository;

import com.zhomeo.zilani.entity.MedicineCategory;
import com.zhomeo.zilani.entity.MedicinePower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MedicinePowerRepository extends JpaRepository<MedicinePower, Long>, JpaSpecificationExecutor<MedicinePower> {
}
