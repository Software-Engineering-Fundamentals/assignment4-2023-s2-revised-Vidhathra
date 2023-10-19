
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
        Date expiryDate = new Date(System.currentTimeMillis() + (24 * 60 * 60 * 1000)); // 24 hrs in the future
        libraryCard = new LibraryCard(student, issueDate, expiryDate, 1);
    }

    @Test // tests to see if getter method correctly return amount of books borrowed
    public void issueBook_IfBookBorrowed_False() throws IllegalBookIssueException {
        Book book1 = new Book(743, "Book1", 1);
        Book book2 = new Book(456, "Book2", 0);
        Book book3 = new Book(234, "Book3", 0);

        libraryCard.issueBook(book1);
        libraryCard.issueBook(book2);
        libraryCard.issueBook(book3);

        assertEquals(3, libraryCard.getBooks().size());
    }

    @Test // passes if number of books not exceeding 4
    public void issueBook_IfBorrowLimit_True() throws IllegalBookIssueException {
        Book book1 = new Book(743, "Book One", 1);

        assertEquals(true, libraryCard.issueBook((book1)));
        assertEquals(true, libraryCard.getBooks().contains(book1));
    }

    @Test // passes if issueBook returns false and doesnt allow book5 to be issued
    public void issueBook_IfGreaterThanBorrowLimit_False() throws IllegalBookIssueException {
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

    @Test // passes when an exception is thrown when try to borrow book already issued
    public void throwsException_IfBookAlreadyBorrowed() throws IllegalBookIssueException {
        Book book1 = new Book(123, "Book1", 0);

        libraryCard.issueBook(book1);

        assertThrows(IllegalBookIssueException.class, () -> libraryCard.issueBook(book1));
    }

    @Test // check if card is valid, passes if invalid card causes issueBook to return
          // false
    public void false_IfCardInvalid() throws IllegalBookIssueException {
        Book book1 = new Book(123, "Book1", 0);
        libraryCard.setExpiryDate(new Date(System.currentTimeMillis() - (24 * 60 * 60 * 1000))); // 24 hrs expired

        assertEquals(false, libraryCard.issueBook(book1));
    }

    @Test // check if book is available, passes if issueBook returns false due to
          // bookunavailabilty
    public void false_IfBookUnavailable() throws IllegalBookIssueException {
        Book book6 = new Book(375, "Book6", 0);
        book6.setStatus(false);

        assertEquals(false, libraryCard.issueBook(book6));
    }

    @Test // check if card has fines, passes if returns false due to fines
    public void false_IfCardHasFines() throws IllegalBookIssueException {
        Book book1 = new Book(123, "Book1", 0);
        libraryCard.setFine(3.5);

        assertEquals(false, libraryCard.issueBook(book1));
    }

    @Test // check if issue days is 3 for books in high demand
    public void three_IfBookHighDemand() throws IllegalBookIssueException {
        Book book1 = new Book(123, "Book1", 1);

        libraryCard.issueBook(book1);

        assertEquals(3, book1.days());
    }

    @Test // check if issue days is 3 for books in high demand
    public void fifteen_IfBookHighDemand() throws IllegalBookIssueException {
        Book book1 = new Book(123, "Book1", 0);

        libraryCard.issueBook(book1);

        assertEquals(15, book1.days());
    }
}
