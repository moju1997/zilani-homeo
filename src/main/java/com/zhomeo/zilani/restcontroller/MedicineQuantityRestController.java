package com.zhomeo.zilani.restcontroller;

import com.jazasoft.embedded.specification.CustomRsqlVisitor;
import com.zhomeo.zilani.ApiUrls;
import com.zhomeo.zilani.entity.MedicinePower;
import com.zhomeo.zilani.entity.MedicineQuantity;
import com.zhomeo.zilani.service.MedicinePowerService;
import com.zhomeo.zilani.service.MedicineQuantityService;
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
@RequestMapping(ApiUrls.ROOT_URL_MEDICINE_QUANTITY)
public class MedicineQuantityRestController {
  private final Logger logger = LoggerFactory.getLogger(MedicineQuantityRestController.class);

  private MedicineQuantityService medicineQuantityService;

  public MedicineQuantityRestController(MedicineQuantityService medicineQuantityService) {
    this.medicineQuantityService = medicineQuantityService;
  }

  @GetMapping
  public ResponseEntity<?> findAllMedicineCategories(@RequestParam(value = "search", defaultValue = "") String search, Pageable pageable) {
    logger.trace("findAllMedicineQuantities()");
    Page<MedicineQuantity> pages;
    if (search.trim().isEmpty()) {
      pages = medicineQuantityService.findAll(pageable);
    } else {
      Node rootNode = new RSQLParser().parse(search);
      Specification<MedicineQuantity> spec = rootNode.accept(new CustomRsqlVisitor<>());
      pages = medicineQuantityService.findAll(spec, pageable);
    }
    return ResponseEntity.ok(pages);
  }

  @GetMapping(ApiUrls.URL_MEDICINE_QUANTITY_MEDICINE_QUANTITY)
  public ResponseEntity<?> findOneMedicineCategory(@PathVariable("medicineQuantityId") long id) {
    logger.trace("findOneMedicineQuantity(): id = {}", id);
    return ResponseEntity.of(medicineQuantityService.findOne(id));
  }

  @PostMapping
  public ResponseEntity<?> saveMedicineCategory(@Valid @RequestBody MedicineQuantity medicineQuantity) {
    logger.trace("createMedicineQuantity():\n {}", medicineQuantity.toString());
    medicineQuantity = medicineQuantityService.save(medicineQuantity);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(medicineQuantity.getId()).toUri();
    return ResponseEntity.created(location).body(medicineQuantity);
  }

  @PutMapping(ApiUrls.URL_MEDICINE_QUANTITY_MEDICINE_QUANTITY)
  public ResponseEntity<?> updateMedicineCategory(@PathVariable("medicineQuantityId") long id, @Validated @RequestBody MedicineQuantity medicineQuantity) {
    logger.trace("updateMedicineQuantityId(): id = {} \n {}", id, medicineQuantity);
    if (!medicineQuantityService.exists(id)) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    medicineQuantity.setId(id);
    medicineQuantity = medicineQuantityService.update(medicineQuantity);
    return new ResponseEntity<>(medicineQuantity, HttpStatus.OK);
  }

  @DeleteMapping(ApiUrls.URL_MEDICINE_QUANTITY_MEDICINE_QUANTITY)
  public ResponseEntity<?> delete(@PathVariable("medicineQuantityId") long id) {
    logger.trace("deleteMedicineQuantityId(): id = {}", id);
    if (!medicineQuantityService.exists(id)) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    medicineQuantityService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
