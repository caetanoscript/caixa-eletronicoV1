package model;

import java.util.ArrayList;
import java.util.List;

public class ContaBancaria {

    private Cliente cliente;
    private String senha;
    private Double saldo;
    List<String> extrato = new ArrayList<>();

    public ContaBancaria(Cliente cliente, String senha) {
        this.cliente = cliente;
        this.senha = senha;
        this.saldo = 0.0;
        extrato.add("conta criada com saldo: R$0.00");
    }

    public boolean autenticar(String senhaDigitada ){
        return senha.equals(senhaDigitada);
    };

    public void depositar(double deposito){
        saldo += deposito;
        extrato.add("deposito de : " + deposito);
        System.out.println("deposito feito com sucesso! ");
    }

    public boolean sacar(double saque){
        if (saque <= saldo){
            saldo -= saque;
            extrato.add("saque de : " + saque);
            System.out.println("saque feito com sucesso! ");
        }else {
            System.out.println("você não tem saldo suficiente para o saque saldo disponivel:\n " +
                    "seu saldo disponivel é de : " + saldo);
        }
        return false;
    }

    public Double getSaldo() {
        return saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void exibirExtrato(){
        for(String E : extrato){
            System.out.println("extrato: \n" + E);
        }
    }

    public String nome(){
        return cliente.getNome();
    }
}
