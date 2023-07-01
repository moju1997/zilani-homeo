package com.zhomeo.zilani.service;

import com.zhomeo.zilani.entity.MedicineCategory;
import com.zhomeo.zilani.repository.MedicineCategoriesRepository;
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
  private final Logger logger = LoggerFactory.getLogger(MedicineCategoryService.class);

  private MedicineCategoriesRepository medicineCategoriesRepository;

  public MedicineCategoryService(MedicineCategoriesRepository medicineCategoriesRepository) {
    this.medicineCategoriesRepository = medicineCategoriesRepository;
  }


  public List<MedicineCategory> findAll() {
    return medicineCategoriesRepository.findAll();
  }

  public Page<MedicineCategory> findAll(Pageable pageable) {
    return medicineCategoriesRepository.findAll(pageable);
  }

  public Page<MedicineCategory> findAll(Specification<MedicineCategory> spec, Pageable pageable) {
    return medicineCategoriesRepository.findAll(spec, pageable);
  }

  public Optional<MedicineCategory> findOne(Long id) {
    return medicineCategoriesRepository.findById(id);
  }

  @Transactional
  public MedicineCategory save(MedicineCategory medicineCategory) {
    return medicineCategoriesRepository.save(medicineCategory);
  }

  @Transactional
  public MedicineCategory update(MedicineCategory medicineCategory) {
    MedicineCategory mMedicineCategory = medicineCategoriesRepository.findById(medicineCategory.getId()).orElseThrow(() -> new RuntimeException("MedicineCategory not found."));
    mMedicineCategory.setName(medicineCategory.getName());
    mMedicineCategory.setDescription(medicineCategory.getDescription());
    return mMedicineCategory;
  }

  @Transactional
  public void delete(Long id) {
    medicineCategoriesRepository.deleteById(id);
  }

  public boolean exists(Long id) {
    return medicineCategoriesRepository.existsById(id);
  }
}
