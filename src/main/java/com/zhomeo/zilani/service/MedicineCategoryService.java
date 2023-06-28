package com.zhomeo.zilani.service;

import com.zhomeo.zilani.entity.MedicineCategory;
import com.zhomeo.zilani.entity.Vendor;
import com.zhomeo.zilani.repository.MedicineCategoriesRespository;
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
public class MedicineCategoryService {
  private final Logger logger = LoggerFactory.getLogger(VendorService.class);

  private MedicineCategoriesRespository medicineCategoriesRespository;

  public MedicineCategoryService(MedicineCategoriesRespository medicineCategoriesRespository) {
    this.medicineCategoriesRespository = medicineCategoriesRespository;
  }


  public List<MedicineCategory> findAll() {
    return medicineCategoriesRespository.findAll();
  }

  public Page<MedicineCategory> findAll(Pageable pageable) {
    return medicineCategoriesRespository.findAll(pageable);
  }

  public Page<MedicineCategory> findAll(Specification<MedicineCategory> spec, Pageable pageable) {
    return medicineCategoriesRespository.findAll(spec, pageable);
  }

  public Optional<MedicineCategory> findOne(Long id) {
    return medicineCategoriesRespository.findById(id);
  }

  @Transactional
  public MedicineCategory save(MedicineCategory medicineCategory) {
    return medicineCategoriesRespository.save(medicineCategory);
  }

  @Transactional
  public MedicineCategory update(MedicineCategory medicineCategory) {
    MedicineCategory mMedicineCategory = medicineCategoriesRespository.findById(medicineCategory.getId()).orElseThrow(() -> new RuntimeException("MedicineCategory not found."));
    mMedicineCategory.setName(medicineCategory.getName());
    return mMedicineCategory;
  }

  @Transactional
  public void delete(Long id) {
    medicineCategoriesRespository.deleteById(id);
  }

  public boolean exists(Long id) {
    return medicineCategoriesRespository.existsById(id);
  }
}
