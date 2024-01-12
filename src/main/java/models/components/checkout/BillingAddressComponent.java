package models.components.checkout;

import models.components.Component;
import models.components.ComponentCSSSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

@ComponentCSSSelector("#opc-billing")
public class BillingAddressComponent extends Component {
    private final static By inputAddressDropdownSel = By.id("billing-address-select");
    private final static By firstNameSel = By.id("BillingNewAddress_FirstName");
    private final static By lastNameSel = By.id("BillingNewAddress_LastName");
    private final static By emailSel = By.id("BillingNewAddress_Email");
    private final static By companySel = By.id("BillingNewAddress_Company");
    private final static By selectCountryDropdownSel = By.id("BillingNewAddress_CountryId");
    private final static By selectStateDropdownSel = By.id("BillingNewAddress_StateProvinceId");
    private final static By loadingStateProgressBarSel = By.id("states-loading-progress");
    private final static By citySel = By.id("BillingNewAddress_City");
    private final static By address1Sel = By.id("BillingNewAddress_Address1");
    private final static By address2Sel = By.id("BillingNewAddress_Address2");
    private final static By zipcodeSel = By.id("BillingNewAddress_ZipPostalCode");
    private final static By phoneNumberSel = By.id("BillingNewAddress_PhoneNumber");
    private final static By faxNumberSel = By.id("BillingNewAddress_FaxNumber");
    private final static By continueBtnSel = By.cssSelector("#billing-buttons-container [class*='new-address-next-step-button']");
    public BillingAddressComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }
    public void selectInputNewAddress(){
        if(!findElements(inputAddressDropdownSel).isEmpty()){
            Select select = new Select(findElement(inputAddressDropdownSel));
            select.selectByVisibleText("New Address");
        }
    }
    public void inputFirstName(String firstName){
        findElement(firstNameSel).sendKeys(firstName);
    }
    public void inputLastName(String lastName){
        findElement(lastNameSel).sendKeys(lastName);
    }
    public void inputEmail(String email){
        findElement(emailSel).sendKeys(email);
    }
    public void inputCompany(String company){
        findElement(companySel).sendKeys(company);
    }
    public void selectCountry(String country){
        Select select = new Select(findElement(selectCountryDropdownSel));
        select.selectByVisibleText(country);
        wait.until(ExpectedConditions.invisibilityOf(findElement(loadingStateProgressBarSel)));
    }
    public void selectState(String state){
        Select select = new Select(findElement(selectStateDropdownSel));
        select.selectByVisibleText(state);
    }
    public void inputCity(String city){
        findElement(citySel).sendKeys(city);
    }
    public void inputAddress1(String address1){
        findElement(address1Sel).sendKeys(address1);
    }
    public void inputAddress2(String address2){
        findElement(address2Sel).sendKeys(address2);
    }
    public void inputZipcode(String zipcode){
        findElement(zipcodeSel).sendKeys(zipcode);
    }
    public void inputPhoneNumber(String phoneNumber){
        findElement(phoneNumberSel).sendKeys(phoneNumber);
    }
    public void inputFaxNumber(String faxNumber){
        findElement(faxNumberSel).sendKeys(faxNumber);
    }
    public void clickContinueBtn( ){
        findElement(continueBtnSel).click();
    }

}
