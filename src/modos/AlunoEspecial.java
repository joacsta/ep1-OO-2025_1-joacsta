package modos;

import java.io.Serializable;

public class AlunoEspecial extends Aluno implements Serializable {
    private static final long serialVersionUID = 1L;
    public AlunoEspecial(String nome, String matricula, String curso) {
        super(nome, matricula, curso);
    }

    @Override
    public void matricularDisciplina(Disciplina disciplina) {
        if (getDisciplinasMatriculadas().size() < 2) {
            super.matricularDisciplina(disciplina);
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\nTipo: Aluno Especial";
    }
}