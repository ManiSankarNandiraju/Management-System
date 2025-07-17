package com.sme.smemanagementsystem.Controller;

import com.sme.smemanagementsystem.Entity.Vendor;
import com.sme.smemanagementsystem.Service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vendors")
public class VendorController {
    @Autowired
    private VendorService vendorService;

    @PostMapping("/add")
    public Vendor createVendor(@RequestBody Vendor vendor) {
        return vendorService.createVendor(vendor);
    }

    @GetMapping("/all")
    public List<Vendor> getAllVendors() {
        return vendorService.getAllVendors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendor> getVendorById(@PathVariable Long id) {
        Optional<Vendor> vendor = vendorService.getVendorById(id);
        if (vendor.isPresent()) {
            return ResponseEntity.ok(vendor.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vendor> updateVendor(@PathVariable Long id, @RequestBody Vendor vendorDetails) {
        Vendor updatedVendor = vendorService.updateVendor(id, vendorDetails);
        if (updatedVendor != null) {
            return ResponseEntity.ok(updatedVendor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendor(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/send-email")
    public void sendEmailToVendors(@RequestBody List<Vendor> vendors) {
        vendorService.sendEmailsToVendors(vendors);
    }
}
