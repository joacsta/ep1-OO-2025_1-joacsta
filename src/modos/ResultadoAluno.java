package modos;

public class ResultadoAluno {
    private String matricula;
    private double p1;
    private double p2;
    private double p3;
    private double listas;
    private double seminario;
    private int totalAulas;
    private int presencas;
    private double mediaFinal;
    private boolean aprovado;

    public ResultadoAluno(String matricula) {
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public double getP1() {
        return p1;
    }

    public void setP1(double p1) {
        this.p1 = p1;
    }

    public double getP2() {
        return p2;
    }

    public void setP2(double p2) {
        this.p2 = p2;
    }

    public double getP3() {
        return p3;
    }

    public void setP3(double p3) {
        this.p3 = p3;
    }

    public double getListas() {
        return listas;
    }

    public void setListas(double listas) {
        this.listas = listas;
    }

    public double getSeminario() {
        return seminario;
    }

    public void setSeminario(double seminario) {
        this.seminario = seminario;
    }

    public int getTotalAulas() {
        return totalAulas;
    }

    public void setTotalAulas(int totalAulas) {
        this.totalAulas = totalAulas;
    }

    public int getPresencas() {
        return presencas;
    }

    public void setPresencas(int presencas) {
        this.presencas = presencas;
    }

    public double getMediaFinal() {
        return mediaFinal;
    }

    public void setMediaFinal(double mediaFinal) {
        this.mediaFinal = mediaFinal;
    }

    public boolean isAprovado() {
        return aprovado;
    }

    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
    }

    public void calcularMediaFinal(FormaAvaliacao forma) {
        if (forma == FormaAvaliacao.METODO1) {
            this.mediaFinal = (p1 + p2 + p3 + listas + seminario) / 5;
        } else {
            this.mediaFinal = (p1 + (p2 * 2) + (p3 * 3) + listas + seminario) / 8;
        }
        this.aprovado = mediaFinal >= 5 && getFrequencia() >= 75;
    }

    public double getFrequencia() {
        return (double) presencas / totalAulas * 100;
    }

    public String getStatus() {
        if (!aprovado) {
            return mediaFinal < 5 ? "Reprovado por nota" : "Reprovado por frequência";
        }
        return "Aprovado";
    }
}
