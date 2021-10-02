package maonamassa2;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestCalculaImpostoRenda {
	
	private double valorCompra;
	private double valorImpostoEsperado;
	
	public TestCalculaImpostoRenda(double valorCompra, double valorImpostoEsperado) {
		this.valorCompra = valorCompra;
		this.valorImpostoEsperado = valorImpostoEsperado;
	}
	
	@Parameters(name= "linha: {index}: valor da compra[{0}] : imposto[{1}]")
	public static Object[][] gerarValorCampraEImpostoEsperado() {
		return new Object[][] {
			{-0.01, 0},
			{0, 0},
			{0.01, 0.01 * 0.1},
			{1200.99, 0},
			{1201, 1201 * 0.1},
			{1201.01, 1201 * 0.1},
			{4999.99, 4999.99 * 0.1},
			{5000, 5000 * 0.1},
			{5000.01, 0},
			{5000.99, 0},
			{5001, 5001 * 0.15},
			{5001.01, 5001.01 * 0.15},
			{9999.99, 9999.99 * 0.15},
			{10000, 10000 * 0.15},
			{10000.01, 10000.01 * 0.2}
		};
	}
	
	@Test
	public void testCalculaImposto() {
		double delta = 0.001;
		assertEquals(this.valorImpostoEsperado, CalculoImpostoRenda.calculaImposto(this.valorCompra), delta);
	}

}
