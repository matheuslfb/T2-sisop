import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Gerente {

	private Bloco memoria;
	private LinkedList<Bloco> memoriaLivre;
	private LinkedList<Bloco> memoriaOcupada;

	// variaveis auxiliares para manipular a Memoria

	private int memTotal, memDisponivel;

	private Bloco atual;

	public Gerente(int start, int end) {
		this.memoria = new Bloco(start, end);
		memoriaOcupada = new LinkedList();
		memoriaLivre = new LinkedList();

		addBlocoMemoriaOcupada(memoria);
	}

	public int getMemoriaTotal() {
		for (Bloco b : memoriaLivre) {
			memTotal += b.getStart() - b.getEnd();
		}
		return memTotal;
	}

	//adiciona o bloco, atravez da solicitacao 
	public void addBlocoMemoriaOcupada(int valor) {
		if (verificaMemoria()) {
			Bloco b = new Bloco();

			atualizaMemoria(b);
			memoriaOcupada.add(b);
		}
	}

	
	//Atualiza memoria disponivel
	public void atualizaMemoria(Bloco b) { 
		atual.setStart(atual.getStart() + b.getStart());
	}

	
	//procura se o bloco existe e se esta na lista de memoria ocupada
	public boolean searchBlocoMemOcupada(Bloco b) {
		for (Bloco x : memoriaOcupada) {
			if (b.getID() == b.getID()) {
				return true;
			}
		}
		return false;
	}

	// public boolean removeBlocoMemoriaOcupada(Bloco b) {
	// if(searchBlocoMemOcupada(b)) {
	//
	// }
	// }

	public int getMemTotal() {
		return memTotal;
	}

	public int getMemDisponivel() {
		return memDisponivel;
	}

	public void liberaMemTotal(int val) {
		this.memDisponivel = this.memDisponivel + val;
	}

	public void diminuiMemTotal(int val) {
		this.memDisponivel = this.memDisponivel - val;
	}

	public void printBlocos() {
		for (Bloco b : memoriaOcupada) {
			System.out.println(" " + b.toString());
		}
	}

}
