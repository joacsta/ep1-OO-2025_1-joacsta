package modos;

public enum FormaAvaliacao {
    METODO1("(P1 + P2 + P3 + L + S) / 5"),
    METODO2("(P1 + P2*2 + P3*3 + L + S) / 8");

    private final String formula;

    FormaAvaliacao(String formula) {
        this.formula = formula;
    }

    public String getFormula() {
        return formula;
    }
}