package com.dropbaba.backend.vendorservice.service;

import com.dropbaba.backend.vendorservice.model.Vendor;

import java.util.List;
import java.util.Optional;

public interface VendorService {
    Vendor createVendor(Vendor vendor);
    Vendor updateVendor(Long id, Vendor vendor);
    Optional<Vendor> getVendor(Long id);
    List<Vendor> getActiveVendorsByCity(String city);
    List<Vendor> getActiveVendorsByCountry(String country);
}