    import java.io.*;
    import java.nio.file.Files;
    import java.nio.file.Paths;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Scanner;

    public class Menu {
        private List<Usuario> usuarios = new ArrayList<>();
        private Scanner scanner = new Scanner(System.in);
        private static final String FOMULARIO_PATH = "C:/Users/Micro/Desktop/formulario.txt";
        private List<String> linhas = new ArrayList<>();
        public Menu() {

        }
        public void showMenu() {
            var opcao = -1;

            while(opcao != 0) {
                System.out.println("""
                        \n
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
                    case 3:
                        addQuestion();
                        break;
                    case 4:
                        removeQuestion();
                        break;
                    case 5:
                        searchUser();
                        break;
                    case 0:
                        System.out.println("saindo...");
                        break;
                }
            }
        }

        private void loadQuestions() {
            linhas.clear(); // Limpar a lista existente para garantir que não haja duplicatas
            File file = new File(FOMULARIO_PATH);
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line = reader.readLine();
                while (line != null) {
                    linhas.add(line);
                    line = reader.readLine();
                }
                reader.close();
            }catch (IOException e) {
                throw new RuntimeException("Erro ao ler o arquivo: " + e.getMessage(), e);
            }

        }

        private void cadastrarUsuario() {
            //carregando as perguntas do fomulário
            loadQuestions();

            //Percorrendo o array de perguntas e printando cada uma na tela
            for (String linha : linhas) {
                System.out.println(linha);
            }

            String name = scanner.nextLine();
            String email = scanner.nextLine();
            int age = scanner.nextInt();
            scanner.nextLine();
            String higth = scanner.nextLine();

            Usuario user = new Usuario(name, email, age, higth);

            System.out.println("\n**** DADOS ****");
            System.out.println(user);
            System.out.println("*************");

            usuarios.add(user);

            File respostFile = new File("C:/Users/Micro/Desktop/" + usuarios.size() + " - " + user.getName().toUpperCase() + ".txt");
            try {
                FileWriter writer = new FileWriter(respostFile);
                writer.write(user.getName() + "\n");
                writer.write(user.getEmail() + "\n");
                writer.write(user.getIdade() + "\n");
                writer.write(user.getAltura() + "\n");
                writer.close();
            }catch(IOException e) {
                e.printStackTrace();  // Tratar a exceção de IO
            }
        }

        private void listarUsuarios() {
            if(usuarios.isEmpty()) {
                System.out.println("Nenhum usuário encontrado");
            }else {
                for(int i = 0; i < usuarios.size(); i++) {
                    System.out.println((i + 1) + " - " + usuarios.get(i).getName());
                }
            }
        }

        private void addQuestion() {
            loadQuestions();
            int nextLineNumber = linhas.size() + 1; // Indica qual o número da próxima pergunta

            System.out.println("Digite uma nova pergunta");
            var newQuestion = scanner.nextLine();

            try (FileWriter writer = new FileWriter(FOMULARIO_PATH, true)) { // O parâmetro true indica modo append
                writer.write(nextLineNumber + "- " + newQuestion + System.lineSeparator()); // Escreve a nova pergunta no formulário
                System.out.println("Pergunta adicionada com sucesso.");
            } catch (IOException e) {
                throw new RuntimeException("Erro ao adicionar nova pergunta: " + e.getMessage(), e);
            }

        }

        private void removeQuestion() {
            loadQuestions();
            System.out.println("Digite o numero da pergunta que queira remover");
            var questionRemoved = scanner.nextInt();
            scanner.nextLine();

            try {
                linhas.remove(questionRemoved -1 );
                linhas.replaceAll(linha -> {
                    int index = linhas.indexOf(linha); // pega o index de cada pergunta
                    int dashIndex = linha.indexOf("-"); // Pega o index do "-" que é 1
                    return (index + 1) + linha.substring(dashIndex); // Pega a pergunta a partir do index do "-"
                });
                Files.write(Paths.get(FOMULARIO_PATH), linhas); // Atualiza o formulário com o array atualizado
                System.out.println("Linha removida com sucesso!");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void searchUser() {
            System.out.println("Digite um treco do nome do usuario");
            var trecho = scanner.nextLine();

            usuarios.stream()
                    .filter(u -> u.getName().toLowerCase().contains(trecho.toLowerCase()))
                    .forEach(System.out::println);
        }



    }

