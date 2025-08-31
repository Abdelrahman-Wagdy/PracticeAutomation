package support;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Config;

import java.time.Duration;
import java.util.List;

public class BrowserActions {
    private final static String URL = Config.get("url");


    public enum TypeOfElement {
        ID("id"),
        CSS_SELECTOR("css selector"),
        XPATH("xpath"),
        NAME("name"),
        CLASSNAME("classname");
        private final String typeValue;
        TypeOfElement(String typeValue){
            this.typeValue=typeValue;
        }
    }
    public static WebElement getElement(String element,TypeOfElement typeOfElement){
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(5));
        return switch (typeOfElement.typeValue) {
            case "id" -> wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(element)));
            case "css selector" -> wait.until(ExpectedConditions
                    .visibilityOfElementLocated(By.cssSelector(element)));
            case "xpath" -> wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
            case "classname" -> wait.until(ExpectedConditions
                    .visibilityOfElementLocated(By.className(element)));
            default -> wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(element)));
        };
    }

    public static void scrollToElement(String element, TypeOfElement typeOfElement){
        WebElement webElement = getElement(element, typeOfElement);
        ((JavascriptExecutor) DriverFactory.getDriver())
                .executeScript("arguments[0].scrollIntoView(true);", webElement);
    }
    public static void click(String element,TypeOfElement typeOfElement){
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(getElement(element, typeOfElement))).click();
    }
    public static void fillData(String element,TypeOfElement typeOfElement, String data){
        getElement(element, typeOfElement).sendKeys(data);
    }
    public static void closeBrowser(){
        DriverFactory.getDriver().quit();
    }
    public static void navigateToURL(){
        DriverFactory.getDriver().navigate().to(URL);
    }
    public static String getURL(){
        return DriverFactory.getDriver().getCurrentUrl();
    }
    public static List<WebElement> getElements(String element, TypeOfElement typeOfElement){
        return switch (typeOfElement) {
            case ID -> DriverFactory.getDriver().findElements(By.id(element));
            case CSS_SELECTOR -> DriverFactory.getDriver().findElements(By.cssSelector(element));
            case XPATH -> DriverFactory.getDriver().findElements(By.xpath(element));
            case CLASSNAME -> DriverFactory.getDriver().findElements(By.className(element));
            default -> DriverFactory.getDriver().findElements(By.name(element));
        };
    }
}
