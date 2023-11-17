package program;

import exceptions.*;
import exceptions.FileReadException;
import exceptions.WriteReceiptException;
import exceptions.ProductNotAvailableException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ProductNotAvailableException, FileReadException, WriteReceiptException {
        List<Product> productList = new ArrayList<>();
        List<Product> productList1 = new ArrayList<>();
        FileService fileService = new FileService();

        Product product1 = new Product("Milk", 20.5, ProductType.GOODS);
        Product product2 = new Product("WhiteBread", 11.5, ProductType.GOODS);
        Product product3 = new Product("Salmon", 30.5, ProductType.FISH);
        Product product4 = new Product("Strawberry", 63.9, ProductType.FRUIT);

        Store store = new Store();

        List<Product> readProducts = FileService.readProductsFromFile("products.txt");

        store.addProduct(product1);
        store.addProduct(product2);
        store.addProduct(product3);
        Product productFromFile1 = store.addProduct(readProducts.get(0));
        Product productFromFile2 = store.addProduct(readProducts.get(1));
        Product productFromFile3 = store.addProduct(readProducts.get(2));
        store.addProduct(readProducts.get(1));
        productList1.add(store.buyProduct(productFromFile1));
        productList1.add(store.buyProduct(product3));
        productList1.add(store.buyProduct(product2));

        store.editProduct(product3, product4);

        store.printProducts();

        store.averagePrice();

        store.filterByPrice(10.0);

        store.sortByPrice();

        Receipt receipt1 = new Receipt(new Customer("Ivan"), productList1, LocalDate.of(2023, 10, 10));
        receipt1.payReceipt();
        receipt1.printReceipt();

        fileService.writeReceiptToFile( receipt1,"receipt");

    }
}