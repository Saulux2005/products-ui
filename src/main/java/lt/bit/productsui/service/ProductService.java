package lt.bit.productsui.service;

import lt.bit.productsui.model.Product;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private List<Product> products = new ArrayList<>();

    public ProductService() {
        products.add(new Product("Product1", BigDecimal.valueOf(10.5), 5));
        products.add(new Product("Product2", BigDecimal.valueOf(12.35), 11));
        products.add(new Product("Product3", BigDecimal.valueOf(9.87), 27));
        products.add(new Product("Product4", BigDecimal.valueOf(3.99), 55));
        products.add(new Product("Product5", BigDecimal.valueOf(59.78), 3));
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product newProduct) {
        products.add(newProduct);
    }

    public void updateProduct(Product product) {
        Product existingProduct = findProduct(product.getId());
        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setQuantity(product.getQuantity());
        }

    }

    public void deleteProduct(UUID id) {
        products.remove(findProduct(id));
    }

    public Product getProduct(UUID id) {
        return findProduct(id);
    }

    private Product findProduct(UUID id) {
        for (Product p : products) {
            if(p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }
}
