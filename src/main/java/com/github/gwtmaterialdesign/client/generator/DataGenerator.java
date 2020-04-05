package com.github.gwtmaterialdesign.client.generator;

import com.github.gwtmaterialdesign.client.generator.product.Product;
import com.github.gwtmaterialdesign.client.generator.product.ProductGenerator;
import com.github.gwtmaterialdesign.client.generator.user.User;
import com.github.gwtmaterialdesign.client.generator.user.UserGenerator;
import com.github.gwtmaterialdesign.client.resources.AppResources;
import gwt.material.design.client.MaterialDesign;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator {

    static {
        MaterialDesign.injectJs(AppResources.INSTANCE.fakerJs());
    }

    public List<User> generateUsers(int total) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < total; i++) {
            users.add(UserGenerator.generate());
        }
        return users;
    }

    public List<Product> generateProducts(int total) {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < total; i++) {
            Product product = ProductGenerator.generate();
            products.add(product);
        }
        return products;
    }

}
