import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        File file = new File("C:/Users/Lenovo/Desktop/formulario.txt");
        List<String> linhas = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                linhas.add(line);
                line = reader.readLine();
            }
        }catch (IOException e) {
            throw new RuntimeException("Erro ao ler o arquivo: " + e.getMessage(), e);
        }

        for (String linha : linhas) {
            System.out.println(linha);
        }

        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        String email = scanner.nextLine();
        String age = scanner.nextLine();
        String higth = scanner.nextLine();

        Usuario user = new Usuario(name, email, age, higth);

        System.out.println("\n**** DADOS ****");
        System.out.println(user);
        System.out.println("**** DADOS ****");

    }
}