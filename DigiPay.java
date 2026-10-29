
import java.util.ArrayList;
import java.util.Scanner;

public class DigiPay {
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.println("\n===== DIGIPAY =====");
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Consultar saldo");
            System.out.println("3 - Adicionar saldo");
            System.out.println("4 - Fazer Pix");
            System.out.println("5 - Realizar pagamento");
            System.out.println("6 - Consultar histórico");
            System.out.println("7 - Listar usuários");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = lerInteiro();

            switch (opcao) {
                case 1:
                    cadastrarUsuario();
                    break;
                case 2:
                    consultarSaldo();
                    break;
                case 3:
                    adicionarSaldo();
                    break;
                case 4:
                    fazerPix();
                    break;
                case 5:
                    realizarPagamento();
                    break;
                case 6:
                    consultarHistorico();
                    break;
                case 7:
                    listarUsuarios();
                    break;
                case 0:
                    System.out.println("Saindo da DigiPay...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static int lerInteiro() {
        while (!scanner.hasNextInt()) {
            System.out.print("Digite um número válido: ");
            scanner.nextLine();
        }

        int numero = scanner.nextInt();
        scanner.nextLine();
        return numero;
    }

    private static double lerValor() {
        while (true) {
            String entrada = scanner.nextLine()
                .trim().replace(",", ".");

            try {
                return Double.parseDouble(entrada);
            } catch (NumberFormatException e) {
                System.out.print("Digite um valor válido: ");
            }
        }
    }

    private static Usuario buscarUsuario(String cpf) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCpf().equals(cpf)) {
                return usuario;
            }
        }

        return null;
    }

    private static Usuario solicitarUsuario() {
        System.out.print("Digite o CPF do usuário: ");
        String cpf = scanner.nextLine().trim();

        Usuario usuario = buscarUsuario(cpf);

        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
        }

        return usuario;
    }

    private static void cadastrarUsuario() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine().trim();

        if (buscarUsuario(cpf) != null) {
            System.out.println("Este CPF já está cadastrado.");
            return;
        }

        System.out.print("E-mail: ");
        String email = scanner.nextLine();

        usuarios.add(new Usuario(nome, cpf, email));

        System.out.println("Usuário cadastrado com sucesso!");
    }

    private static void consultarSaldo() {
        Usuario usuario = solicitarUsuario();

        if (usuario != null) {
            usuario.getConta().exibirSaldo();
        }
    }

    private static void adicionarSaldo() {
        Usuario usuario = solicitarUsuario();

        if (usuario != null) {
            System.out.print("Valor para adicionar: R$ ");
            double valor = lerValor();

            usuario.getConta().adicionarSaldo(valor);
        }
    }

    private static void fazerPix() {
        System.out.println("Dados de quem envia:");
        Usuario remetente = solicitarUsuario();

        if (remetente == null) {
            return;
        }

        System.out.print("CPF de quem recebe: ");
        String cpfDestino = scanner.nextLine().trim();

        Usuario destinatario = buscarUsuario(cpfDestino);

        if (destinatario == null) {
            System.out.println("Destinatário não encontrado.");
            return;
        }

        if (remetente == destinatario) {
            System.out.println("Não é possível enviar Pix para si mesmo.");
            return;
        }

        System.out.print("Valor do Pix: R$ ");
        double valor = lerValor();

        boolean sucesso = remetente.getConta().debitar(
            valor, "Pix enviado",
            "Pix para " + destinatario.getNome()
        );

        if (sucesso) {
            destinatario.getConta().receber(
                valor, "Pix recebido",
                "Pix de " + remetente.getNome()
            );

            System.out.println("Pix realizado com sucesso!");
        }
    }

    private static void realizarPagamento() {
        Usuario usuario = solicitarUsuario();

        if (usuario == null) {
            return;
        }

        System.out.print("Nome da loja ou serviço: ");
        String loja = scanner.nextLine();

        System.out.print("Valor do pagamento: R$ ");
        double valor = lerValor();

        boolean sucesso = usuario.getConta().debitar(
            valor, "Pagamento",
            "Pagamento para " + loja
        );

        if (sucesso) {
            System.out.println("Pagamento realizado com sucesso!");
        }
    }

    private static void consultarHistorico() {
        Usuario usuario = solicitarUsuario();

        if (usuario != null) {
            usuario.getConta().exibirHistorico();
        }
    }

    private static void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        for (Usuario usuario : usuarios) {
            System.out.println("-------------------------");
            usuario.exibirDados();
        }
    }
}
