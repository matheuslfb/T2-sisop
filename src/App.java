import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.ReadOnlyFileSystemException;
import java.util.Scanner;

public class App {

	private static int start, end, size;
	private static int MODO; // 1 = modo fixo; 2 = modo aleatório

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		readFile();
		// Gerente g = new Gerente();

	}

	public static void readFile() throws FileNotFoundException, IOException {

		Scanner s = new Scanner(System.in);
		System.out.println("Digite o nome do arquivo de entrada (sem extensão .txt)");
		String enter = s.nextLine(); // le o nome do arquivo de entrada
		System.out.println();
		System.out.println("====================================================================================");
		BufferedReader in = new BufferedReader(new FileReader(enter + ".txt"));
		String line;

		MODO = Integer.parseInt(in.readLine());
		start = Integer.parseInt(in.readLine());
		end = Integer.parseInt(in.readLine());

		Bloco blocoInicial = new Bloco(start, end); // cria o bloco inicial com a memoria disponivel

		Gerente g = new Gerente(blocoInicial); // cria um objeto do tipo do gerente

		int count = 0; // quantidade de solicitacoes de memoria

		int solicitacao = 0;// tamanho da solicitacao de alocacao de memoria para cada bloco

		try {
			while ((line = in.readLine()) != null) {// faz a leitura do arquivo
				String info[] = line.split(" ");

				// for (String a : info) { // print teste
				// System.out.println(a);
				// }

				if (info[0].equals("S")) { // verifica se o comando lido é de solicitacao

					solicitacao = Integer.parseInt(info[1]);// tamanho solicitado do bloco

					if (g.verificaMemDisponivel(solicitacao)) {
						g.addBlocoMemoriaOcupada(solicitacao, count);
						count++;
						System.out.println("Tamanho total de memoria disponivel: " + g.getMemoriaDisponivel());
					} else if (!g.verificaMemDisponivel(solicitacao)) {
						System.out.println("Fragmentação externa!");
					}

				}
				if (info[0].equals("L")) {
					g.liberaBloco(Integer.parseInt(info[1]));
					System.out.print( g.getMemDisponivelPorBloco());
				}

				// System.out.println("Tamanho da solicitação:" + solicitacao);

			}
			in.close();
		} catch (FileNotFoundException e) {
			System.err.print(e);
		}
		g.printBlocos();
		System.out.println("Quantidade de solicitações: " + count);

	}

}
