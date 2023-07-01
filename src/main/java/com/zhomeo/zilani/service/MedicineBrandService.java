package com.zhomeo.zilani.service;

import com.zhomeo.zilani.entity.MedicineBrand;
import com.zhomeo.zilani.entity.MedicinePower;
import com.zhomeo.zilani.repository.MedicineBrandRepository;
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
public class MedicineBrandService {

  private final Logger logger = LoggerFactory.getLogger(MedicineBrandService.class);

  private MedicineBrandRepository medicineBrandRepository;

  public MedicineBrandService(MedicineBrandRepository medicineBrandRepository) {
    this.medicineBrandRepository = medicineBrandRepository;
  }


  public List<MedicineBrand> findAll() {
    return medicineBrandRepository.findAll();
  }

  public Page<MedicineBrand> findAll(Pageable pageable) {
    return medicineBrandRepository.findAll(pageable);
  }

  public Page<MedicineBrand> findAll(Specification<MedicineBrand> spec, Pageable pageable) {
    return medicineBrandRepository.findAll(spec, pageable);
  }

  public Optional<MedicineBrand> findOne(Long id) {
    return medicineBrandRepository.findById(id);
  }

  @Transactional
  public MedicineBrand save(MedicineBrand medicinePower) {
    return medicineBrandRepository.save(medicinePower);
  }

  @Transactional
  public MedicineBrand update(MedicineBrand medicineBrand) {
    MedicineBrand mMedicineBrand = medicineBrandRepository.findById(medicineBrand.getId()).orElseThrow(() -> new RuntimeException("MedicinePower not found."));
    mMedicineBrand.setName(medicineBrand.getName());
    mMedicineBrand.setDescription(medicineBrand.getDescription());
    return mMedicineBrand;
  }

  @Transactional
  public void delete(Long id) {
    medicineBrandRepository.deleteById(id);
  }

  public boolean exists(Long id) {
    return medicineBrandRepository.existsById(id);
  }
}
