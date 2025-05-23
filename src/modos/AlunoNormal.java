package modos;

import java.util.HashMap;
import java.io.Serializable;
import java.util.Map;

public class AlunoNormal extends Aluno implements Serializable {
    private static final long serialVersionUID = 1L;
    private Map<Disciplina, Double> notas;

    public AlunoNormal(String nome, String matricula, String curso) {
        super(nome, matricula, curso);
        this.notas = new HashMap<>();
    }

    public void adicionarNota(Disciplina disciplina, double nota) {
        notas.put(disciplina, nota);
    }

    public double getNota(Disciplina disciplina) {
        return notas.getOrDefault(disciplina, 0.0);
    }

    @Override
    public String toString() {
        return super.toString() + "\nTipo: Aluno Regular";
    }
}