package lt.bit.productsui.controller;

import lt.bit.productsui.model.Product;
import lt.bit.productsui.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/products/add")
    String addProduct(Model model) {
        List<Product> products = service.getProducts();
        model.addAttribute("productItem", new Product());
        return  "productForm";
    }

    @PostMapping("products/save")
    String saveProduct(@ModelAttribute Product product, Model model) {
        String error = hasError(product.getName());
        if (error != null) {
            model.addAttribute("errorMsg", error);
            model.addAttribute("productItem", product);
            return "productForm";
        }
        service.saveProduct(product);
        return "redirect:/products";
    }

    private String hasError(String name) {

        if (!StringUtils.hasLength(name)) {
            return "Name is required";
        }

        if (name.length() < 3) {
            return "Name is to short";
        }
        return null;
    }

    @GetMapping("/products/delete")
    String deleteProduct(@RequestParam UUID id) {
        service.deleteProduct(id);
        return "redirect:/products";
    }

}
