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
	private ArrayList<Integer> aguardando;

	// variaveis auxiliares para manipular a Memoria

	private int totalMemoriaDisponivel;

	public Gerente(Bloco b) {
		memoriaOcupada = new LinkedList();
		memoriaLivre = new LinkedList();
		aguardando = new ArrayList();

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

		Bloco b = new Bloco(getCompativel(solicitacao).getStart(), getCompativel(solicitacao).getStart() + solicitacao);
		b.setID(ID);
		System.out.println(b.toString());
		memoriaOcupada.add(b);
		atualizaBlocoCompativel(b);
		atualizaMemDisponivel();
	}

	// atualiza o tamanho do bloco que é compativel, diminuindo com o valor da
	// solicitacao
	public void atualizaBlocoCompativel(Bloco b) {
		getCompativel(b.getTamBloco()).setStart(b.getEnd());
		// blocoInicial.setStart(b.getEnd());
		atualizaMemDisponivel();
	}

	// atualiza o valor de memoria disponivel do bloco inicial
	public void atualizaMemDisponivel() {
		int count = 0;
		for (Bloco b : memoriaLivre) {
			count += b.getTamBloco();
		}
		totalMemoriaDisponivel = count;

	}

	// procura um bloco com tamanho compativel com a solicitacao
	public Bloco getCompativel(int solicitacao) {
		Bloco aux = null;
		for (Bloco b : memoriaLivre) {
			if (solicitacao <= b.getTamBloco()) {
				aux = b;
			}
		}
		return aux;
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

	public void addAguardando(int solicitacao) {
		aguardando.add(solicitacao);
	}

	// verifica se tem memoria disponivel para alocar o bloco solicitado
	public boolean verificaMemDisponivel(int solicitacao, int id) {
		atualizaMemDisponivel();
		int count = 0; // total de memoria livre fragmentada

		if (solicitacao < totalMemoriaDisponivel) {
			if (searchBlocoCompativel(solicitacao)) {
				return true;
			} else {
				addAguardando(solicitacao);

			}
			for (Bloco b : memoriaLivre) {
				count += b.getTamBloco();
			}

			if (count >= solicitacao) {
				System.out.println("Memoria livre: " + totalMemoriaDisponivel + " | " + "solicitados : " + solicitacao
						+ " - fragmentação externa.");
				return false;
			}

		}

		System.out.println("Não há memoria disponivel! | Total de memoria fragmentada disponivel :"
				+ totalMemoriaDisponivel + " Solicitado: " + solicitacao);

		return false;
	}

	public boolean atendeListaAguardando(int id) {

		for (int x : aguardando) {
			if (verificaMemDisponivel(x, id)) {
				addBlocoMemoriaOcupada(x, id);
				aguardando.remove(aguardando.contains(x));
				return true;
			}

		}
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
				// removeMemoriaOcupada(x); // remove bloco da lista de memoria ocupada
				memoriaOcupada.remove(x);
				atualizaMemDisponivel(); // atualiza o tamanho total de memoria disponivel

				return true;
			}
		}
		System.out.println("Não é possivel liberar o bloco com id " + id + ". Bloco não encontrado na lista!");
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

	public Bloco serachBlocoListaMemoriaOcupada(Bloco b) {
		int count = 1;
		for (Bloco x : memoriaOcupada) {
			if (b.getID() == x.getID()) {
				memoriaOcupada.remove(count);
			}
			count++;
		}
		return b;
	}

	public void printListaLivre() {
		System.out.println("Memoria Livre");
		for (Bloco b : memoriaLivre) {
			System.out.println(" " + b.toString() + " livre" + "(tamanho " + b.getTamBloco() + " )");
		}
	}

	// print da lista de memoria ocupada
	public void printListaMemoriaOcupada() {
		System.out.println("Memoria ocupada");
		for (Bloco b : memoriaOcupada) {
			System.out.println(" " + b.toString() + " | Bloco - " + b.getID() + " (tamanho " + b.getTamBloco() + " )");
		}
	}

	// print da lista de memoria ocupada
	public void printListaAguardando() {
		if (!aguardando.isEmpty()) {
			System.out.println("Memoria ocupada");
			for (int i =0; i< aguardando.size(); i++) {
				System.out.println("Solicitacão aguardando | Tamanho: " + aguardando.get(i));
			}
		}

	}

}
