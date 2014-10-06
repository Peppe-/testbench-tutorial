import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbench.elements.TextFieldElement;
import org.openqa.selenium.WebDriver;

public class EntryForm extends TestBenchTestCase {
    public EntryForm(WebDriver driver) {
        setDriver(driver);
    }

    public String getLastName() {
        return $(TextFieldElement.class)
                .caption("Last Name").first().getValue();
    }

    public String getFirstName() {
        return $(TextFieldElement.class)
                .caption("First Name").first().getValue();
    }

    public void setLastName(String lastName) {
        $(TextFieldElement.class).caption("Last Name").first().setValue(lastName);
    }

    public void setFirstName(String firstName) {
        $(TextFieldElement.class).caption("First Name").first().setValue(firstName);
    }
}
