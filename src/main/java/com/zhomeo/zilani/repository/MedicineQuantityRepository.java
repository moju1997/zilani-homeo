package com.zhomeo.zilani.repository;

import com.zhomeo.zilani.entity.MedicinePower;
import com.zhomeo.zilani.entity.MedicineQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MedicineQuantityRepository extends JpaRepository<MedicineQuantity, Long>, JpaSpecificationExecutor<MedicineQuantity> {
}
