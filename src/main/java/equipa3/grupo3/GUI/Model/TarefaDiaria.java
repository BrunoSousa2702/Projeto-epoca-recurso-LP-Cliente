package equipa3.grupo3.GUI.Model;

import java.time.LocalDate;
import java.time.LocalTime;

public class TarefaDiaria extends Tarefa {
    private LocalTime horario; // Horário específico para realizar a tarefa (HH:mm)

    // Getters e Setters
    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public void setHorario(String horario) {
        this.horario = LocalTime.parse(horario); // Converte String para LocalTime
    }

    // Construtor
    public TarefaDiaria(int id, String titulo, String descricao, LocalDate data, Prioridade prioridade, Estado estado, Utilizador utilizador, LocalTime horario) {
        super(id, titulo, descricao, data, prioridade, estado, utilizador);
        this.horario = horario;
    }

    // Construtor vazio
    public TarefaDiaria() {
        super(); // Chama o construtor da classe pai
    }
}
