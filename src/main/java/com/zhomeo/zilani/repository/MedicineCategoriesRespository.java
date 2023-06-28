package com.zhomeo.zilani.repository;

import com.zhomeo.zilani.entity.MedicineCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MedicineCategoriesRespository extends JpaRepository<MedicineCategory, Long>, JpaSpecificationExecutor<MedicineCategory> {
}
