
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Library Card associated with the Student
 */
public class LibraryCard {
    /**
     * Card id
     */
    private int ID;

    /**
     * Issue Date of the Card
     */
    private Date IssueDate;

    /**
     * Expiry Date of the Card
     */
    private Date ExpiryDate;

    /**
     * Number of books borrowed
     */
    private List<Book> borrowed = new ArrayList<Book>();

    /**
     * Fine asscoaited with the card
     */
    private double fine;

    /**
     * Details about the cardholder
     */
    private Student student;

    public LibraryCard(Student student, Date IssueDate, Date ExpiryDate, int ID) {
        this.student = student;
        this.IssueDate = IssueDate;
        this.ExpiryDate = ExpiryDate;
        this.ID = ID;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    public Date getIssueDate() {
        return IssueDate;
    }

    public void setIssueDate(Date IssueDate) {
        this.IssueDate = IssueDate;
    }

    public Date getExpiryDate() {
        return ExpiryDate;
    }

    public void setExpiryDate(Date ExpiryDate) {
        this.ExpiryDate = ExpiryDate;
    }

    public List<Book> getBooks() {
        return borrowed;
    }

    // method to add/issue valid book to borrowed arraylist
    public void addBook(Book book) {
        this.borrowed.add(book);
    }

    /**
     * Issue a new book
     * 
     * @param Book: book to borrow
     * @return true if the book is successfully borrowed, false otherwise
     * @throws IllegalBookIssueException
     */

    public boolean issueBook(Book book) throws IllegalBookIssueException {
        int numbooks = getBooks().size();

        if (numbooks < 4) {
            if (bookBorrowedAlready(book)) {
                throw new IllegalBookIssueException("Book has already been issued");
            } else {
                if (cardExpired()) {
                    if (bookAvailable(book)) {
                        if (noPendingFine()) {
                            // issue the book
                            addBook(book);
                            // call method to set return days
                            bookReturnDays(book);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    // method to check if book has been borrowed already
    // private boolean bookBorrowedAlready(Book book) throws
    // IllegalBookIssueException {
    // if (borrowed.contains(book)) {
    // throw new IllegalBookIssueException("Book is already issued");
    // }
    // return true;
    // }

    private boolean bookBorrowedAlready(Book book) {
        return borrowed.contains(book);
    }

    // method to check if expiration date has passed current date
    private boolean cardExpired() throws IllegalBookIssueException {
        Date currentDate = new Date();
        if (ExpiryDate.after(currentDate)) {
            return true;
        } else {
            return false;
        }
    }

    // is book available to borrow (check status)
    private boolean bookAvailable(Book book) {
        return book.getStatus();
    }

    private static void bookReturnDays(Book book) {
        // sets return days according to book demand
        if (book.getDemand() == 0) {
            book.setDays(15);
        } else if (book.getDemand() == 1) {
            book.setDays(3);
        }
    }

    // method returns false if any number other than 0.0 indicating a fine
    private boolean noPendingFine() {
        return fine == 0.0;
    }

}
