package com.dropbaba.backend.vendorservice.service;

import com.dropbaba.backend.vendorservice.event.VendorCreatedEvent;
import com.dropbaba.backend.vendorservice.messaging.VendorEventPublisher;
import com.dropbaba.backend.vendorservice.model.Vendor;
import com.dropbaba.backend.vendorservice.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final VendorEventPublisher vendorEventPublisher;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorEventPublisher vendorEventPublisher) {
        this.vendorRepository = vendorRepository;
        this.vendorEventPublisher = vendorEventPublisher;
    }

    @Override
    public Vendor createVendor(Vendor vendor) {
        vendor.setActive(true);
        Vendor saved = vendorRepository.save(vendor);

        VendorCreatedEvent event = VendorCreatedEvent.builder()
                .id(saved.getId())
                .name(saved.getName())
                .email(saved.getEmail())
                .city(saved.getCity())
                .build();

        vendorEventPublisher.publishVendorCreatedEvent(event);
        return saved;
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
