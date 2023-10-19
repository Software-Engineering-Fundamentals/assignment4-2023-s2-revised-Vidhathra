
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;

/**
 * Implement and test {Programme.addStudent } that respects the considtion given
 * the assignment specification
 * NOTE: You are expected to verify that the constraints to borrow a new book
 * from a library
 *
 * Each test criteria must be in an independent test method .
 *
 * Initialize the test object with "setting" method.
 */
public class IssueBook {

    private LibraryCard libraryCard;

    // setting method
    @BeforeEach
    public void initializeLibraryCard() {
        // create new LibraryCard with a student, valid dates, and no books or fines
        Student student = new Student("Vanessa Smith", 1234567);
        Date issueDate = new Date();
        Date expiryDate = new Date(System.currentTimeMillis() + 10000000); // Set an expiry date in the future
        libraryCard = new LibraryCard(student, issueDate, expiryDate, 1);
    }

    // test issueBook to check if book has been borrowed
    @Test
    public void issueBook_IfBookBorrowed_False() {
        Book book1 = new Book(743, "Book One", 1);
        // book1.setStatus(true);
        // borrow book1
        libraryCard.issueBook(book1);

        // attempt borrowing book1 again
        assertEquals(false, libraryCard.issueBook(book1));

    }

    @Test // passes if number of books not exceeding 4
    public void issueBook_IfBorrowLimit_True() {
        Book book1 = new Book(743, "Book One", 1);

        assertEquals(true, libraryCard.issueBook((book1)));
        assertEquals(true, libraryCard.getBooks().contains(book1));
    }

    @Test // passes if issueBook returns false and doesnt allow book5 to be issued
    public void issueBook_IfGreaterThanBorrowLimit_False() {
        Book book1 = new Book(123, "Book1", 0);
        Book book2 = new Book(456, "Book2", 0);
        Book book3 = new Book(234, "Book3", 0);
        Book book4 = new Book(890, "Book4", 0);
        Book book5 = new Book(453, "Book5", 0);

        // borrow 4 books
        libraryCard.issueBook(book1);
        libraryCard.issueBook(book2);
        libraryCard.issueBook(book3);
        libraryCard.issueBook(book4);

        // Attempt to borrow a fifth book
        assertEquals(false, libraryCard.issueBook(book5));
    }

}
