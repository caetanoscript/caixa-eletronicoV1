package util;

import model.Cliente;
import model.ContaBancaria;
import model.ContaCorrente;
import model.ContaPoupanca;

import java.util.ArrayList;
import java.util.Scanner;

public class TelaCadastro {

    public static void cadastrar(ArrayList<ContaBancaria> contas, Scanner sc) {
        System.out.print("Digite seu CPF: ");
        String cpf = sc.nextLine();

        if (GerenciarContas.cpfExiste(contas, cpf)) {
            System.out.println("CPF já cadastrado. Deseja fazer login? (sim/nao)");
            String resposta = sc.nextLine();
            if (resposta.equalsIgnoreCase("sim")) {
                TelaLogin.fazerLogin(contas, sc);
            }
            return;
        }
        System.out.print("Digite seu nome: ");
        String nome = sc.nextLine();

        String novaSenha = "";
        String senhaComfirm = " ";
        while (!novaSenha.equals(senhaComfirm)) {
            System.out.print("Digite sua senha: ");
            novaSenha = sc.nextLine();
            System.out.print("Confirme sua senha: ");
            senhaComfirm = sc.nextLine();
            if (!novaSenha.equals(senhaComfirm)) {
                System.out.println("As senhas não coincidem. Tente novamente.");
            }
        }

        System.out.print("Qual tipo de conta: Corrente/Poupança? ");
        String tipo = sc.nextLine();

        NovaConta contaNova = new NovaConta(nome, novaSenha, senhaComfirm, cpf);

        if (contaNova.verificarSenha()) {
            Cliente cliente = new Cliente(nome, cpf);
            ContaBancaria conta;
            if (tipo.equalsIgnoreCase("corrente")) {
                conta = new ContaCorrente(cliente, novaSenha);
            } else {
                conta = new ContaPoupanca(cliente, novaSenha);
            }
            contas.add(conta);
            System.out.println("Conta criada com sucesso!");
        } else {
            System.out.println("As senhas não coincidem. Tente novamente.");
        }
    }
}
