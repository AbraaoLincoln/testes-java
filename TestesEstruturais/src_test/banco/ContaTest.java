package banco;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ContaTest {
	
	private Conta c1;
	private Conta c2;
	
	@Before
	public void setUp() {
		c1 = new Conta("123", 50);
		c2 = new Conta("1234", 70);
	}
	
	/* 
	 * Este teste faz com que o primeiro ramo do if do método debitar seja 
	 * executado(quando é tentado debitar um valor positivo) assim fazendo com 
	 * que a instrução dentro do if seja executada. 
	 * 
	 */
	@Test
	public void testDebitarSucesso() throws OperacaoIlegalException{
		Conta c = new Conta("123", 20);
		c.debitar(10);
		assertEquals(10,c.getSaldo(),0.0);
	} 
	
	/*
	 * Código adicionado para atingir os 100% na cobertura por instrução.
	 * 
	 * Este teste faz com que o segundo ramo do if do método debitar seja 
	 * executado(quando é tentado debitar um valor igual a zero ou menor) assim 
	 * fazendo com que a instrução dentro do else seja executada. Com esse 
	 * método de teste e o acima(testDebitarSucesso) todas as instruções do 
	 * método debitar foram executadas.
	 * 
	 */
	@Test
	public void testDebitarFalha() throws OperacaoIlegalException{
		Conta c = new Conta("123", 20);
		
		try {
			c.debitar(0);
			fail("Valor negativo debitado");
		} catch (OperacaoIlegalException e) {
			assertEquals(20,c.getSaldo(),0.0);
		}
	} 
	
	@Test
	public void testTranferirValorValido() throws OperacaoIlegalException{
		double delta = 0.0;
		
		c1.transferir(c2, 30);
		
		assertEquals(100, c2.getSaldo(), delta);
	}
	
	@Test 
	public void testTransferirValorNegativo() {
		double delta = 0.0;
		
		try {
			c1.transferir(c2, -30);
			fail("Valor negativo transferido");
		} catch (OperacaoIlegalException e) {
			assertEquals(50, c1.getSaldo(), delta);
			assertEquals(70, c2.getSaldo(), delta);
		}
		
	}
	
	@Test
	public void testTransferirValorMaiorDQSaldo() {
		double delta = 0.0;
		
		try {
			c1.transferir(c2, 51);
			fail("Valor maior que o saldo da origem transferido");
		} catch (OperacaoIlegalException e) {
			assertEquals(50, c1.getSaldo(), delta);
			assertEquals(70, c2.getSaldo(), delta);
		}
	}
}
