package exceptions;

import program.Product;
public class ProductNotAvailableException extends Exception {
    public ProductNotAvailableException(final Product product) {
        super("program.Product " + product.getName() + " not available");
    }
}