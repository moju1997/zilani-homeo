package com.zhomeo.zilani.service;

import com.zhomeo.zilani.entity.MedicinePower;
import com.zhomeo.zilani.entity.MedicineQuantity;
import com.zhomeo.zilani.repository.MedicineCategoriesRepository;
import com.zhomeo.zilani.repository.MedicinePowerRepository;
import com.zhomeo.zilani.repository.MedicineQuantityRepository;
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
public class MedicineQuantityService {
  private final Logger logger = LoggerFactory.getLogger(MedicineQuantityService.class);

  private MedicineQuantityRepository medicineQuantityRepository;

  public MedicineQuantityService(MedicineQuantityRepository medicineQuantityRepository) {
    this.medicineQuantityRepository = medicineQuantityRepository;
  }


  public List<MedicineQuantity> findAll() {
    return medicineQuantityRepository.findAll();
  }

  public Page<MedicineQuantity> findAll(Pageable pageable) {
    return medicineQuantityRepository.findAll(pageable);
  }

  public Page<MedicineQuantity> findAll(Specification<MedicineQuantity> spec, Pageable pageable) {
    return medicineQuantityRepository.findAll(spec, pageable);
  }

  public Optional<MedicineQuantity> findOne(Long id) {
    return medicineQuantityRepository.findById(id);
  }

  @Transactional
  public MedicineQuantity save(MedicineQuantity medicineQuantity) {
    return medicineQuantityRepository.save(medicineQuantity);
  }

  @Transactional
  public MedicineQuantity update(MedicineQuantity medicineQuantity) {
    MedicineQuantity mMedicineQuantity = medicineQuantityRepository.findById(medicineQuantity.getId()).orElseThrow(() -> new RuntimeException("MedicineQuantity not found."));
    mMedicineQuantity.setName(medicineQuantity.getName());
    mMedicineQuantity.setDescription(medicineQuantity.getDescription());
    return mMedicineQuantity;
  }

  @Transactional
  public void delete(Long id) {
    medicineQuantityRepository.deleteById(id);
  }

  public boolean exists(Long id) {
    return medicineQuantityRepository.existsById(id);
  }

}
