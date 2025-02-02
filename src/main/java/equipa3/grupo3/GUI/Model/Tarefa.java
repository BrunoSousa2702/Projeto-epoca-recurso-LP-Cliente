package equipa3.grupo3.GUI.Model;

import java.time.LocalDate;

public class Tarefa {

    private int id;

    private String titulo;
    private String descricao;
    private LocalDate data;

    
    private Prioridade prioridade;
    public enum Prioridade {
        ALTA, MEDIA, BAIXA;
    }
    

    
    private Estado estado;
    public enum Estado {
        ATIVO,
        NAO_ATIVO
    }

   
    private Utilizador utilizador;

    // Getters e Setters
    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getdate() {
        return data;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public Estado getEstado() {
        return estado;
    }

    public Utilizador getUtilizador() {
        return utilizador;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setdate(LocalDate data) {
        this.data = data;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setUtilizador(Utilizador utilizador) {
        this.utilizador = utilizador;
    }

    // Construtor

    public Tarefa(int id, String titulo, String descricao, LocalDate data, Prioridade prioridade, Estado estado, Utilizador utilizador) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.prioridade = prioridade;
        this.estado = estado;
        this.utilizador = utilizador;
    }
    

    public Tarefa() {
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", data=" + data +
                ", prioridade=" + prioridade +
                ", estado='" + estado + '\'' +
                ", utilizador=" + utilizador +
                '}';
    }

}
