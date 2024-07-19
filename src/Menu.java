import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private List<Usuario> usuarios = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        var opcao = -1;

        while(opcao != 0) {
            System.out.println("""
                    1 - Cadastrar o usuário
                    2 - Listar todos usuários cadastrados
                    3 - Cadastrar nova pergunta no formulário
                    4 - Deletar pergunta do formulário
                    5 - Pesquisar usuário por nome ou idade ou email
                    0 - sair\n""");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch(opcao) {
                case 1:
                    cadastrarUsuario();
                    break;
                case 2:
                    listarUsuarios();
                    break;
                case 0:
                    System.out.println("saindo...");
                    break;
            }
        }
    }

    private void listarUsuarios() {
        if(usuarios.isEmpty()) {
            System.out.println("Nenhum  encontrado");
        }else {
            for(int i = 0; i < usuarios.size(); i++) {
                System.out.println((i + 1) + " - " + usuarios.get(i).getName());
            }
        }
    }

    private void cadastrarUsuario() {
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
        System.out.println("*************");

        usuarios.add(user);

        for(int i = 0; i < usuarios.size(); i++){
            File respostFile = new File("C:/Users/Lenovo/Desktop/" + (i + 1) +"-" + usuarios.get(i).getName().toUpperCase() + ".txt");
            try {
                FileWriter writer = new FileWriter(respostFile);
                writer.write(usuarios.get(i).getName() + "\n");
                writer.write(usuarios.get(i).getEmail() + "\n");
                writer.write(usuarios.get(i).getIdade() + "\n");
                writer.write(usuarios.get(i).getAltura() + "\n");
                writer.close();
            }catch(IOException e) {
                e.printStackTrace();  // Tratar a exceção de IO
            }
        }
    }
}

