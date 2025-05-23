import controladores.AlunoControlador;
import controladores.AvaliacaoControlador;
import controladores.DisciplinaControlador;
import menus.MenuPrincipal;

public class Main {
    public static void main(String[] args) {
        DisciplinaControlador disciplinaControlador = new DisciplinaControlador();
        AvaliacaoControlador avaliacaoControlador = new AvaliacaoControlador(disciplinaControlador);
        MenuPrincipal menu = new MenuPrincipal(
                new AlunoControlador(),
                disciplinaControlador,
                avaliacaoControlador
        );
        menu.exibir();
    }
}