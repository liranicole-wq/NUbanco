
public class Usuario {
    private String nome;
    private String cpf;
    private String email;
    private Conta conta;

    public Usuario(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.conta = new Conta();
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public Conta getConta() {
        return conta;
    }

    public void exibirDados() {
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("E-mail: " + email);
        System.out.printf("Saldo: R$ %.2f%n", conta.getSaldo());
    }
}
