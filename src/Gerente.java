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
		memoriaLivre.add(b);
		atualizaMemDisponivel();

	}

	public int getMemoriaDisponivel() {
		return qntdMemDisponivel;
	}

	//
	public void getMemDisponivelPorBloco() {
		for (Bloco b : memoriaLivre) {
			System.out.println("ID: " + b.getID() + "| Tamanho: " + b.getSize());
		}
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

	// procura por um bloco com tamanho maior igual ao solicitado
	public boolean searchBlocoCompativel(int solicitacao) {
		for (Bloco b : memoriaLivre) {
			if (solicitacao <= b.getTamBloco()) {
				return true;
			}
		}
		return false;
	}

	// verifica se tem memoria disponivel para alocar o bloco solicitado
	public boolean verificaMemDisponivel(int solicitacao) {
		if (solicitacao < qntdMemDisponivel && searchBlocoCompativel(solicitacao)) {
			return true;
		} else
			System.out
					.println(qntdMemDisponivel + "livres" + ", " + solicitacao + "solicitados - fragmentação externa.");
		return false;
	}

	// retorna o bloco inicial com a memoria disponivel
	public Bloco getBlocoMemoriaDisponivel() {
		return blocoInicial;
	}

	// metodo para liberar o bloco solicitado
	public void liberaBloco(int id) {
		if (searchBlocoMemOcupada(id)) {
			System.out.println("Bloco " + id + " encontrado! Memoria liberada.");
		}

	}

	// procura se o bloco existe e se esta na lista de memoria ocupada
	public boolean searchBlocoMemOcupada(int id) {
		for (Bloco x : memoriaOcupada) {
			if (x.getID() == id) {
				addMemoriaLivre(x); // adiciona bloco na lista de memoria disponível
				removeMemoriaOcupada(x); // remove bloco da lista de memoria ocupada
				atualizaMemDisponivel(); // atualiza o tamanho total de memoria disponivel
				return true;
			}
		}
		return false;
	}

	// adicona bloco na lista de memoria disponivel

	public void addMemoriaLivre(Bloco b) {
		memoriaLivre.add(b);
	}

	// remove bloco da lista de memoria ocupada
	public void removeMemoriaOcupada(Bloco b) {
		int count = 0;
		for (Bloco x : memoriaOcupada) {
			if (b.getID() == x.getID()) {
				memoriaOcupada.remove(count);
			}
			count++;
		}
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
