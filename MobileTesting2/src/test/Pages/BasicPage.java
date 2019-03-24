package Pages;

import org.openqa.selenium.support.PageFactory;
import util.BasicTest;

public class BasicPage {

    public BasicPage() {
        //Initialize PageObject
        PageFactory.initElements(BasicTest.driver, this);
    }


}
