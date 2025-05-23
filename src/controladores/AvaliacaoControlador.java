package controladores;

import modos.*;
import utilitarios.GerenciadorArquivos;

import java.util.*;

public class AvaliacaoControlador {
    private DisciplinaControlador disciplinaControlador;
    private GerenciadorArquivos gerenciadorArquivos;
    private Map<String, ResultadoAluno> resultados;

    public AvaliacaoControlador(DisciplinaControlador disciplinaControlador) {
        this.disciplinaControlador = disciplinaControlador;
        this.gerenciadorArquivos = new GerenciadorArquivos();
        this.resultados = gerenciadorArquivos.carregarResultados();
    }

    public void lancarNotas(Scanner scanner) {
        System.out.print("Código da turma: ");
        String codigoTurma = scanner.nextLine();

        Turma turma = disciplinaControlador.buscarTurma(codigoTurma);
        if (turma == null) {
            System.out.println("Turma não encontrada!");
            return;
        }

        System.out.println("Lançamento de notas para " + turma.getDisciplina().getNome());
        turma.getAlunosMatriculados().forEach(matricula -> {
            ResultadoAluno resultado = resultados.getOrDefault(matricula + codigoTurma,
                    new ResultadoAluno(matricula));

            System.out.println("\nAluno: " + matricula);
            resultado.setP1(lerNota(scanner, "P1"));
            resultado.setP2(lerNota(scanner, "P2"));
            resultado.setP3(lerNota(scanner, "P3"));
            resultado.setListas(lerNota(scanner, "Listas"));
            resultado.setSeminario(lerNota(scanner, "Seminário"));
            resultado.setTotalAulas(lerInteiro(scanner, "Total de aulas"));
            resultado.setPresencas(lerInteiro(scanner, "Presenças"));

            resultado.calcularMediaFinal(turma.getFormaAvaliacao());
            resultados.put(matricula + codigoTurma, resultado);
        });

        gerenciadorArquivos.salvarResultados(resultados);
    }

    private double lerNota(Scanner scanner, String tipo) {
        System.out.print(tipo + ": ");
        return scanner.nextDouble();
    }

    private int lerInteiro(Scanner scanner, String campo) {
        System.out.print(campo + ": ");
        return scanner.nextInt();
    }

    public void gerarRelatorioTurma(Scanner scanner) {
        System.out.print("Código da turma: ");
        String codigoTurma = scanner.nextLine();

        Turma turma = disciplinaControlador.buscarTurma(codigoTurma);
        if (turma == null) {
            System.out.println("Turma não encontrada!");
            return;
        }

        System.out.println("\n=== RELATÓRIO DA TURMA ===");
        System.out.println(turma.getDisciplina().getNome() + " - " + turma.getCodigo());
        System.out.println("Forma de avaliação: " + turma.getFormaAvaliacao().getFormula());

        turma.getAlunosMatriculados().forEach(matricula -> {
            ResultadoAluno resultado = resultados.get(matricula + codigoTurma);
            if (resultado != null) {
                System.out.println("\nAluno: " + matricula);
                System.out.printf("Média Final: %.2f\n", resultado.getMediaFinal());
                System.out.printf("Frequência: %.1f%%\n", resultado.getFrequencia());
                System.out.println("Status: " + resultado.getStatus());
            }
        });
    }
}