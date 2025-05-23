package menus;

import controladores.AlunoControlador;
import controladores.AvaliacaoControlador;
import controladores.DisciplinaControlador;
import java.util.Scanner;

public class MenuPrincipal {
    private AlunoControlador alunoControlador;
    private DisciplinaControlador disciplinaControlador;
    private AvaliacaoControlador avaliacaoControlador;
    private Scanner scanner;

    public MenuPrincipal(AlunoControlador alunoControlador,
                         DisciplinaControlador disciplinaControlador,
                         AvaliacaoControlador avaliacaoControlador) {
        this.alunoControlador = alunoControlador;
        this.disciplinaControlador = disciplinaControlador;
        this.avaliacaoControlador = avaliacaoControlador;
        this.scanner = new Scanner(System.in);
    }

    public void exibir() {
        int opcao;

        do {
            System.out.println("\n=== SISTEMA ACADÊMICO ===");
            System.out.println("1. Modo Aluno");
            System.out.println("2. Modo Disciplina/Turma");
            System.out.println("3. Modo Avaliação/Frequência");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    modoAluno();
                    break;
                case 2:
                    modoDisciplina();
                    break;
                case 3:
                    modoAvaliacao();
                    break;
                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private void modoAluno() {
        new MenuAluno(alunoControlador).exibir();
    }

    private void modoDisciplina() {
        new MenuDisciplina(disciplinaControlador).exibir();
    }

    private void modoAvaliacao() {
        new MenuAvaliacao(avaliacaoControlador).exibir();
    }
}