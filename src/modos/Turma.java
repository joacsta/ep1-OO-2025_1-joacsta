package modos;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Turma implements Serializable{
    private static final long serialVersionUID = 1L;

    private String codigo;
    private Disciplina disciplina;
    private String professor;
    private String semestre;
    private FormaAvaliacao formaAvaliacao;
    private boolean presencial;
    private String sala;
    private String horario;
    private int capacidade;
    private List<String> alunosMatriculados;

    public Turma(String codigo, Disciplina disciplina, String professor, String semestre,
                 String formaAvaliacao, boolean presencial, String sala, String horario, int capacidade) {
        this.codigo = codigo;
        this.disciplina = disciplina;
        this.professor = professor;
        this.semestre = semestre;
        this.formaAvaliacao = FormaAvaliacao.valueOf(formaAvaliacao.toUpperCase());
        this.presencial = presencial;
        this.sala = sala;
        this.horario = horario;
        this.capacidade = capacidade;
        this.alunosMatriculados = new ArrayList<>();
    }

    public void matricularAluno(String matricula) {
        if (alunosMatriculados.size() < capacidade) {
            alunosMatriculados.add(matricula);
        }
    }

    public void removerAluno(String matricula) {
        alunosMatriculados.remove(matricula);
    }

    public boolean temVagas() {
        return alunosMatriculados.size() < capacidade;
    }

    public String getCodigo() {
        return codigo;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public String getProfessor() {
        return professor;
    }

    public String getSemestre() {
        return semestre;
    }

    public FormaAvaliacao getFormaAvaliacao() {
        return formaAvaliacao;
    }

    public boolean isPresencial() {
        return presencial;
    }

    public String getSala() {
        return presencial ? sala : "Turma Remota";
    }

    public String getHorario() {
        return horario;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public List<String> getAlunosMatriculados() {
        return new ArrayList<>(alunosMatriculados); // Retorna cópia para evitar alterações externas
    }

    @Override
    public String toString() {
        return String.format(
                "%s - %s (%s)\nProfessor: %s | Horário: %s | Vagas: %d/%d | %s",
                disciplina.getNome(),
                codigo,
                semestre,
                professor,
                horario,
                alunosMatriculados.size(),
                capacidade,
                presencial ? "Sala: " + sala : "Modalidade Remota"
        );
    }
}