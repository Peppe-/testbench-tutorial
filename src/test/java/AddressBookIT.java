import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.TableElement;
import com.vaadin.testbench.elements.TextFieldElement;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AddressBookIT extends TestBenchTestCase {
    @Before
    public void setUp() throws Exception {
        setDriver(new FirefoxDriver());
    }

    @After
    public void tearDown() throws Exception {
        getDriver().quit();
    }

    @Test
    public void testAddressBook() {
        getDriver().get("http://localhost:8080/");
        Assert.assertTrue($(TableElement.class).exists());
    }

    @Test
    public void testFormShowsCorrectData() {
        getDriver().get("http://localhost:8080/");
        // 1. Find the Table
        TableElement table = $(TableElement.class).first();
        // 2. Store the first name and last name values shown
        // in the first row of the table for later comparison
        String firstName = table.getCell(0, 0).getText();
        String lastName = table.getCell(0, 1).getText();
        // 3. Click on the first row
        table.getCell(0, 0).click();
        // 4. Assert that the values in the first name and
        // last name fields are the same as in the table
        Assert.assertEquals(firstName, $(TextFieldElement.class)
                .caption("First Name").first().getValue());
        Assert.assertEquals(lastName, $(TextFieldElement.class)
                .caption("Last Name").first().getValue());
    }

    @Test
    public void testEnterNew() {
        getDriver().get("http://localhost:8080/");

        AddressBook addressBook = new AddressBook(getDriver());

        EntryForm form = addressBook.createNewEntry();
        form.setFirstName("Tyler");
        form.setLastName("Durden");

        // Select some other entry
        form = addressBook.selectEntryAtIndex(6);

        // Assert that the entered name is not in the text fields any longer
        Assert.assertNotEquals("Tyler", form.getFirstName());
        Assert.assertNotEquals("Durden", form.getLastName());

        // Verify that the first row and form contain "Tyler Durden"
        form = addressBook.selectEntryAtIndex(0);
        Assert.assertEquals("Tyler", addressBook.getFirstNameAtIndex(0));
        Assert.assertEquals("Durden", addressBook.getLastNameAtIndex());
        Assert.assertEquals("Tyler", form.getFirstName());
        Assert.assertEquals("Durden", form.getLastName());
    }
}
