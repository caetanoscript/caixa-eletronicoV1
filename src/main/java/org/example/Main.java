package org.example;

import model.*;
import util.GerenciarContas;
import util.NovaConta;
import util.TelaCadastro;
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
                    TelaCadastro.cadastrar(contas, sc);
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