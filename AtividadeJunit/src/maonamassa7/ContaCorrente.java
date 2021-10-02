package maonamassa7;

import java.util.ArrayList;
import java.util.List;

public class ContaCorrente {
	private int saldoInicial;
	private int saldoFinal;
	private String nomeDonoConta;
	private List<Deposito> depositosFeitos;

	public ContaCorrente() {
		this.saldoInicial = 0;
		this.saldoFinal = 0;
		this.nomeDonoConta = "James Grenning";
		this.depositosFeitos = new ArrayList<Deposito>();
	}
	
	public ContaCorrente(int saldoInicial) {
		this.saldoInicial = saldoInicial;
		this.saldoFinal = saldoInicial;
		this.depositosFeitos = new ArrayList<Deposito>();
	}
	
	public ContaCorrente(String nomeDonoConta) {
		this.nomeDonoConta = nomeDonoConta;
		this.depositosFeitos = new ArrayList<Deposito>();
	}
	
	public ContaCorrente(String nomeDonoConta, int saldoInicial) {
		this.nomeDonoConta = nomeDonoConta;
		this.saldoInicial = saldoInicial;
		this.saldoFinal = saldoInicial;
		this.depositosFeitos = new ArrayList<Deposito>();
	}
	
	public int saldo() {
		return this.saldoFinal;
	}
	
	public void creditar(Deposito d) {
		this.saldoFinal += d.valor();
		this.depositosFeitos.add(d);
	}
	
	public String extrato() {
		return "Conta de " + this.nomeDonoConta +"\n" +
				"Saldo Inicial $" + this.saldoInicial + "\n" +
				"Saldo Final $" + this.saldoFinal + "\n" +
				this.historicoDepositos();
	}
	
	private String historicoDepositos() {
		
		if(depositosFeitos.size() == 0) {
			return "Nenhuma trasacao realizada\n";
		}else {
			String depositosFeitosString = "";
			for(Deposito d : this.depositosFeitos) {
				depositosFeitosString += d.data() + "\tDeposito\t$" + d.valor() +"\n";
			}
			return depositosFeitosString;
		}
		
	}
}
