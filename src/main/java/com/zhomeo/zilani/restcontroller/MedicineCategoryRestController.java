package com.zhomeo.zilani.restcontroller;

import com.jazasoft.embedded.specification.CustomRsqlVisitor;
import com.zhomeo.zilani.ApiUrls;
import com.zhomeo.zilani.entity.MedicineCategory;
import com.zhomeo.zilani.service.MedicineCategoryService;
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
@RequestMapping("/api/medicineCategories")
public class MedicineCategoryRestController {
  private final Logger logger = LoggerFactory.getLogger(MedicineCategoryRestController.class);

  private MedicineCategoryService medicineCategoryService;

  public MedicineCategoryRestController(MedicineCategoryService medicineCategoryService) {
    this.medicineCategoryService = medicineCategoryService;
  }

  @GetMapping
  public ResponseEntity<?> findAllMedicineCategories(@RequestParam(value = "search", defaultValue = "") String search, Pageable pageable) {
    logger.trace("findAllMedicineCategories()");
    Page<MedicineCategory> pages;
    if (search.trim().isEmpty()) {
      pages = medicineCategoryService.findAll(pageable);
    } else {
      Node rootNode = new RSQLParser().parse(search);
      Specification<MedicineCategory> spec = rootNode.accept(new CustomRsqlVisitor<>());
      pages = medicineCategoryService.findAll(spec, pageable);
    }
    return ResponseEntity.ok(pages);
  }

  @GetMapping(ApiUrls.URL_MEDICINE_CATEGORYIES_MEDICINE_CATEGORY)
  public ResponseEntity<?> findOneMedicineCategory(@PathVariable("medicineCategoryId") long id) {
    logger.trace("findOneBuyer(): id = {}", id);
    return ResponseEntity.of(medicineCategoryService.findOne(id));
  }

  @PostMapping
  public ResponseEntity<?> saveMedicineCategory(@Valid @RequestBody MedicineCategory medicineCategory) {
    logger.trace("createMedicineCategory():\n {}", medicineCategory.toString());
    medicineCategory = medicineCategoryService.save(medicineCategory);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(medicineCategory.getId()).toUri();
    return ResponseEntity.created(location).body(medicineCategory);
  }

  @PutMapping(ApiUrls.URL_MEDICINE_CATEGORYIES_MEDICINE_CATEGORY)
  public ResponseEntity<?> updateBuyerMedicineCategory(@PathVariable("medicineCategoryId") long id, @Validated @RequestBody MedicineCategory medicineCategory) {
    logger.trace("updateBuyer(): id = {} \n {}", id, medicineCategory);
    if (!medicineCategoryService.exists(id)) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    medicineCategory.setId(id);
    medicineCategory = medicineCategoryService.update(medicineCategory);
    return new ResponseEntity<>(medicineCategory, HttpStatus.OK);
  }

  @DeleteMapping(ApiUrls.URL_MEDICINE_CATEGORYIES_MEDICINE_CATEGORY)
  public ResponseEntity<?> deleteBuyer(@PathVariable("buyerId") long id) {
    logger.trace("deleteBuyer(): id = {}", id);
    if (!medicineCategoryService.exists(id)) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    medicineCategoryService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
