package menus;

import controladores.AlunoControlador;
import controladores.DisciplinaControlador;

import java.util.Scanner;

public class MenuAluno {
    private AlunoControlador alunoControlador;
    private Scanner scanner;

    public MenuAluno(AlunoControlador alunoControlador) {
        this.alunoControlador = alunoControlador;
        this.scanner = new Scanner(System.in);
    }

    public void exibir() {
        int opcao;

        do {
            System.out.println("\n=== MODO ALUNO ===");
            System.out.println("1. Cadastrar Aluno");
            System.out.println("2. Editar Aluno");
            System.out.println("3. Listar Alunos");
            System.out.println("4. Matricular em Disciplina");
            System.out.println("5. Trancar Disciplina");
            System.out.println("6. Trancar Semestre");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    alunoControlador.cadastrarAluno(scanner);
                    break;
                case 2:
                    alunoControlador.editarAluno(scanner);
                    break;
                case 3:
                    alunoControlador.listarAlunos();
                    break;
                case 4:
                    alunoControlador.matricularDisciplina(scanner);
                    break;
                case 5:
                    alunoControlador.trancarDisciplina(scanner);
                    break;
                case 6:
                    alunoControlador.trancarSemestre(scanner);
                    break;
                case 0:
                    System.out.println("Retornando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    public static class MenuDisciplina {
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
}