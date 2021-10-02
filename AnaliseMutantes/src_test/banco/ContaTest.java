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

	@Test
	public void testDebitarSucesso() throws OperacaoIlegalException{
		double resultado = c1.debitar(10);
		
		assertEquals(40,resultado,0.0);
		assertEquals(40,c1.getSaldo(),0.0);
	} 
	
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
	
	//Teste adicionados para matar os bugs inseridos pelo pitest
	
	
	@Test
	public void testTranferirValorIgualSaldo() throws OperacaoIlegalException{
		double delta = 0.0;
		
		c1.transferir(c2, 50);
		
		assertEquals(120, c2.getSaldo(), delta);
	}	

	@Test
	public void testTranferirValorZer0() throws OperacaoIlegalException{
		double delta = 0.0;
		
		try {
			c1.transferir(c2, 0);
			fail("Valor invalido creditado a conta");
		} catch (OperacaoIlegalException e) {
			assertEquals(50, c1.getSaldo(), delta);
			assertEquals(70, c2.getSaldo(), delta);
		}
	}
	
	@Test
	public void testCreditarSucesso() throws OperacaoIlegalException{
		double resultado = c1.creditar(10);
		
		assertEquals(60,resultado,0.0);
		assertEquals(60,c1.getSaldo(),0.0);
	}
	
	@Test
	public void testCreditarFalha() {
		double delta = 0.0;
		
		try {
			c1.creditar(0);
			fail("Valor invalido creditado a conta");
		} catch (OperacaoIlegalException e) {
			assertEquals(50, c1.getSaldo(), delta);
		}
	}

}
