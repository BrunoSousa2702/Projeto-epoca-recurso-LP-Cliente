package equipa3.grupo3.GUI.Model;

public class Utilizador {
    
    private int id;
    private String nome;
    private String password;
    private String email;
    private String username;
    private boolean isAdmin = false;
    //private enum tipoUtilizador {ADMIN, USER};

    public Utilizador(int id, String nome, String password, String email,String username, boolean isAdmin) {
        this.id = id;
        this.nome = nome;
        this.password = password;
        this.email = email;
        this.username = username;
        this.isAdmin = false;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
    public boolean  getisAdmin() {
        return isAdmin;
    }
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }




}
