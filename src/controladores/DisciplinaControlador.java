package controladores;

import modos.Disciplina;
import modos.Turma;
import utilitarios.GerenciadorArquivos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DisciplinaControlador {
    private List<Disciplina> disciplinas;
    private List<Turma> turmas;
    private GerenciadorArquivos gerenciadorArquivos;

    public DisciplinaControlador() {
        this.disciplinas = new ArrayList<>();
        this.turmas = new ArrayList<>();
        this.gerenciadorArquivos = new GerenciadorArquivos();
        carregarDados();
    }

    public void cadastrarDisciplina(Scanner scanner) {
        System.out.println("\n=== CADASTRAR DISCIPLINA ===");
        Disciplina disciplina = new Disciplina(
                solicitarDado(scanner, "Código"),
                solicitarDado(scanner, "Nome"),
                Integer.parseInt(solicitarDado(scanner, "Carga horária")),
                List.of(solicitarDado(scanner, "Pré-requisitos (separados por vírgula)").split(","))
        );

        disciplinas.add(disciplina);
        gerenciadorArquivos.salvarDisciplinas(disciplinas);
    }

    public void criarTurma(Scanner scanner) {
        System.out.println("\n=== CRIAR TURMA ===");
        System.out.print("Código da disciplina: ");
        Disciplina disciplina = buscarDisciplina(scanner.nextLine());

        if (disciplina == null) {
            System.out.println("Disciplina não encontrada!");
            return;
        }

        Turma turma = new Turma(
                solicitarDado(scanner, "Código da turma"),
                disciplina,
                solicitarDado(scanner, "Professor"),
                solicitarDado(scanner, "Semestre"),
                solicitarDado(scanner, "Forma de avaliação"),
                solicitarDado(scanner, "Presencial? (S/N)").equalsIgnoreCase("S"),
                solicitarDado(scanner, "Sala (deixe em branco se remota)"),
                solicitarDado(scanner, "Horário"),
                Integer.parseInt(solicitarDado(scanner, "Capacidade"))
        );

        if (!verificarConflitoHorario(turma)) {
            turmas.add(turma);
            gerenciadorArquivos.salvarTurmas(turmas);
        } else {
            System.out.println("Conflito de horário detectado!");
        }
    }

    public void listarTurmasDisponiveis() {
        turmas.stream()
                .filter(Turma::temVagas)
                .forEach(System.out::println);
    }

    public void matricularAluno(String matricula, String codigoTurma) {
        Turma turma = buscarTurma(codigoTurma);
        if (turma != null && turma.temVagas()) {
            turma.matricularAluno(matricula);
            gerenciadorArquivos.salvarTurmas(turmas);
        }
    }

    public void trancarMatricula(String matricula, String codigoDisciplina) {
        turmas.stream()
                .filter(t -> t.getDisciplina().getCodigo().equals(codigoDisciplina))
                .findFirst()
                .ifPresent(t -> {
                    t.removerAluno(matricula);
                    gerenciadorArquivos.salvarTurmas(turmas);
                });
    }

    private String solicitarDado(Scanner scanner, String mensagem) {
        System.out.print(mensagem + ": ");
        return scanner.nextLine();
    }

    private boolean verificarConflitoHorario(Turma novaTurma) {
        return turmas.stream().anyMatch(t ->
                t.getProfessor().equals(novaTurma.getProfessor()) &&
                        t.getHorario().equals(novaTurma.getHorario()));
    }

    private Disciplina buscarDisciplina(String codigo) {
        return disciplinas.stream()
                .filter(d -> d.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }

    Turma buscarTurma(String codigo) {
        return turmas.stream()
                .filter(t -> t.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }

    private void carregarDados() {
        this.disciplinas = gerenciadorArquivos.carregarDisciplinas();
        this.turmas = gerenciadorArquivos.carregarTurmas();
    }

    public void removerAlunoDeTodasTurmas(String matricula) {
        turmas.forEach(t -> t.removerAluno(matricula));
        gerenciadorArquivos.salvarTurmas(turmas);
    }
    public void listarTurmas() {
        if (turmas.isEmpty()) {
            System.out.println("Nenhuma turma cadastrada.");
            return;
        }
        System.out.println("\n=== LISTA DE TURMAS ===");
        turmas.forEach(t -> {
            System.out.println(t);
            System.out.println("----------------------");
        });
    }

    public void listarAlunosTurma(Scanner scanner) {
        System.out.print("\nCódigo da turma: ");
        Turma turma = buscarTurma(scanner.nextLine());

        if (turma == null) {
            System.out.println("Turma não encontrada!");
            return;
        }

        System.out.println("\nAlunos matriculados em " + turma.getDisciplina().getNome() + ":");
        if (turma.getAlunosMatriculados().isEmpty()) {
            System.out.println("Nenhum aluno matriculado.");
        } else {
            turma.getAlunosMatriculados().forEach(System.out::println);
        }
    }

    public void atualizarDados() {
        gerenciadorArquivos.salvarDisciplinas(disciplinas);
        gerenciadorArquivos.salvarTurmas(turmas);
        System.out.println("Dados atualizados com sucesso!");
    }

}