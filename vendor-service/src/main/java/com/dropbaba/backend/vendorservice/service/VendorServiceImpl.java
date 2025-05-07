package com.dropbaba.backend.vendorservice.service;

import com.dropbaba.backend.vendorservice.model.Vendor;
import com.dropbaba.backend.vendorservice.repository.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public Vendor createVendor(Vendor vendor) {
        vendor.setActive(true);
        return vendorRepository.save(vendor);
    }

    @Override
    public Vendor updateVendor(Long id, Vendor vendor) {
        vendor.setId(id);
        return vendorRepository.save(vendor);
    }

    @Override
    public Optional<Vendor> getVendor(Long id) {
        return vendorRepository.findById(id);
    }

    @Override
    public List<Vendor> getActiveVendorsByCity(String city) {
        return vendorRepository.findByCityAndActive(city, true);
    }

    @Override
    public List<Vendor> getActiveVendorsByCountry(String country) {
        return vendorRepository.findByCountryAndActive(country, true);
    }
}
