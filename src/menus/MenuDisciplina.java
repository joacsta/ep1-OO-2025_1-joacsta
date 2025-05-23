package menus;

import controladores.DisciplinaControlador;

import java.util.Scanner;

public class MenuDisciplina {
    private DisciplinaControlador disciplinaControlador;
    private Scanner scanner;

    public MenuDisciplina(DisciplinaControlador disciplinaControlador) {
        this.disciplinaControlador = disciplinaControlador;
        this.scanner = new Scanner(System.in);
    }

    public void exibir() {
        int opcao;

        do {
            System.out.println("\n=== MODO DISCIPLINA/TURMA ===");
            System.out.println("1. Cadastrar Disciplina");
            System.out.println("2. Criar Turma");
            System.out.println("3. Listar Turmas");
            System.out.println("4. Listar Alunos Matriculados");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    disciplinaControlador.cadastrarDisciplina(scanner);
                    break;
                case 2:
                    disciplinaControlador.criarTurma(scanner);
                    break;
                case 3:
                    disciplinaControlador.listarTurmas();
                    break;
                case 4:
                    disciplinaControlador.listarAlunosTurma(scanner);
                    break;
                case 0:
                    disciplinaControlador.atualizarDados();
                    System.out.println("Retornando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
}