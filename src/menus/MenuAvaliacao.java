package menus;

import controladores.AvaliacaoControlador;
import java.util.Scanner;

public class MenuAvaliacao {
    private AvaliacaoControlador avaliacaoControlador;
    private Scanner scanner;

    public MenuAvaliacao(AvaliacaoControlador avaliacaoControlador) {
        this.avaliacaoControlador = avaliacaoControlador;
        this.scanner = new Scanner(System.in);
    }

    public void exibir() {
        int opcao;
        do {
            System.out.println("\n=== MODO AVALIAÇÃO/FREQUÊNCIA ===");
            System.out.println("1. Lançar notas e presenças");
            System.out.println("2. Gerar relatório por turma");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    avaliacaoControlador.lancarNotas(scanner);
                    break;
                case 2:
                    avaliacaoControlador.gerarRelatorioTurma(scanner);
                    break;
                case 0:
                    System.out.println("Retornando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
}