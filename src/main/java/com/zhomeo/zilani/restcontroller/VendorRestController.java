package com.zhomeo.zilani.restcontroller;

import com.jazasoft.embedded.specification.CustomRsqlVisitor;
import com.zhomeo.zilani.ApiUrls;
import com.zhomeo.zilani.entity.Vendor;
import com.zhomeo.zilani.service.VendorService;
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
@RequestMapping(ApiUrls.ROOT_URL_VENDORS)
public class VendorRestController {
    private final Logger logger = LoggerFactory.getLogger(VendorRestController.class);

    private VendorService vendorService;

    public VendorRestController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    public ResponseEntity<?> findAllVendors(@RequestParam(value = "search", defaultValue = "") String search, Pageable pageable) {
        logger.trace("findAllVendors()");
        Page<Vendor> pages;
        if (search.trim().isEmpty()) {
            pages = vendorService.findAll(pageable);
        } else {
            Node rootNode = new RSQLParser().parse(search);
            Specification<Vendor> spec = rootNode.accept(new CustomRsqlVisitor<>());
            pages = vendorService.findAll(spec, pageable);
        }
        return ResponseEntity.ok(pages);
    }

    @GetMapping(ApiUrls.URL_VENDORS_VENDOR)
    public ResponseEntity<?> findOneVendor(@PathVariable("vendorId") long id) {
        logger.trace("findOneVendor(): id = {}", id);
        return ResponseEntity.of(vendorService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<?> saveVendor(@Valid @RequestBody Vendor Vendor) {
        logger.trace("createVendor():\n {}", Vendor.toString());
        Vendor = vendorService.save(Vendor);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(Vendor.getId()).toUri();
        return ResponseEntity.created(location).body(Vendor);
    }

    @PutMapping(ApiUrls.URL_VENDORS_VENDOR)
    public ResponseEntity<?> updateVendor(@PathVariable("vendorId") long id, @Validated @RequestBody Vendor vendor) {
        logger.trace("updateVendor(): id = {} \n {}", id, vendor);
        if (!vendorService.exists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        vendor.setId(id);
        vendor = vendorService.update(vendor);
        return new ResponseEntity<>(vendor, HttpStatus.OK);
    }

    @DeleteMapping(ApiUrls.URL_VENDORS_VENDOR)
    public ResponseEntity<?> deleteVendor(@PathVariable("vendorId") long id) {
        logger.trace("deleteVendor(): id = {}", id);
        if (!vendorService.exists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        vendorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
