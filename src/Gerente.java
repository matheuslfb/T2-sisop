import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Gerente {

	private int MODO; // 1 = modo fixo; 2 = modo aleatório

	private class Bloco {

		private int start;
		private int end;
		private int size;

		public Bloco(int start, int end, int size) {
			this.start = start;
			this.end = end;
			this.size = size;
		}

		public int getStart() {
			return this.start;
		}

		public int getEnd() {
			return this.end;
		}

		public int getSize() {
			return this.size;
		}

	}

	private Bloco bloco;

	private int start, end, size;

	public Gerente(int modo) {
		bloco(start, end, size);

	}

	public void readFile() throws FileNotFoundException, IOException {

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

		while ((line = in.readLine()) != null) {
			String info[] = line.split(" ");

			if (info[0] == "S") {
				solicita();
			}

		}
		in.close();
	}

	public void solicita(int val) {

	}

}
