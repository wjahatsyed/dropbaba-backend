package com.dropbaba.backend.vendorservice.controller;

import com.dropbaba.backend.vendorservice.model.Vendor;
import com.dropbaba.backend.vendorservice.service.VendorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @PostMapping
    public ResponseEntity<Vendor> createVendor(@RequestBody Vendor vendor) {
        return ResponseEntity.ok(vendorService.createVendor(vendor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vendor> updateVendor(@PathVariable Long id, @RequestBody Vendor vendor) {
        return ResponseEntity.ok(vendorService.updateVendor(id, vendor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendor> getVendorById(@PathVariable Long id) {
        Vendor vendor = vendorService.getVendorById(id);
        return ResponseEntity.ok(vendor);
    }

    @GetMapping(params = "country")
    public ResponseEntity<List<Vendor>> getActiveVendorsByCity(@RequestParam String city) {
        return ResponseEntity.ok(vendorService.getActiveVendorsByCity(city));
    }

    @GetMapping
    public ResponseEntity<List<Vendor>> getActiveVendorsByCountry(@RequestParam String country) {
        return ResponseEntity.ok(vendorService.getActiveVendorsByCountry(country));
    }


}
