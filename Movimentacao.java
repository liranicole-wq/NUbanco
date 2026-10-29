
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Movimentacao {
    private String tipo;
    private String descricao;
    private double valor;
    private LocalDateTime data;

    public Movimentacao(String tipo, String descricao, double valor) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.valor = valor;
        this.data = LocalDateTime.now();
    }

    public void exibir() {
        DateTimeFormatter formato =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("-------------------------");
        System.out.println("Tipo: " + tipo);
        System.out.println("Descrição: " + descricao);
        System.out.printf("Valor: R$ %.2f%n", valor);
        System.out.println("Data: " + data.format(formato));
    }
}
