package myFirstPackage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class busquedaCanciones {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
		
		// Configuración chromedriver path setting
		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// 1. Open Chrome
		driver.get("https://google.com");
		// 2. Navegar a la URL Youtube
		WebElement busqueda = driver.findElement(By.name("q"));
		busqueda.sendKeys("youtube");
		busqueda.sendKeys(Keys.ENTER);
		WebElement enlace = driver.findElement(By.xpath("//span[contains(text(),'YouTube')]"));
		enlace.click();
		
		WebElement busquedaYoutube = driver.findElement(By.name("search_query"));
		busquedaYoutube.sendKeys("rock de los 80");
		busquedaYoutube.sendKeys(Keys.ENTER);
		WebElement ListaCanciones = driver.findElement(By.id("primary"));
		System.out.println(ListaCanciones);
		List<WebElement> elements = driver.findElements(By.id("video-title"));
		System.out.println("Number of elements:" +elements.size());
		elements.get(4).click();
		
	}

}
