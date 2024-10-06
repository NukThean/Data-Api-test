package com.example.demo.vendor;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/pos/vendor")
public class VendorController {

  private final VendorService vendorService;

  @Autowired
  public VendorController(VendorService vendorService) {
    this.vendorService = vendorService;
  }

  @GetMapping
  public List<Vendor> getVendors() {
    return vendorService.getVendors();
  }

  @PostMapping
  public void addVendor(@RequestBody Vendor vendor) {
    vendorService.addVendor(vendor);
  }

  @DeleteMapping(path = "{vendorId}")
  public void deleteVendor(@PathVariable("vendorId") Long vendorId) {
    vendorService.deleteVendor(vendorId);
  }
}


