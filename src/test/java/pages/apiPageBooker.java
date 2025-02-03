package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class apiPageBooker {
    WebDriver driver;

    @FindBy(xpath = "//*[@id=\"scrollingNav\"]/ul/li[2]/a")
    public WebElement crateToken; //PageFactory ise ByFind to sore web elemnt instead of FindEDlement

    @FindBy(xpath = "//*[@id=\"examples-Auth-CreateToken-1_0_0-0\"]/pre/code/span[7]")
    public WebElement username;

    @FindBy(xpath = "//*[@id=\"examples-Auth-CreateToken-1_0_0-0\"]/pre/code/span[11]")
    public WebElement password;


    public apiPageBooker(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initialize WebElements using PageFactory
    }
}
