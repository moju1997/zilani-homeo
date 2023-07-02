package com.zhomeo.zilani.restcontroller;

import com.jazasoft.embedded.specification.CustomRsqlVisitor;
import com.zhomeo.zilani.ApiUrls;
import com.zhomeo.zilani.entity.MedicineCategory;
import com.zhomeo.zilani.entity.MedicinePower;
import com.zhomeo.zilani.service.MedicineCategoryService;
import com.zhomeo.zilani.service.MedicinePowerService;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(ApiUrls.ROOT_URL_MEDICINE_POWERS)
public class MedicinePowerRestController {
  private final Logger logger = LoggerFactory.getLogger(MedicinePowerRestController.class);

  private MedicinePowerService medicinePowerService;

  public MedicinePowerRestController(MedicinePowerService medicinePowerService) {
    this.medicinePowerService = medicinePowerService;
  }

  @GetMapping
  public ResponseEntity<?> findAllMedicineCategories(@RequestParam(value = "search", defaultValue = "") String search, Pageable pageable) {
    logger.trace("findAllMedicinePower()");
    Page<MedicinePower> pages;
    if (search.trim().isEmpty()) {
      pages = medicinePowerService.findAll(pageable);
    } else {
      Node rootNode = new RSQLParser().parse(search);
      Specification<MedicinePower> spec = rootNode.accept(new CustomRsqlVisitor<>());
      pages = medicinePowerService.findAll(spec, pageable);
    }
    return ResponseEntity.ok(pages);
  }

  @GetMapping(ApiUrls.URL_MEDICINE_POWERS_MEDICINE_POWER)
  public ResponseEntity<?> findOneMedicineCategory(@PathVariable("medicinePowerId") long id) {
    logger.trace("findOneMedicinePowerId(): id = {}", id);
    return ResponseEntity.of(medicinePowerService.findOne(id));
  }

  @PostMapping
  public ResponseEntity<?> saveMedicineCategory(@Valid @RequestBody MedicinePower medicinePower) {
    logger.trace("createMedicinePower():\n {}", medicinePower.toString());
    medicinePower = medicinePowerService.save(medicinePower);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(medicinePower.getId()).toUri();
    return ResponseEntity.created(location).body(medicinePower);
  }

  @PutMapping(ApiUrls.URL_MEDICINE_POWERS_MEDICINE_POWER)
  public ResponseEntity<?> updateMedicineCategory(@PathVariable("medicinePowerId") long id, @Validated @RequestBody MedicinePower medicinePower) {
    logger.trace("updateMedicinePowerId(): id = {} \n {}", id, medicinePower);
    if (!medicinePowerService.exists(id)) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    medicinePower.setId(id);
    medicinePower = medicinePowerService.update(medicinePower);
    return new ResponseEntity<>(medicinePower, HttpStatus.OK);
  }

  @DeleteMapping(ApiUrls.URL_MEDICINE_POWERS_MEDICINE_POWER)
  public ResponseEntity<?> delete(@PathVariable("medicinePowerId") long id) {
    logger.trace("deleteMedicinePowerId(): id = {}", id);
    if (!medicinePowerService.exists(id)) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    medicinePowerService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
