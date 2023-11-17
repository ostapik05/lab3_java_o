package program;

import exceptions.ProductNotAvailableException;

import java.util.*;
import java.util.stream.Collectors;

public class Store {
    private List<Product> products = new ArrayList<>();
    private List<Receipt> receipts = new ArrayList<>();

    public Product addProduct(Product product) {
        products.add(product);
        return product;
    }

    private boolean isAvailable(Product product) {
        return products.stream()
                .anyMatch(e -> e.getName().equals(product.getName()) && e.getType().equals(product.getType()));
    }

    public void sellProduct(Product product) throws ProductNotAvailableException{
        if (isAvailable(product)) {
            products = products.stream()
                    .map(e -> e.getName().equals(product.getName()) ?
                            new Product(e.getName(), e.getPrice(), e.getType()) : e)
                    .collect(Collectors.toList());
        }else {
            throw new ProductNotAvailableException(product);
        }
    }

    public Product buyProduct(Product product) throws ProductNotAvailableException {
        if (isAvailable(product)) {
            products = products.stream()
                    .map(e -> e.getName().equals(product.getName()) ?
                            new Product(e.getName(), e.getPrice(), e.getType()) : e)
                    .collect(Collectors.toList());
        }else {
            throw new ProductNotAvailableException(product);
        }
        return product;
    }

    public void editProduct(Product product1, Product product2) {
        products.remove(product1);
        products.add(product2);
    }

    public void printProducts() {
        products.forEach(e -> System.out.println(e.getName() + " " + e.getPrice() + " " ));
    }

    public int countProducts() {
        return products.stream()
                .mapToInt(product -> 1)
                .sum();
    }

    public void averagePrice() {
        double averagePrice = products.stream()
                .mapToDouble(Product ::getPrice)
                .sum() / countProducts();
        System.out.println("Average price: " + averagePrice);
    }

    public void filterByPrice(double price) {
        products.stream()
                .filter(e -> e.getPrice() < price)
                .forEach(e -> System.out.println(e.getName() + " " + e.getPrice()));
    }

    public void sortByPrice() {
        products = products.stream()
                .sorted(Comparator.comparingDouble(Product::getPrice))
                .collect(Collectors.toList());
    }
}
