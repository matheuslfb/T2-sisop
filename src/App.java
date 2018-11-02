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
		String enter = s.nextLine();
		System.out.println();
		System.out.println("====================================================================================");
		BufferedReader in = new BufferedReader(new FileReader(enter + ".txt"));
		String line;

		MODO = Integer.parseInt(in.readLine());
		start = Integer.parseInt(in.readLine());
		end = Integer.parseInt(in.readLine());

		int count = 0; // quantidade de solicitacoes de memoria
		Gerente g = new Gerente(start, end);
		int solicitacao = 0;// tamanho da solicitacao de alocacao de memoria

		try {
			while ((line = in.readLine()) != null) {
				String info[] = line.split(" ");
				for (String a : info) {
					System.out.print(a);
				}

				// TODO
				// pegar o valor da solicitação de info e passar para o metodo solicita()
				// System.out.println(info[0]);
				if (info[0].equals("S")) { // verifica se é solicitacao
					count++; // executa a solicitacao passando o valor requerido

					solicitacao = Integer.parseInt(info[1]);

					Bloco b = new Bloco(g.getMemoriaTotal().getStart(), solicitacao + g.getMemoriaTotal().getStart());
					b.setID(count);

					g.addBloco(b);

				}

				System.out.println("Tamanho da solicitação:" + solicitacao);

				g.printBlocos();
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.err.print(e);
		}
		System.out.println("Quantidade de solicitações: " + count);

	}

}
