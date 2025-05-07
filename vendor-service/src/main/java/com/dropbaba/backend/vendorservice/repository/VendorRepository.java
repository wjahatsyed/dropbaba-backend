package com.dropbaba.backend.vendorservice.repository;

import com.dropbaba.backend.vendorservice.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
    List<Vendor> findByCityAndActive(String city, boolean active);

    List<Vendor> findByCountryAndActive(String country, boolean active);
}
