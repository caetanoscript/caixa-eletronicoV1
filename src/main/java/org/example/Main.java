package org.example;

import model.*;
import util.GerenciarContas;
import util.NovaConta;
import util.TelaLogin;

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
                    TelaLogin.fazerLogin(contas, sc);
                    break;
                case "nova":
                    System.out.print("Digite seu CPF: ");
                    String cpf = sc.nextLine();
                    if (GerenciarContas.cpfExiste(contas,cpf)){
                        System.out.println("CPF já cadastrado. Deseja fazer login? (sim/nao)");
                        String resposta = sc.nextLine();
                        if (resposta.equalsIgnoreCase("sim")) {
                            TelaLogin.fazerLogin(contas, sc);
                        }
                        break;
                    }
                    System.out.print("Digite seu nome: ");
                    String nome = sc.nextLine();

                    String novaSenha = "";
                    String senhaComfirm = " ";
                    while (!novaSenha.equals(senhaComfirm)){
                        System.out.print("Digite sua senha: ");
                         novaSenha = sc.nextLine();
                        System.out.print("confirme sua senha: ");
                         senhaComfirm = sc.nextLine();
                         if(!novaSenha.equals(senhaComfirm)){
                             System.out.println("as senhas não coinciden tente novamente:");
                         }
                    }

                    System.out.print("qual tipo de conta : Corrente/Poupança ");
                    String tipo = sc.nextLine();

                    NovaConta contaNova = new NovaConta(nome, novaSenha, senhaComfirm, cpf  );

                    if (contaNova.verificarSenha()) {
                        Cliente cliente = new Cliente(nome, cpf);
                        ContaBancaria conta;
                        if(tipo.equalsIgnoreCase("corrente")){
                             conta = new ContaCorrente(cliente, novaSenha);
                        }else{
                             conta = new ContaPoupanca(cliente, novaSenha);
                        }
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