package ejercicioMysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class tablaPaginacion {
public static void main(String[] args) throws Exception {
		
		Logger logger = LogManager.getLogger("CapturarTablaPaginacion");
		PropertyConfigurator.configure("Log4j.properties");
		
		WebDriver driver = new ChromeDriver();
		logger.info("Carga navegador");
		driver.get("https://datatables.net/examples/data_sources/js_array.html");
		logger.info("Envia la direccion URL");
		driver.manage().window().maximize();
		
		getConnection();
		logger.info("Conecta con la base de datos");
		
		WebElement table1=driver.findElement(By.id("example"));
		logger.info("Obtiene la tabla con el id example");
		
		
		int paginationSize = driver.findElements(By.cssSelector("#example_paginate > span > a")).size();
		logger.info("Obtiene el numero de paginas");
		
		for(int i=1; i<=paginationSize;i++)
		{logger.info("Hace el recorrido de cada pagina");
		String paginationSelector ="#example_paginate > span > a:nth-child("+i+")";
		logger.info("captura el nombre del cssSelector de la pagina");
		driver.findElement(By.cssSelector(paginationSelector)).click();
		logger.info("Hace click en la pagina");
		List<WebElement>Rows=table1.findElements(By.xpath("//tbody//tr[@role='row']"));
		logger.info("captura todas las fila de la tabla");
		System.out.println("Total filas"+Rows.size());
		logger.info("Imprime en consola el numero de filas");
		String [] valor=new String[7];
		logger.info("Crea un vector de cadena de tamaño de 7 elementos");
		for(WebElement Row:Rows){
			logger.info("hace un recorrido en cada fila");	
			List<WebElement>Cols=Row.findElements(By.tagName("td"));
			logger.info("Almacena en una lista los datos de cada columna de la fila");
			int k=0;
			
			for(WebElement Col:Cols)
			{	logger.info("Hace un recorrido de cada valor de la celda"); 
				
				valor[k]=Col.getText();
				logger.info("Almacena en la matriz cada valor de la celda");
				System.out.println(valor[k]+" "+k);
				k=k+1;
				
			}
			post(valor[0],valor[1],valor[2],valor[3],valor[4],valor[5]);
			logger.info("Inserta los valores cada columna de la fila en la tabla de la base de datos");
			//if(valor[1] != null || valor[2] != null || valor[3] != null) {
				//post(valor[0],valor[1],valor[2],valor[3],valor[4],valor[5]);
			//}
		}
		}

	}
	public static void post(String var1, String var2, String var3,String var4, String var5, String var6) throws Exception{
			
			try {
				Connection con=getConnection();
				PreparedStatement posted = con.prepareStatement("INSERT INTO tercero(Name1,Position1,Office,Exnt,StartDate,Salary) VALUES('"+var1+"','"+var2+"','"+var3+"','"+var4+"','"+var5+"','"+var6+"')");
				posted.executeUpdate();
			}catch(Exception e) {System.out.println(e);}
			finally {
				System.out.println("Fila Insertada.");
			}
			
		}
		public static Connection getConnection() throws Exception{
			try {
				String driver = "com.mysql.jdbc.Driver";
				String url = "jdbc:mysql://localhost:3306/practica";
				String username="";
				String password = "";
				Class.forName(driver);
				Connection conn=DriverManager.getConnection(url,username,password);
				System.out.println("Connected");
				return conn;
			}catch(Exception e)
			{System.out.println(e);}
			return null;
			
		}
}
