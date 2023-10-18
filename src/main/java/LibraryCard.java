
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

    /**
     * Issue a new book
     * 
     * @param Book: book to borrow
     * @return true if the book is successfully borrowed, false otherwise
     */

    public boolean issueBook(Book book) {
        int numbooks = getBooks().size();
        boolean book_borrowed = borrowed.contains(book);

        if (numbooks <= 4) {
            if (!book_borrowed) {
                return true;
            }

        }
        return false;

    }

    private boolean isBookAlreadyBorrowed(Book book) {
        return borrowed.contains(book);
    }

    private boolean isCardStillValid() {
        Date currentDate = new Date();
        return ExpiryDate.after(currentDate);
    }

    private boolean isBookAvailableForBorrowing(Book book) {
        // Implement logic to check book availability
        return true; // Placeholder, update as needed
    }

    private boolean noPendingFine() {
        return fine == 0.0;
    }

}
