package program;

import exceptions.FileReadException;
import exceptions.WriteReceiptException;
import exceptions.ProductNotAvailableException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileService {
    public static void writeReceiptToFile(Receipt receipt, String fileName) throws WriteReceiptException, FileReadException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writeLineToFile(writer, "program.Receipt Information:");
            writeLineToFile(writer, "Date: " + receipt.getDate());
            writeLineToFile(writer, "program.Customer: " + receipt.getCustomer().getName());
            writeLineToFile(writer, "Products:");

            receipt.getProducts().stream()
                    .map(product -> product.getName() + ", type: " + product.getType())
                    .forEach(line -> {
                        try {
                            writeLineToFile(writer, "- " + line);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });

            writeLineToFile(writer, "Comments:");
            receipt.commentToCheck().forEach(comment -> {
                try {
                    writeLineToFile(writer, comment);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            writeLineToFile(writer, "Total Price: " + calculateTotalPrice(receipt.getProducts()));
            writeLineToFile(writer, "Paid: " + receipt.isPaid());
            writeLineToFile(writer, "------------------------------");

            System.out.println("task.Receipt file generated successfully: " + fileName);
        } catch (IOException e) {
            throw new WriteReceiptException();
        }
    }

    private static void writeLineToFile(BufferedWriter writer, String line) throws IOException {
        writer.write(line + System.lineSeparator());
    }

    private static double calculateTotalPrice(List<Product> products) {
        return products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

    public static List<Product> readProductsFromFile(String filePath) throws FileReadException {
        final int maxLength = 3;
        List<Product> readProducts = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == maxLength) {
                    String name = parts[0].trim();
                    double price = Double.parseDouble(parts[1].trim());
                    ProductType type = ProductType.valueOf(parts[2].trim().toUpperCase());

                    Product product = new Product(name, price, type);
                    readProducts.add(product);
                }
            }
            System.out.println("Products loaded successfully from file: " + filePath);
        } catch ( IOException e) {
            throw new FileReadException();
        }
        return readProducts;
    }
}
