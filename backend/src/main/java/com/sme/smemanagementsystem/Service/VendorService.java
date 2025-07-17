package com.sme.smemanagementsystem.Service;

import com.sme.smemanagementsystem.Entity.Vendor;
import com.sme.smemanagementsystem.Repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendorService {
    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    public Vendor createVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    public Optional<Vendor> getVendorById(Long id) {
        return vendorRepository.findById(id);
    }

    public Vendor updateVendor(Long id, Vendor vendorDetails) {
        Optional<Vendor> optionalVendor = vendorRepository.findById(id);
        if (optionalVendor.isPresent()) {
            Vendor vendor = optionalVendor.get();
            vendor.setName(vendorDetails.getName());
            vendor.setEmail(vendorDetails.getEmail());
            vendor.setUpi(vendorDetails.getUpi());
            return vendorRepository.save(vendor);
        } else {
            return null;
        }
    }

    public void deleteVendor(Long id) {
        vendorRepository.deleteById(id);
    }

    public void sendEmailToVendor(Vendor vendor) {
        String subject = "Payment Notification";
        String message = "Hi " + vendor.getName()  + " payment is sent successfully to upi " + vendor.getUpi();

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(vendor.getEmail());
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        javaMailSender.send(mailMessage);
    }

    public void sendEmailsToVendors(List<Vendor> vendors) {
        for (Vendor vendor : vendors) {
            sendEmailToVendor(vendor);
        }
    }
}
