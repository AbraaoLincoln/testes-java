package maonamassa4;

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
			{1230, 123},
			{7899, 1184.85},
			{14755, 2951},
			{999, 0}
		};
	}
	
	@Test
	public void testCalculaImposto() {
		double delta = 0.0;
		assertEquals(this.valorImpostoEsperado, CalculoImpostoRenda.calculaImposto(this.valorCompra), delta);
	}

}
