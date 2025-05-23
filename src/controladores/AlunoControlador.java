package controladores;

import modos.Aluno;
import modos.AlunoEspecial;
import modos.AlunoNormal;
import utilitarios.GerenciadorArquivos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AlunoControlador {
    private List<Aluno> alunos;
    private DisciplinaControlador disciplinaControlador;
    private GerenciadorArquivos gerenciadorArquivos;

    public AlunoControlador() {
        this.alunos = new ArrayList<>();
        this.disciplinaControlador = new DisciplinaControlador();
        this.gerenciadorArquivos = new GerenciadorArquivos();
        carregarAlunos();
    }

    public void cadastrarAluno(Scanner scanner) {
        System.out.println("\n=== CADASTRAR ALUNO ===");
        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();

        if (alunoExiste(matricula)) {
            System.out.println("Erro: Matrícula já cadastrada!");
            return;
        }

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Curso: ");
        String curso = scanner.nextLine();
        System.out.print("É aluno especial? (S/N): ");
        String especial = scanner.nextLine();

        Aluno aluno = especial.equalsIgnoreCase("S")
                ? new AlunoEspecial(nome, matricula, curso)
                : new AlunoNormal(nome, matricula, curso);

        alunos.add(aluno);
        gerenciadorArquivos.salvarAluno(aluno);
    }

    public void editarAluno(Scanner scanner) {
        System.out.print("\nMatrícula do aluno: ");
        Aluno aluno = buscarAluno(scanner.nextLine());

        if (aluno == null) {
            System.out.println("Aluno não encontrado!");
            return;
        }

        System.out.print("Novo nome: ");
        aluno.setNome(scanner.nextLine());
        System.out.print("Novo curso: ");
        aluno.setCurso(scanner.nextLine());

        gerenciadorArquivos.atualizarAlunos(alunos);
    }

    public void listarAlunos() {
        alunos.forEach(System.out::println);
    }

    public void matricularDisciplina(Scanner scanner) {
        System.out.print("\nMatrícula do aluno: ");
        Aluno aluno = buscarAluno(scanner.nextLine());

        if (aluno == null) {
            System.out.println("Aluno não encontrado!");
            return;
        }

        if (aluno instanceof AlunoEspecial && aluno.getDisciplinasMatriculadas().size() >= 2) {
            System.out.println("Aluno especial atingiu o limite de disciplinas!");
            return;
        }

        disciplinaControlador.listarTurmasDisponiveis();
        System.out.print("Código da turma: ");
        String codigoTurma = scanner.nextLine();

        disciplinaControlador.matricularAluno(aluno.getMatricula(), codigoTurma);
        gerenciadorArquivos.atualizarAlunos(alunos);
    }

    public void trancarDisciplina(Scanner scanner) {
        System.out.print("\nMatrícula do aluno: ");
        Aluno aluno = buscarAluno(scanner.nextLine());

        if (aluno != null && !aluno.getDisciplinasMatriculadas().isEmpty()) {
            aluno.getDisciplinasMatriculadas().forEach(d ->
                    System.out.println(d.getCodigo() + " - " + d.getNome()));

            System.out.print("Código da disciplina: ");
            disciplinaControlador.trancarMatricula(aluno.getMatricula(), scanner.nextLine());
            gerenciadorArquivos.atualizarAlunos(alunos);
        } else {
            System.out.println("Nenhuma disciplina matriculada!");
        }
    }

    private boolean alunoExiste(String matricula) {
        return alunos.stream().anyMatch(a -> a.getMatricula().equals(matricula));
    }

    private Aluno buscarAluno(String matricula) {
        return alunos.stream()
                .filter(a -> a.getMatricula().equals(matricula))
                .findFirst()
                .orElse(null);
    }

    private void carregarAlunos() {
        this.alunos = gerenciadorArquivos.carregarAlunos();
    }

    public void trancarSemestre(Scanner scanner) {
        System.out.print("\nMatrícula do aluno: ");
        Aluno aluno = buscarAluno(scanner.nextLine());

        if (aluno == null) {
            System.out.println("Aluno não encontrado!");
            return;
        }


        System.out.println("Deseja trancar o semestre para " + aluno.getNome() + "?");
        System.out.print("Isso removerá TODAS as matrículas atuais. (S/N): ");
        String confirmacao = scanner.nextLine();

        if (confirmacao.equalsIgnoreCase("S")) {
            aluno.trancarSemestre();


            disciplinaControlador.removerAlunoDeTodasTurmas(aluno.getMatricula());

            gerenciadorArquivos.atualizarAlunos(alunos);
            System.out.println("Semestre trancado com sucesso!");
        } else {
            System.out.println("Operação cancelada.");
        }
    }

}