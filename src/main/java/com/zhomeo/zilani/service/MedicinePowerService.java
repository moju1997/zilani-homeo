package com.zhomeo.zilani.service;

import com.zhomeo.zilani.entity.MedicineCategory;
import com.zhomeo.zilani.entity.MedicinePower;
import com.zhomeo.zilani.repository.MedicineCategoriesRepository;
import com.zhomeo.zilani.repository.MedicinePowerRepository;
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
public class MedicinePowerService {

  private final Logger logger = LoggerFactory.getLogger(MedicinePowerService.class);

  private MedicinePowerRepository medicinePowerRepository;

  public MedicinePowerService(MedicinePowerRepository medicinePowerRepository) {
    this.medicinePowerRepository = medicinePowerRepository;
  }


  public List<MedicinePower> findAll() {
    return medicinePowerRepository.findAll();
  }

  public Page<MedicinePower> findAll(Pageable pageable) {
    return medicinePowerRepository.findAll(pageable);
  }

  public Page<MedicinePower> findAll(Specification<MedicinePower> spec, Pageable pageable) {
    return medicinePowerRepository.findAll(spec, pageable);
  }

  public Optional<MedicinePower> findOne(Long id) {
    return medicinePowerRepository.findById(id);
  }

  @Transactional
  public MedicinePower save(MedicinePower medicinePower) {
    return medicinePowerRepository.save(medicinePower);
  }

  @Transactional
  public MedicinePower update(MedicinePower medicinePower) {
    MedicinePower mMedicineCategory = medicinePowerRepository.findById(medicinePower.getId()).orElseThrow(() -> new RuntimeException("MedicinePower not found."));
    mMedicineCategory.setName(medicinePower.getName());
    mMedicineCategory.setDescription(medicinePower.getDescription());
    return mMedicineCategory;
  }

  @Transactional
  public void delete(Long id) {
    medicinePowerRepository.deleteById(id);
  }

  public boolean exists(Long id) {
    return medicinePowerRepository.existsById(id);
  }

}
