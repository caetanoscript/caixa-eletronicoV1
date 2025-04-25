package org.example;

import model.Cliente;
import model.ContaBancaria;
import model.NovaConta;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<ContaBancaria> contas = new ArrayList<>();
        boolean executando = true;

        while (executando) {
            System.out.println("bem vindo o ja possui conta, ou deseja fazer login: nova/login");
            String opcao = sc.nextLine();

            switch (opcao.toLowerCase()) {
                case "login":
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
                    break;
                case "nova":
                    System.out.print("Digite seu nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Digite seu CPF: ");
                    String cpf = sc.nextLine();
                    System.out.print("Digite sua senha: ");
                    String novaSenha = sc.nextLine();
                    System.out.print("confirme sua senha: ");
                    String senhaComfirm = sc.nextLine();

                    NovaConta contaNova = new NovaConta(nome, cpf, novaSenha, senhaComfirm );

                    if (contaNova.verificarSenha()) {
                        Cliente cliente = new Cliente(nome, cpf);
                        ContaBancaria conta = new ContaBancaria(cliente, novaSenha);
                        contas.add(conta);
                        System.out.println("Conta criada com sucesso!");
                    } else {
                        System.out.println("As senhas não coincidem. Tente novamente.");
                    }
                    break;

                case "sair":
                    executando = false;
                    System.out.println("Encerrando o sistema.");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        sc.close();
    }
}