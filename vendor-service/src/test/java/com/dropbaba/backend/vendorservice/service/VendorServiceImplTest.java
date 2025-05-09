package com.dropbaba.backend.vendorservice.service;


import com.dropbaba.backend.vendorservice.messaging.VendorEventPublisher;
import com.dropbaba.backend.vendorservice.model.Vendor;
import com.dropbaba.backend.vendorservice.repository.VendorRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class VendorServiceImplTest {

    @Mock
    private VendorRepository vendorRepository;

    @InjectMocks
    private VendorServiceImpl vendorService;

    @Mock
    private VendorEventPublisher vendorEventPublisher;

    public VendorServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateVendorSuccessfully() {
        Vendor vendor = Vendor.builder()
                .name("Test Vendor")
                .email("test@vendor.com")
                .city("Lahore")
                .build();

        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);

        Vendor created = vendorService.createVendor(vendor);

        assertThat(created.getName()).isEqualTo("Test Vendor");
        verify(vendorRepository, times(1)).save(any());
    }

    @Test
    void shouldFindVendorById() {
        Vendor vendor = new Vendor(1L, "Tikka Hut", "tikka@hut.com", "03001234567", "Main Road", "Karachi",
                "Pakistan", true);
        when(vendorRepository.findById(1L)).thenReturn(Optional.of(vendor));

        Vendor result = vendorService.getVendorById(1L);

        assertThat(result);
        assertThat(result.getCity()).isEqualTo("Karachi");
    }
}
