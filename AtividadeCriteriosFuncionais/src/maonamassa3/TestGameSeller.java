package maonamassa3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestGameSeller {
	private int tipoJogo;
	private double valor;
	private double valorEsperado;
	private boolean lancaExec;
	
	public TestGameSeller(int tipoJogo, double valor, double valorEsperado, boolean lancaExec) {
		this.tipoJogo = tipoJogo;
		this.valor = valor;
		this.valorEsperado = valorEsperado;
		this.lancaExec = lancaExec;
	}



	@Parameters(name= "linha: {index}: tipo do jogo[{0}], valor[{1}], valor esperado[{2}]")
	public static Object[][] gerarValorresTipoJogoEValor() {
		return new Object[][] {
			{0, 0.01, 0, true},
			{1, 0, 0,true},
			{2, -0.01, 0, true},
			{3, 99.99, 129.99, false},
			{4, 10, 0, true},
			{1, -0.01, 0, true},
			{1, 0, 0, true},
			{1, 0.01, 30.01, false},
			{1, 199.99, 229.99, false},
			{1, 200, 0, false},
			{1, 200.01, 200.01, false},
			{2, 0, 0, true},
			{2, 0.01, 0.01, false},
			{3, 100, 130, false},
			{3, 100.01, 120.01, false},
			{3, -0.01, 0, true},
			{3, 0, 0, true},
			{3, 0.01, 30.01, true}
		};
	}
	
	@Test
	public void testeCalculaPreco() throws ArgumentoInvalidoException {
		GameSeller gs = new GameSeller();
		
		if(this.lancaExec) {
			try {
				gs.calculaPreco(this.tipoJogo, this.valor);
				fail("Não lançou a exceção");
			} catch (ArgumentoInvalidoException e) {
				
			}
		}else {
			double delta = 0.01;
			assertEquals(this.valorEsperado, gs.calculaPreco(this.tipoJogo, this.valor), delta);
		}
	}
}
