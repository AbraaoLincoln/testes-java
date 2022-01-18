import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Predicate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TesteLattes {
	  private WebDriver driver;
	  private WebDriverWait wait;
	  private String driverPath = "/home/abraao/Downloads/bti/teste2/drivers/geckodriver";
	  private Random rand;
	  
	  @Before
	  public void setUp() {
		  System.setProperty("webdriver.gecko.driver", driverPath);		
		  driver = new FirefoxDriver();
		  wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		  rand = new Random();
	  }
	  
	  @After
	  public void tearDow() {
		  driver.quit();
	  }
	  
	  @Test
	  public void testeBuscaTodas() {
		  final String pesquisa = "pesquisa";
		  final String energia = "energia";
		  
		  carregarPaginaBuscaAvancada();
		  
		  driver.findElement(By.id("textoBuscaTodas")).sendKeys(pesquisa + " " + energia);
		  
		  driver.findElement(By.id("botaoBuscaFiltros")).click();
		  
		  escolherCurriculoAleatorio();
		  mudarParaTabDoCurriculo();

		  assertTrue(driver.getPageSource().toLowerCase().contains(pesquisa) && driver.getPageSource().toLowerCase().contains(energia));

	  }
	  
	  @Test
	  public void testeBuscaFrase() {
		  final String frase = "internet das coisas";
		  
		  carregarPaginaBuscaAvancada();
		  
		  driver.findElement(By.id("textoBuscaFrase")).sendKeys(frase);
		  
		  driver.findElement(By.id("botaoBuscaFiltros")).click();
		  
		  escolherCurriculoAleatorio();
		  mudarParaTabDoCurriculo();

		  assertTrue(driver.getPageSource().toLowerCase().contains(frase));

	  }
	  
	  @Test
	  public void testeBuscaQualquer() {
		  final String tutor = "tutor";
		  final String professor = "professor";
		  
		  carregarPaginaBuscaAvancada();
		  
		  driver.findElement(By.id("textoBuscaQualquer")).sendKeys(tutor + " " + professor);
		  
		  driver.findElement(By.id("botaoBuscaFiltros")).click();
		  
		  escolherCurriculoAleatorio();
		  mudarParaTabDoCurriculo();

		  assertTrue(driver.getPageSource().toLowerCase().contains(tutor) || driver.getPageSource().toLowerCase().contains(professor));

	  }
	  
	  @Test
	  public void testeBuscaNenhuma() {
		  final String youtube = "youtube";
		  final String biblioteca = "biblioteca";
		  
		  carregarPaginaBuscaAvancada();
		  
		  driver.findElement(By.id("textoBuscaNenhuma")).sendKeys(youtube + " " + biblioteca);
		  
		  driver.findElement(By.id("botaoBuscaFiltros")).click();
		  
		  escolherCurriculoAleatorio();
		  mudarParaTabDoCurriculo();

		  assertFalse(driver.getPageSource().toLowerCase().contains(youtube));
		  assertFalse(driver.getPageSource().toLowerCase().contains(biblioteca));

	  }
	  
	  @Test
	  public void testeBuscaCurricosQueTemENaoTemAMesmaPalavra() {
		  final String palavra = "youtube";
		  
		  carregarPaginaBuscaAvancada();
		  
		  driver.findElement(By.id("textoBuscaTodas")).sendKeys(palavra);
		  driver.findElement(By.id("textoBuscaNenhuma")).sendKeys(palavra);
		  
		  driver.findElement(By.id("botaoBuscaFiltros")).click();
		  
		  wait.until(ExpectedConditions.presenceOfElementLocated(By.className("resultado"))); 
		  List<WebElement> resultado = driver.findElement(By.className("resultado")).findElements(By.tagName("li"));
		  
		  assertTrue(resultado.size() == 0);
		  
	  }
	  
	  @Test
	  public void testeBuscaCurricosQueTemFraseExataENaoTemPalavraDaFrase() {
		  final String frase = "pesquisa  terra";
		  final String palavra = "terra";
		  
		  carregarPaginaBuscaAvancada();
		  
		  driver.findElement(By.id("textoBuscaFrase")).sendKeys(frase);
		  driver.findElement(By.id("textoBuscaNenhuma")).sendKeys(palavra);
		  
		  driver.findElement(By.id("botaoBuscaFiltros")).click();
		  
		  wait.until(ExpectedConditions.presenceOfElementLocated(By.className("resultado"))); 
		  List<WebElement> resultado = driver.findElement(By.className("resultado")).findElements(By.tagName("li"));
		  
		  assertTrue(resultado.size() == 0);
		  
	  }
	  
	  private void carregarPaginaBuscaAvancada() {
		  System.out.println("Iniciando o browser firefox");
		  
		  driver.get("http://buscatextual.cnpq.br/buscatextual/busca.do?metodo=apresentar");
		  
		  WebElement element = driver.findElement(By.id("tit_simples"));
		  element.findElement(By.tagName("a")).click();
	  }
	  
	  private void escolherCurriculoAleatorio() {
		  wait.until(ExpectedConditions.presenceOfElementLocated(By.id("busca2_simples"))); 
		  List<WebElement> lis = driver.findElements(By.tagName("li"));
		  
		  lis.get(rand.nextInt(lis.size())).findElement(By.tagName("b")).findElement(By.tagName("a")).click();

		  wait.until(ExpectedConditions.elementToBeClickable(By.id("idbtnabrircurriculo")));
		  driver.findElement(By.id("idbtnabrircurriculo")).click();
	  }
	  
	  private void mudarParaTabDoCurriculo() {
		  wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		  List<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		  driver.switchTo().window(tabs.get(1));
		  
		  wait.until(ExpectedConditions.presenceOfElementLocated(By.className("nome"))); 
		  System.out.println(driver.findElement(By.className("nome")).getText());
	  }
}
