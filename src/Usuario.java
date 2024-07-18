public class Usuario {

    private String name;
    private String idade;
    private String email;
    private String altura;


    public Usuario(String name, String idade, String email, String altura) {
        this.name = name;
        this.idade = idade;
        this.email = email;
        this.altura = altura;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    @Override
    public String toString() {
        return name + "\n" + idade + "\n" + email + "\n" + altura + "\n";
    }
}
