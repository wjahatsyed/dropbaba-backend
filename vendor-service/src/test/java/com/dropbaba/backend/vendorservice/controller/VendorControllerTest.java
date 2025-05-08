package com.dropbaba.backend.vendorservice.controller;

import com.dropbaba.backend.vendorservice.model.Vendor;
import com.dropbaba.backend.vendorservice.service.VendorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VendorController.class)
@Import(VendorControllerTest.MockedServiceConfig.class)  // import the manual mock config
public class VendorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VendorService vendorService;

    private Vendor vendor;

    @BeforeEach
    void setUp() {
        vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName("Wajahat Foods");
        vendor.setEmail("contact@wajahatfoods.pk");
    }

    @Test
    void shouldCreateVendorSuccessfully() throws Exception {
        when(vendorService.createVendor(any(Vendor.class))).thenReturn(vendor);

        mockMvc.perform(post("/api/vendors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                      "name": "Wajahat Foods",
                      "email": "contact@wajahatfoods.pk"
                    }
                    """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Wajahat Foods"))
                .andExpect(jsonPath("$.email").value("contact@wajahatfoods.pk"));
    }

    // ✅ Manual mock configuration
    @TestConfiguration
    static class MockedServiceConfig {
        @Bean
        public VendorService vendorService() {
            return Mockito.mock(VendorService.class);
        }
    }
}
