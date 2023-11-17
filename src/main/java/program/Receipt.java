package program;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Receipt {
    private Customer customer;
    private List<Product> products;
    boolean isPaid;

    private LocalDate date;

    public Receipt(Customer customer, List<Product> products, LocalDate date) {
        this.customer = customer;
        this.products = products;
        this.isPaid = false;
        this.date = date;
    }

    public void payReceipt() {
        this.isPaid = true;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void printReceipt() {
        if (isPaid) {
            System.out.println("program.Receipt: " + this.date);
            System.out.println("program.Customer: " + this.customer.getName());
            System.out.println("Products: ");
            this.products.forEach(product -> System.out.println(product.getName() + ", type " + product.getType()));
            commentToCheck();
        } else {
            System.out.println("program.Receipt is not paid.To see receipt pay it.");
        }
    }

    public List<String> commentToCheck() {
        return products.stream()
                .filter(e -> (e.getType().equals(ProductType.MEAT) ||  e.getType().equals(ProductType.FISH)))
                .map(e -> "Put " + e.getName() + " in the fridge")
                .collect(Collectors.toList());
    }

    public void editReceipt(Customer newCustomer, List<Product> newProducts, LocalDate newDate) {
        if (!isPaid) {
            this.customer = newCustomer;
            this.products = newProducts;
            this.date = newDate;
            System.out.println("program.Receipt edited successfully.");
        } else {
            System.out.println("Cannot edit a paid receipt.");
        }
    }
}
