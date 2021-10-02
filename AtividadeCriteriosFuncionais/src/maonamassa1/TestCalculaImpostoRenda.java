package maonamassa1;

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
	
	@Parameters
	public static Object[][] gerarValorCampraEImpostoEsperado() {
		return new Object[][] {
			{2500, 2500 * 0.1},
			{900, 0},
			{7599, 7599 * 0.15},
			{12000, 12000 * 0.2}
		};
	}
	
	@Test
	public void testCalculaImposto() {
		double delta = 0.01;
		assertEquals(this.valorImpostoEsperado, CalculoImpostoRenda.calculaImposto(this.valorCompra), delta);
	}

}
