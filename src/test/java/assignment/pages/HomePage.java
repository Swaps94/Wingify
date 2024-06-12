package assignment.pages;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import assignment.base.Base;

public class HomePage extends Base {

    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//table[@id='transactionsTable']")
    private WebElement transactionTable;

    @FindBy(xpath = "//th[@id='amount']")
    private WebElement amountColumn;

    public String verifyUrl() {
        return driver.getCurrentUrl();
    }

    public String verifyTitle() {
        return driver.getTitle();
    }

   

    
    private double parseAmount(String amount) {
        return Double.parseDouble(amount.replaceAll("[^\\d.-]", ""));
    }

    private String formatAmount(double amount) {
        DecimalFormat df = new DecimalFormat("+ #,##0.00 USD;- #,##0.00 USD");
        return df.format(amount);
    }

    public String[] sortedData() {
        List<WebElement> data = driver.findElements(By.xpath("//table[@id='transactionsTable']/tbody/tr/td[5]"));
        double[] amounts = data.stream().mapToDouble(e -> parseAmount(e.getText().trim())).toArray();
        Arrays.sort(amounts);
        return Arrays.stream(amounts).mapToObj(this::formatAmount).toArray(String[]::new);
    }


    public String[] extractData() {
        List<WebElement> amountData = driver.findElements(By.xpath("//table[@id='transactionsTable']/tbody/tr/td[5]"));
        String[] beforeSort = new String[amountData.size()];
        System.out.println("----------------Before Clicking Amount--------------");
        for (int i = 0; i < amountData.size(); i++) {
            System.out.println(beforeSort[i] = amountData.get(i).getText().toString().trim());
        }
        return beforeSort;
    }

    public String[] perfromSort() throws InterruptedException {
    	
        amountColumn.click();
        Thread.sleep(3000);
        List<WebElement> sortedData = driver.findElements(By.xpath("//table[@id='transactionsTable']/tbody/tr/td[5]"));

        String[] afterSort = new String[sortedData.size()];
        
        System.out.println("----------------After Clicking Amount--------------");
        
        for (int i = 0; i < sortedData.size(); i++) {
            System.out.println(afterSort[i] = sortedData.get(i).getText().toString().trim());
        }

        return afterSort;
    }
}
