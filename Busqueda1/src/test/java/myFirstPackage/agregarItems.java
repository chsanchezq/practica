package myFirstPackage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class agregarItems {

	public static void main(String[] args) throws InterruptedException {
		/*
		* Pasos a automatizar 
		 * 1. Abrir navegador Chrome 
		 * 2. Navegar a la página de youtube
		 * 3. Ingresar en la busqueda rock de los 80 
		 * 4. Seleccionar la tercera opcion
		 * 6. Validación (¿Selección exitosa?) 
		 * 
		 * a.
		 * Titulo de página del reproductor.
		 */
		
		// Configuración variables de entorno
		String usuario = System.getenv("USER");
		String password = System.getenv("PASSWORD");
		// Configuración chromedriver path setting
		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// 1. Abrir Enlace
		driver.get("https://"+usuario+":"+password+"@todoappfor2020.herokuapp.com/");
		// 2. Agregar item
		WebElement item = driver.findElement(By.id("create-field"));
		item.sendKeys("practice is funny");
		
		WebElement buttonAddItem = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
		buttonAddItem.click();
	
		Thread.sleep(3000);
		List<WebElement> elements = driver.findElements(By.xpath("//li/span"));
		
		System.out.println(elements.size());
		String message ="Agregar item fallo";
		for(int i=0; i< elements.size();i++) {
			System.out.println(elements.get(i).getText());
			if(elements.get(i).getText().equals("practice is funny")) {
				message="Agregar item exitoso";
				//break;
			}
		}
		
		System.out.println(message);
		 
		
	}

}
