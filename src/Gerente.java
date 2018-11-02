import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Gerente {	

	private Bloco memoria;
	private ArrayList<Bloco> blocos;
	
	
	//variaveis auxiliares para manipular a Memoria
	
	private int tamTotalMemoria, tamDisponivel;
	
	public Gerente(int start, int end) {
		this.memoria = new Bloco(start, end);
		blocos = new ArrayList<>();
	}

	public Bloco getMemoriaTotal() {
		return memoria;
	}
	
	public void addBloco(Bloco b) {
		this.blocos.add(b);
	}
	
	public void printBlocos() {
		for(Bloco b: blocos) {
			System.out.println(b.toString());
		}
	}
	

}
