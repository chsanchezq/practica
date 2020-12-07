package myFirstPackage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class busquedaPilsen {

	public static void main(String[] args) throws InterruptedException {
		
		
		
		
		// Configuración chromedriver
		Map prefs = new HashMap();
		prefs.put("profile.default_content_settings.cookies", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// 1. Open Chrome
		driver.get("https://google.com");
		// 2. Navegar a la URL
		WebElement search = driver.findElement(By.name("q"));
		search.sendKeys("pilsen");
		search.sendKeys(Keys.ENTER);
		WebElement link = driver.findElement(By.xpath("//span[contains(text(),'Disfrutemos')]"));
		link.click();
		// 3. Ingresar datos para entrar
		WebElement day = driver.findElement(By.id("age_checker_day"));
		day.sendKeys("01");
		WebElement month = driver.findElement(By.id("age_checker_month"));
		month.sendKeys("01");
		WebElement year = driver.findElement(By.id("age_checker_year"));
		year.sendKeys("1980");
		
		//4. Presionar el boton Aceotar cookies 
		WebElement buttonAcept = driver.findElement(By.xpath("//button[contains(text(),'Aceptar')]"));
		JavascriptExecutor executorbuttonAcept = (JavascriptExecutor)driver;
		executorbuttonAcept.executeScript("arguments[0].click();", buttonAcept);
		WebElement submit = driver.findElement(By.xpath("//input[@value = 'Entrar']"));
		submit.click();
		Thread.sleep(1500);
		
		//5. Navegar al formulario de registro
		WebElement enlaceRegistrate = driver.findElement(By.xpath("//li/a[contains(@href,'trate')]"));
		enlaceRegistrate.click();
		Thread.sleep(1500);
		//Ingresar datos
		WebElement signupButton = driver.findElement(By.id("signupButtonToggler"));
		
		signupButton.click();
		WebElement name = driver.findElement(By.id("edit-title"));
		name.sendKeys("Oregano");
		WebElement lastname = driver.findElement(By.id("edit-field-apellidos-und-0-value"));
		lastname.sendKeys("Jones");
		WebElement dni = driver.findElement(By.id("edit-field-dni-und-0-value"));
		dni.sendKeys("12177741");
		Select genero = new Select(driver.findElement(By.id("edit-field-g-nero-und")));
		genero.selectByValue("Masculino");
		Select dia = new Select(driver.findElement(By.id("edit-field-fecha-de-nacimiento-und-0-value-day")));
		dia.selectByValue("1");
		Select mes = new Select(driver.findElement(By.id("edit-field-fecha-de-nacimiento-und-0-value-month")));
		mes.selectByValue("1");
		Select ano = new Select(driver.findElement(By.id("edit-field-fecha-de-nacimiento-und-0-value-year")));
		ano.selectByValue("1980");
		Select ciudad = new Select(driver.findElement(By.id("edit-field-ciudad-und")));
		ciudad.selectByVisibleText("Bagua");
		WebElement email = driver.findElement(By.id("email"));
		email.sendKeys("inventado01@gmail.com");
		WebElement telefono = driver.findElement(By.id("edit-field-tel-fono-m-vil-und-0-value"));
		telefono.sendKeys("99912399");
		WebElement terminos = driver.findElement(By.id("terms"));
		JavascriptExecutor executorTerminos = (JavascriptExecutor)driver;
		executorTerminos.executeScript("arguments[0].click();", terminos);
		WebElement marketing = driver.findElement(By.id("edit-field-marketing-und"));
		JavascriptExecutor executormarketing = (JavascriptExecutor)driver;
		executormarketing.executeScript("arguments[0].click();", marketing);
		WebElement botonApuntate = driver.findElement(By.id("contacto-node-form"));
		JavascriptExecutor executorBotonApuntate = (JavascriptExecutor)driver;
		executorBotonApuntate.executeScript("arguments[0].submit();", botonApuntate);
		
		// 6. Validación de Registro
		WebElement confirmacion = driver.findElement(By.xpath("//h2[@class='home_sec4__medium']"));
		System.out.println(confirmacion.getText().replaceAll("\n"," "));
		String textoConfirmacion = confirmacion.getText().replaceAll("\n"," ");
		
		if(textoConfirmacion.equals("GRACIAS POR REGISTRARTE")) {
			System.out.println("Registro exitoso!!!!");
		} else {
			System.out.println("Registro fallido!!!!");
		}
		
	}
	
}
