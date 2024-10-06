package com.example.demo.vendor;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.product.ProductRepository;

@Service
public class VendorService {
  private final VendorRepository vendorRepository;
  private final ProductRepository productRepository;

  @Autowired
  public VendorService(VendorRepository vendorRepository, ProductRepository productRepository) {
    this.vendorRepository = vendorRepository;
    this.productRepository = productRepository;
  }

  // Check if Vendor with ID exists
  public Optional<Vendor> findVendorById(Long id) {
    return vendorRepository.findById(id);
  }

  public List<Vendor> getVendors() {
    return vendorRepository.findAll();
  }

  public void addVendor(Vendor vendor) {
    vendorRepository.save(vendor);
  }

  public void deleteVendor(Long id) {
    boolean exists = vendorRepository.existsById(id);
    if (!exists) {
      throw new IllegalStateException("Vendor with id " + id + " does not exists");
    }
    // Check if the vendor has associated products
    int associatedProductsCount = productRepository.countByVendorId(id);
    if (associatedProductsCount > 0) {
      throw new IllegalStateException("Vendor cannot be deleted because it has "
          + associatedProductsCount + " associated products.");
    }

    vendorRepository.deleteById(id);
  }
}
