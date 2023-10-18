
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
    public void initializeLibraryCard() {
        // create new LibraryCard with a student, valid dates, and no books or fines
        Student student = new Student("Vanessa Smith", 1234567);
        Date issueDate = new Date();
        Date expiryDate = new Date(System.currentTimeMillis() + 10000000); // Set an expiry date in the future
        libraryCard = new LibraryCard(student, issueDate, expiryDate, 1);
    }

}
