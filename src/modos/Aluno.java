package modos;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public abstract class Aluno implements Serializable{
    private static final long serialVersionUID = 1L;

    private String nome;
    private String matricula;
    private String curso;
    private boolean semestreTrancado;
    private List<Disciplina> disciplinasMatriculadas;

    public Aluno(String nome, String matricula, String curso) {
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
        this.semestreTrancado = false;
        this.disciplinasMatriculadas = new ArrayList<>();
    }

    public void matricularDisciplina(Disciplina disciplina) {
        if (!semestreTrancado && !disciplinasMatriculadas.contains(disciplina)) {
            disciplinasMatriculadas.add(disciplina);
        }
    }

    public void trancarDisciplina(Disciplina disciplina) {
        disciplinasMatriculadas.remove(disciplina);
    }

    public void trancarSemestre() {
        semestreTrancado = true;
        disciplinasMatriculadas.clear();
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public boolean isSemestreTrancado() {
        return semestreTrancado;
    }

    public List<Disciplina> getDisciplinasMatriculadas() {
        return disciplinasMatriculadas;
    }

    @Override
    public String toString() {
        return "Aluno: " + nome + "\nMatrícula: " + matricula + "\nCurso: " + curso +
                "\nSemestre trancado: " + (semestreTrancado ? "Sim" : "Não") +
                "\nDisciplinas matriculadas: " + disciplinasMatriculadas.size();
    }
}