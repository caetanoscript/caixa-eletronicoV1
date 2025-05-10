package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContaBancaria {

    CartaoVirtual cartao;
    private Cliente cliente;
    private String numeroConta;
    private String senha;
    private Double saldo;
    List<String> extrato = new ArrayList<>();


    public ContaBancaria(Cliente cliente, String senha) {
        this.cliente = cliente;
        this.senha = senha;
        this.numeroConta = gerarNumeroConta();
        this.saldo = 0.0;
        extrato.add("conta criada com saldo: R$0.00");

    }

    private String gerarNumeroConta() {
        Random random = new Random();
        int numero = 100000 + random.nextInt(900000); // número de 6 dígitos
        return String.valueOf(numero);
    }

    public boolean autenticar(String senhaDigitada) {
        return senha.equals(senhaDigitada);
    }

    public void depositar(double deposito) {
        saldo += deposito;
        extrato.add("deposito de : " + deposito);
        System.out.println("deposito feito com sucesso! ");
    }

    public boolean sacar(double saque) {
        if (saque <= saldo) {
            saldo -= saque;
            extrato.add("saque de : " + saque);
            System.out.println("saque feito com sucesso!");
            return true;
        } else {
            System.out.println("você não tem saldo suficiente para o saque.");
            System.out.println("seu saldo disponível é de: " + saldo);
            return false;
        }
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public Double getSaldo() {

        return saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String nome() {
        return cliente.getNome();
    }



    public void exibirExtrato() {
        for (String E : extrato) {
            System.out.println("extrato: \n" + E);
        }
    }

    public boolean transferir(ContaBancaria destino, double valor) {
        double taxa = 0;
        double valorTotal = valor + taxa;

        if (valorTotal <= saldo) {
            saldo -= valorTotal;
            destino.depositar(valor);
            extrato.add(String.format("Transferência para %s | Valor: R$ %.2f", destino.getCliente().getCpf(), valorTotal));
            return true;
        } else {
            System.out.println("Transferência falhou: saldo insuficiente.");
            return false;
        }
    }




}
