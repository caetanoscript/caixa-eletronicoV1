package util;

import model.ContaBancaria;

import java.util.ArrayList;
import java.util.Scanner;

public class TelaLogin {

    public static void fazerLogin (ArrayList<ContaBancaria> contas, Scanner sc){
        System.out.print("Digite seu CPF: ");
        String cpfLogin = sc.nextLine();
        ContaBancaria contaEncontrada = null;

        for (ContaBancaria c : contas) {
            if (c.getCliente().getCpf().equals(cpfLogin)) {
                contaEncontrada = c;
                break;
            }
        }
        if (contaEncontrada != null) {
            System.out.print("Digite sua senha: ");
            String senha = sc.nextLine();

            if (contaEncontrada.autenticar(senha)) {
                int opcaoLogado;
                do {
                    System.out.println("\nBem-vindo, " + contaEncontrada.nome());
                    System.out.println("1 - Ver saldo");
                    System.out.println("2 - Depositar");
                    System.out.println("3 - Sacar");
                    System.out.println("4 - Ver extrato");
                    System.out.println("5 - transferencia");
                    System.out.println("0 - Sair");
                    System.out.print("Escolha uma opção: ");
                    opcaoLogado = sc.nextInt();
                    sc.nextLine();

                    switch (opcaoLogado) {
                        case 1:
                            System.out.println("Saldo atual: R$" + contaEncontrada.getSaldo());
                            break;
                        case 2:
                            System.out.print("Valor do depósito: ");
                            double deposito = sc.nextDouble();
                            contaEncontrada.depositar(deposito);
                            break;
                        case 3:
                            System.out.print("Valor do saque: ");
                            double saque = sc.nextDouble();
                            contaEncontrada.sacar(saque);
                            break;
                        case 4:
                            contaEncontrada.exibirExtrato();
                            break;
                        case 5:
                            System.out.print("Valor da transferência: ");
                            Double valorT = sc.nextDouble();
                            sc.nextLine();

                            if (valorT <= 0) {
                                System.out.println("Valor inválido para transferência.");
                                break;
                            }

                            System.out.print("Qual o CPF do destinatário: ");
                            String cpf = sc.nextLine();

                            ContaBancaria contaDestino = GerenciarContas.encontarContaCpf(contas, cpf);

                            if (contaDestino == null) {
                                System.out.println("Conta com esse CPF não foi encontrada.");
                            } else if (contaEncontrada == contaDestino) {
                                System.out.println("Você não pode transferir para a própria conta.");
                            } else {
                                boolean sucesso = contaEncontrada.transferir(contaDestino, valorT);
                                if (sucesso) {
                                    System.out.println("Transferência realizada com sucesso!");
                                } else {
                                    System.out.println("Transferência falhou. Verifique o saldo.");
                                }
                            }
                            break;
                        case 0:
                            System.out.println("Saindo da conta...");
                            break;
                        default:
                            System.out.println("Opção inválida.");
                    }
                } while (opcaoLogado != 0);
            } else
                System.out.println("Senha incorreta!");
        }else{
            System.out.println("Conta não encontrada.");

        }
    }

}
