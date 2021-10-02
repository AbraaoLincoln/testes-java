package maonamassa3;

public class GameSeller {
	public static int JOGO_TABULEIRO = 1;
	public static int JOGO_STREAM = 2;
	public static int JOGO_DVD = 3;
	
	/*
	Este método retorna o preço calculado para um Jogo comprado na loja virtual GameSeller. Se o Jogo
	for de tabuleiro a cobrança do frete deve ser de 30 reais em todas as compras abaixo de 200 reais,
	caso contrário o frete será grátis. Se o jogo for STREAM nunca será comprado frete. Se o jogo for em
	DVD o valor de frete será de 30 reais (para compras até 100 reais) e de 20 reais para compras
	superiores a 100 reais. Caso algum dos parâmetros seja invalido o método deve lançar
	ArgumentoInvalidoException */
	public double calculaPreco (int tipoJogo, double valor) throws ArgumentoInvalidoException {
		return 0;
	}
}
