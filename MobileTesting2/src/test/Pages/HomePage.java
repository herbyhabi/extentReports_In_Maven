package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasicPage {

    @FindBy(xpath = "//android.widget.ImageButton[@resource-id = 'com.xiaomi.notes:id/notelist_MenuBar_Add']")
    public WebElement btnOfAdd;

    @FindBy(xpath = "//android.widget.EditText[@resource-id = 'com.xiaomi.notes:id/noteedit_NoteEdit']")
    public WebElement areaOfText;
}
