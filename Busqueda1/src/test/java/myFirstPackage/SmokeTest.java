package myFirstPackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SmokeTest {
	public static void main(String[] args) {

		/*
		 * Pasos a automatizar 
		 * 1. Abrir navegador Chrome 
		 * 2. Navegar a la URL https://parabank.parasoft.com/parabank/index.htm
		 * 3. Ingresar nombre de usuario => Encontrar Username e Ingresar el texto 
		 * 4. Ingresar password 
		 * 5. Click en el boton submit 
		 * 6. Validación (¿Login exitoso?) 
		 * 
		 * a.
		 * Titulo de página es de la cuenta con login.
		 */
		
		
		String testUserName = System.getenv("TEST_USERNAME");

		// Configuración chromedriver path setting
		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// 1. Open Chrome
		driver.get("https://google.com");
		// 2. Navegar a la URL

		// driver.get("https://parabank.parasoft.com");

		WebElement busqueda = driver.findElement(By.name("q"));
		busqueda.sendKeys("parabank");
		busqueda.sendKeys(Keys.ENTER);

		WebElement enlace = driver.findElement(By.xpath("//span[contains(text(),'ParaBank - Parasoft')]"));
		enlace.click();

		// 3. Ingresar usuario
		WebElement oUserName = driver.findElement(By.name("username"));
		oUserName.sendKeys("john");

		// Ingresar Password
		WebElement oPassword = driver.findElement(By.name("password"));
		oPassword.sendKeys("demo");
		// 5. Submit XPATH
		// *[@id="loginPanel"]/form/div[3]/input

		WebElement oSubmit = driver.findElement(By.xpath("//input[@value = 'Log In']"));
		oSubmit.click();

		// 6. Validacion titulo
		String expected = "ParaBank | Accounts Overview";
		String actual = driver.getTitle();
		if (expected.equals(actual)) {
			System.out.println("Login Exitoso.");
		} else {
			System.out.println("Login Fallo.");
		}

	}

}
