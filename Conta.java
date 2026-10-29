
import java.util.ArrayList;

public class Conta {
    private double saldo;
    private ArrayList<Movimentacao> historico;

    public Conta() {
        this.saldo = 0.0;
        this.historico = new ArrayList<>();
    }

    public double getSaldo() {
        return saldo;
    }

    public void adicionarSaldo(double valor) {
        if (valor <= 0) {
            System.out.println("Valor inválido.");
            return;
        }

        saldo += valor;

        historico.add(new Movimentacao(
            "Depósito", "Adição de saldo", valor));

        System.out.println("Saldo adicionado com sucesso!");
    }

    public boolean debitar(double valor, String tipo,
                           String descricao) {
        if (valor <= 0) {
            System.out.println("Valor inválido.");
            return false;
        }

        if (valor > saldo) {
            System.out.println("Saldo insuficiente.");
            return false;
        }

        saldo -= valor;

        historico.add(new Movimentacao(
            tipo, descricao, valor));

        return true;
    }

    public void receber(double valor, String tipo,
                        String descricao) {
        saldo += valor;

        historico.add(new Movimentacao(
            tipo, descricao, valor));
    }

    public void exibirSaldo() {
        System.out.printf("Saldo atual: R$ %.2f%n", saldo);
    }

    public void exibirHistorico() {
        if (historico.isEmpty()) {
            System.out.println("Nenhuma movimentação realizada.");
            return;
        }

        for (Movimentacao movimentacao : historico) {
            movimentacao.exibir();
        }
    }
}
