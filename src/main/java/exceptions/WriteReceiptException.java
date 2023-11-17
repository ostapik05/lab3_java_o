package exceptions;

public class WriteReceiptException extends Exception {
    public WriteReceiptException(){
        super("Can`t generate receipt file, pay receipt first");
    }
}
