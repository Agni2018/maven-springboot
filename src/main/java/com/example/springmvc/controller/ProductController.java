package com.example.springmvc.controller;

import com.example.springmvc.Models.Product;
import com.example.springmvc.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@Controller
public class ProductController {
    private ProductRepository productRepository;
    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping(path="/")
    public String index(){
        return "index";
    }
    @RequestMapping(path="/Products/add", method=RequestMethod.GET)
    public String createProduct(Model model){
        model.addAttribute("product",new Product());
        return "entry";
    }
    @RequestMapping(path="/Products", method=RequestMethod.POST)
    public String saveProduct(Product product){
        productRepository.save(product);
        return "redirect:/";
    }
    @RequestMapping(path="/ProductsDisplay",method=RequestMethod.GET)
    public String getProduct(Model model){
        model.addAttribute("products",productRepository.findAll());
        return "products";
    }
    @RequestMapping(path="/Products/edit/{id}",method=RequestMethod.GET)
    public String editProduct(Model model, @PathVariable(name="id") UUID id){
        model.addAttribute("product",productRepository.findById(id));
        return "entry";
    }
    @RequestMapping(path="/Products/delete/{id}",method=RequestMethod.GET)
    public String deleteProduct(@PathVariable(name="id")UUID id){
        productRepository.deleteById(id);
        return "redirect:/ProductsDisplay";
    }

}

