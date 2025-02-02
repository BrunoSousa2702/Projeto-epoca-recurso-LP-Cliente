package equipa3.grupo3.GUI.Model;




public class TarefaFaculdade extends Tarefa {
    //@Id
    private int idTarefaFaculdade;  

    private String disciplina;

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public int getIdTarefaFaculdade() {
        return idTarefaFaculdade;
    }

    public void setIdTarefaFaculdade(int idTarefaFaculdade) {
        this.idTarefaFaculdade = idTarefaFaculdade;
    }
}
