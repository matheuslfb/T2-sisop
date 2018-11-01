import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.ReadOnlyFileSystemException;
import java.util.Scanner;

public class App {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		readFile();

	}

	public static void readFile() throws FileNotFoundException, IOException {

		Scanner s = new Scanner(System.in);
		System.out.println("Digite o nome do arquivo de entrada (sem extensão .txt)");
		String enter = s.nextLine();
		System.out.println();
		System.out.println("====================================================================================");
		BufferedReader in = new BufferedReader(new FileReader(enter + ".txt"));
		String line;

		while ((line = in.readLine()) != null) {
			System.out.println(line);

		}
		in.close();
	}

}
