import javax.naming.InvalidNameException;

public class Usuario {

    private String name;
    private int age;
    private String email;
    private String higth;


    public Usuario(String name, String email, int age, String higth) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.higth = higth;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdade() {
        return age;
    }

    public void setIdade(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAltura() {
        return higth;
    }

    public void setAltura(String altura) {
        this.higth = altura;
    }

    @Override
    public String toString() {
        return name + "\n" + age + "\n" + email + "\n" + higth + "\n";
    }
}
