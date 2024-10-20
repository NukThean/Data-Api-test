package com.example.demo.product;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.demo.vendor.Vendor;
import com.example.demo.vendor.VendorRepository;
import com.example.demo.product_category.Category;
import com.example.demo.product_category.CategoryRepository;
import com.example.demo.sale_price.SalePrice;
import com.example.demo.sale_price.SalePriceRepository;

@Configuration
public class ProductConfig {

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository,
            VendorRepository vendorRepository, CategoryRepository categoryRepository,
            SalePriceRepository salePriceRepository) {
        return args -> {
            long count = productRepository.count();
            if (count < 2) {
                // Get today's date
                Date currentDate = new Date();

                Date createdAt = currentDate;
                Date updatedAt = currentDate;

                Vendor vendor = new Vendor("Logiech Cambodia", "Image/Logitech", true,
                        "Monivong Street #13A", null);

                Category category = new Category("Mouse");

                vendorRepository.save(vendor);
                categoryRepository.save(category);

                Product G102 = new Product("G102", "Logitech G102", 10, category, "200-8000 DPI",
                        null, "Image/G102", createdAt, updatedAt, true, vendor);

                Product G304 = new Product("G304", "Logitech G304", 10, category, "12000 DPI", null,
                        "Image/G304", createdAt, updatedAt, true, vendor);

                productRepository.saveAll(List.of(G102));
                productRepository.saveAll(List.of(G304));

                // Create SalePrice entries for the Product based on quantity
                SalePrice salePrice1 = new SalePrice(G102, // Reference to the product
                        1, // minQuantity
                        10, // maxQuantity
                        19.00f, // price per unit
                        currentDate, // effectiveDate
                        null, // expirationDate, null means no expiration
                        currentDate, currentDate);

                SalePrice salePrice2 = new SalePrice(G102, // Reference to the product
                        11, // minQuantity
                        50, // maxQuantity
                        18.50f, // price per unit
                        currentDate, // effectiveDate
                        null, // expirationDate
                        currentDate, currentDate);

                SalePrice salePrice3 = new SalePrice(G102, // Reference to the product
                        51, // minQuantity
                        100, // maxQuantity
                        17.50f, // price per unit
                        currentDate, // effectiveDate
                        null, // expirationDate
                        currentDate, currentDate);

                SalePrice salePrice4 = new SalePrice(G304, // Reference to the product
                        1, // minQuantity
                        null, // maxQuantity
                        40.00f, // price per unit
                        currentDate, // effectiveDate
                        null, // expirationDate
                        currentDate, currentDate);

                SalePrice salePrice5 = new SalePrice(G304, // Reference to the product
                        10, // minQuantity
                        null, // maxQuantity
                        38.00f, // price per unit
                        currentDate, // effectiveDate
                        null, // expirationDate
                        currentDate, currentDate);

                // Save the sale prices
                salePriceRepository.saveAll(List.of(salePrice4, salePrice5));
                salePriceRepository.saveAll(List.of(salePrice1, salePrice2, salePrice3));
            } ;
        };
    }
}
