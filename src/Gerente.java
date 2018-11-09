import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Gerente {

	private Bloco blocoInicial;
	private LinkedList<Bloco> memoriaLivre;
	private LinkedList<Bloco> memoriaOcupada;

	// variaveis auxiliares para manipular a Memoria

	private int qntdMemDisponivel;

	public Gerente(Bloco b) {
		memoriaOcupada = new LinkedList();
		memoriaLivre = new LinkedList();

		blocoInicial = b;
		atualizaMemDisponivel();

	}

	public int getMemoriaDisponivel() {
		return qntdMemDisponivel;
	}

	// adiciona o bloco a lista de memoria ocupada
	public void addBlocoMemoriaOcupada(int solicitacao, int ID) {

		Bloco b = new Bloco(blocoInicial.getStart(), blocoInicial.getStart() + solicitacao);
		b.setID(ID);
		System.out.println(b.toString());
		memoriaOcupada.add(b);
		atualizaBlocoInicial(b);
	}

	public void atualizaBlocoInicial(Bloco b) {
		blocoInicial.setStart(b.getEnd());
		atualizaMemDisponivel();
	}

	// atualiza o valor de memoria disponivel do bloco inicial
	public void atualizaMemDisponivel() {
		qntdMemDisponivel = blocoInicial.getEnd() - blocoInicial.getStart();

	}

	// verifica se tem memoria disponivel para alocar o bloco solicitado
	public boolean verificaMemDisponivel(int solicitacao) {
		if (solicitacao < qntdMemDisponivel) {
			return true;
		} else
			return false;
	}

	// retorna o bloco inicial com a memoria disponivel
	public Bloco getBlocoMemoriaDisponivel() {
		return blocoInicial;
	}

	// procura se o bloco existe e se esta na lista de memoria ocupada
	public boolean searchBlocoMemOcupada(Bloco b) {
		for (Bloco x : memoriaOcupada) {
			if (b.getID() == b.getID()) {
				return true;
			}
		}
		return false;
	}

	// retorna o ultimo bloco da lista de memoria ocupada
	public Bloco getUltimoBloco() {
		return memoriaOcupada.getLast();
	}

	// print dos blocos
	public void printBlocos() {
		for (Bloco b : memoriaOcupada) {
			System.out.println(" " + b.toString());
		}
	}

}
