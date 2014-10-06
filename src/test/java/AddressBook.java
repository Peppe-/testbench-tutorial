import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.TableElement;
import org.openqa.selenium.WebDriver;

public class AddressBook extends TestBenchTestCase {

    public AddressBook(WebDriver driver) {
        setDriver(driver);
    }

    public String getLastNameAtIndex() {
        return $(TableElement.class).first().getCell(0, 1).getText();
    }

    public String getFirstNameAtIndex(int index) {
        return $(TableElement.class).first().getCell(index, 0).getText();
    }

    public EntryForm selectEntryAtIndex(int index) {
        $(TableElement.class).first().getCell(index, 0).click();
        return new EntryForm(getDriver());
    }

    public EntryForm createNewEntry() {
        $(ButtonElement.class).caption("New").first().click();
        return new EntryForm(getDriver());
    }
}
