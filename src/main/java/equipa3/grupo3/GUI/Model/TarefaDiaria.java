package equipa3.grupo3.GUI.Model;

import java.time.LocalDate;



public class TarefaDiaria extends Tarefa {
    private String horario; // Horário específico para realizar a tarefa (HH:mm)

    // Getters e Setters
    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    // Construtor
    public TarefaDiaria(int id, String titulo, String descricao, LocalDate data, Prioridade prioridade, Estado estado, Utilizador utilizador, String horario) {
        super(id, titulo, descricao, data, prioridade, estado, utilizador);
        this.horario = horario;
    }

    public TarefaDiaria() {
    }
}
