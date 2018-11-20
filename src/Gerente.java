import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Gerente {

	private Bloco blocoInicial;
	private LinkedList<Bloco> memoriaLivre;
	private LinkedList<Bloco> memoriaOcupada;

	// variaveis auxiliares para manipular a Memoria

	private int totalMemoriaDisponivel;

	public Gerente(Bloco b) {
		memoriaOcupada = new LinkedList();
		memoriaLivre = new LinkedList();

		blocoInicial = b;
		memoriaLivre.add(b);
		atualizaMemDisponivel();

	}

	public int getMemoriaDisponivel() {
		return totalMemoriaDisponivel;
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
		totalMemoriaDisponivel = blocoInicial.getEnd() - blocoInicial.getStart();

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
		int count = 0;

		// arrumar o retorno de fragmentação externa

		// if (solicitacao < totalMemoriaDisponivel) {
		// if (searchBlocoCompativel(solicitacao)) {
		// return true;
		// } else {
		// for (Bloco b : memoriaLivre) {
		// if (b.getTamBloco() < solicitacao) {
		// count += b.getTamBloco();
		// System.out.println("ID - Bloco: " + b.getID() + "| tamanho do bloco: " +
		// b.getTamBloco());
		// }
		// System.out.println("Total de blocos de memoria dos blocos fragmentados" +
		// count);
		//
		// }
		// }
		// System.out.println(
		// totalMemoriaDisponivel + "livres" + ", " + solicitacao + "solicitados -
		// fragmentação externa.");
		//
		// } else
		// System.out.println("Não há memoria disponivel! | Total de memoria :" +
		// totalMemoriaDisponivel);
		// return false;

		if (solicitacao < totalMemoriaDisponivel) {
			if (searchBlocoCompativel(solicitacao)) {
				return true;
			}
			for (Bloco b : memoriaLivre) {
				if (b.getTamBloco() < solicitacao) {
					count += b.getTamBloco();
					System.out.println("ID - Bloco: " + b.getID() + "| tamanho do bloco: " + b.getTamBloco());
				}
				System.out.println("Total de blocos de memoria dos blocos fragmentados" + count);
			}
			System.out.println(
					totalMemoriaDisponivel + "livres" + ", " + solicitacao + "solicitados - fragmentação externa.");
			return false;

		} else
			System.out.println("Não há memoria disponivel! | Total de memoria :" + totalMemoriaDisponivel);
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
		ordenaListaMemoriaLivre();

	}

	public void ordenaListaMemoriaLivre() {
		Collections.sort(memoriaLivre, new Comparator<Bloco>() {
			@Override
			public int compare(Bloco b1, Bloco b2) {
				if (b1.getEnd() <= b2.getStart()) {
					return -1;
				} else {
					return 1;
				}
			}
		});
		System.out.println();
		System.out.println(memoriaLivre);
	}

	// remove bloco da lista de memoria ocupada
	public void removeMemoriaOcupada(Bloco b) {
		int count = 0;

		try {// verifica se o bloco existe
			for (Bloco x : memoriaOcupada) {
				if (b.getID() == x.getID()) {
					memoriaOcupada.remove(count);
				}
				count++;
			}
		} catch (Exception e) { // retorna mensagem de que o bloco não foi encontrado
			// TODO: handle exception
			System.out.println("Não é possivel liberar o bloco. Bloco não encontrado na lista!");
		}

	}

	// retorna o ultimo bloco da lista de memoria ocupada
	public Bloco getUltimoBloco() {
		return memoriaOcupada.getLast();
	}

	// print da lista de memoria ocupada
	public void printListaMemoriaOcupada() {
		for (Bloco b : memoriaOcupada) {
			System.out.println(" " + b.toString());
		}
	}

}
