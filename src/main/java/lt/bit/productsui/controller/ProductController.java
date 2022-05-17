package lt.bit.productsui.controller;

import lt.bit.productsui.model.Product;
import lt.bit.productsui.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@Controller
class ProductController {

    private ProductService service;

    ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/products")
    String showProducts(Model model) {
        List<Product> products = service.getProducts();
        model.addAttribute("productItems", products);
        return  "productList";
    }

    @GetMapping("/products/{id}")
    String editProduct(@PathVariable UUID id, Model model) {
        List<Product> products = service.getProducts();
        model.addAttribute("productItem", service.getProduct(id));
        return  "productForm";
    }

}
