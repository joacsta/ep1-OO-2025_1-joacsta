package utilitarios;

import modos.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GerenciadorArquivos {
    // Arquivos
    private static final String ARQUIVO_ALUNOS = "alunos.txt";
    private static final String ARQUIVO_DISCIPLINAS = "disciplinas.txt";
    private static final String ARQUIVO_TURMAS = "turmas.txt";

    /* ========== ALUNOS ========== */
    public void salvarAluno(Aluno aluno) {
        List<Aluno> alunosExistentes = carregarAlunos();
        alunosExistentes.add(aluno);
        salvarListaAlunos(alunosExistentes);
    }

    public void atualizarAlunos(List<Aluno> alunos) {
        salvarListaAlunos(alunos);
    }

    @SuppressWarnings("unchecked")
    public List<Aluno> carregarAlunos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_ALUNOS))) {
            return (List<Aluno>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private void salvarListaAlunos(List<Aluno> alunos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_ALUNOS))) {
            oos.writeObject(alunos);
        } catch (IOException e) {
            System.err.println("Erro ao salvar alunos: " + e.getMessage());
        }
    }

    /* ========== DISCIPLINAS ========== */
    public void salvarDisciplinas(List<Disciplina> disciplinas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_DISCIPLINAS))) {
            oos.writeObject(disciplinas);
        } catch (IOException e) {
            System.err.println("Erro ao salvar disciplinas: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public List<Disciplina> carregarDisciplinas() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_DISCIPLINAS))) {
            return (List<Disciplina>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    /* ========== TURMAS ========== */
    public void salvarTurmas(List<Turma> turmas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_TURMAS))) {
            oos.writeObject(turmas);
        } catch (IOException e) {
            System.err.println("Erro ao salvar turmas: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public List<Turma> carregarTurmas() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_TURMAS))) {
            return (List<Turma>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    /* ========== MÉTODOS AUXILIARES ========== */
    public static void criarArquivosSeNaoExistirem() {
        try {
            new File(ARQUIVO_ALUNOS).createNewFile();
            new File(ARQUIVO_DISCIPLINAS).createNewFile();
            new File(ARQUIVO_TURMAS).createNewFile();
        } catch (IOException e) {
            System.err.println("Erro ao criar arquivos: " + e.getMessage());
        }
    }

    public void salvarResultados(Map<String, ResultadoAluno> resultados) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("resultados.txt"))) {
            oos.writeObject(resultados);
        } catch (IOException e) {
            System.err.println("Erro ao salvar resultados: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, ResultadoAluno> carregarResultados() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("resultados.txt"))) {
            return (Map<String, ResultadoAluno>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new HashMap<>();
        }
    }
}