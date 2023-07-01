package com.zhomeo.zilani.restcontroller;

import com.jazasoft.embedded.specification.CustomRsqlVisitor;
import com.zhomeo.zilani.ApiUrls;
import com.zhomeo.zilani.entity.MedicineBrand;
import com.zhomeo.zilani.entity.MedicinePower;
import com.zhomeo.zilani.service.MedicineBrandService;
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
@RequestMapping(ApiUrls.ROOT_URL_MEDICINE_BRANDS)
public class MedicineBrandRestController {
  private final Logger logger = LoggerFactory.getLogger(MedicineBrandRestController.class);

  private MedicineBrandService medicineBrandService;

  public MedicineBrandRestController(MedicineBrandService medicineBrandService) {
    this.medicineBrandService = medicineBrandService;
  }

  @GetMapping
  public ResponseEntity<?> findAllMedicineBrand(@RequestParam(value = "search", defaultValue = "") String search, Pageable pageable) {
    logger.trace("findAllMedicinePower()");
    Page<MedicineBrand> pages;
    if (search.trim().isEmpty()) {
      pages = medicineBrandService.findAll(pageable);
    } else {
      Node rootNode = new RSQLParser().parse(search);
      Specification<MedicineBrand> spec = rootNode.accept(new CustomRsqlVisitor<>());
      pages = medicineBrandService.findAll(spec, pageable);
    }
    return ResponseEntity.ok(pages);
  }

  @GetMapping(ApiUrls.URL_MEDICINE_BRANDS_MEDICINE_BRAND)
  public ResponseEntity<?> findOneMedicineBrand(@PathVariable("medicineBrandId") long id) {
    logger.trace("findOneMedicineBrandId(): id = {}", id);
    return ResponseEntity.of(medicineBrandService.findOne(id));
  }

  @PostMapping
  public ResponseEntity<?> saveMedicineBrand(@Valid @RequestBody MedicineBrand medicineBrand) {
    logger.trace("createMedicineBrand():\n {}", medicineBrand.toString());
    medicineBrand = medicineBrandService.save(medicineBrand);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(medicineBrand.getId()).toUri();
    return ResponseEntity.created(location).body(medicineBrand);
  }

  @PutMapping(ApiUrls.URL_MEDICINE_BRANDS_MEDICINE_BRAND)
  public ResponseEntity<?> updateMedicineBrand(@PathVariable("medicineBrandId") long id, @Validated @RequestBody MedicineBrand medicineBrand) {
    logger.trace("updateMedicineBrandId(): id = {} \n {}", id, medicineBrand);
    if (!medicineBrandService.exists(id)) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    medicineBrand.setId(id);
    medicineBrand = medicineBrandService.update(medicineBrand);
    return new ResponseEntity<>(medicineBrand, HttpStatus.OK);
  }

  @DeleteMapping(ApiUrls.URL_MEDICINE_BRANDS_MEDICINE_BRAND)
  public ResponseEntity<?> delete(@PathVariable("medicineBrandId") long id) {
    logger.trace("deleteMedicineBrandId(): id = {}", id);
    if (!medicineBrandService.exists(id)) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    medicineBrandService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
