package model;

import java.util.Random;

public class CartaoVirtual {

    private String numeroDocartao;
    private String validade;
    private String codigoCvv;
    private String nomeTitular;
    private Double limiteCredito;
    private Double saldoDisponiovel;

    public CartaoVirtual( String nomeTitular) {
        this.validade = gerarValidade();
        this.codigoCvv = gerarCvv();
        this.nomeTitular = nomeTitular;
        this.limiteCredito = 1000.0;
        this.saldoDisponiovel = limiteCredito;
        this.numeroDocartao = gerarNumeroCartao();
    }

    public String gerarValidade(){
        Random rand = new Random();
        int mes = rand.nextInt(12) + 1;
        int ano = rand.nextInt(5) + 25;
        return String.format("%02d/%02d", mes, ano);
    }
    private String gerarCvv(){
        Random rand = new Random();
        return String.format("%03d", rand.nextInt(1000));
    }

    private String gerarNumeroCartao() {
        Random rand = new Random();
        StringBuilder num = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            num.append(rand.nextInt(10));
        }
        return num.toString();
    }

    public String getNumeroDocartao() {
        return numeroDocartao;
    }

    public Double getLimiteCredito() {
        return limiteCredito;
    }

    public void setSaldoDisponiovel(Double saldoDisponiovel) {
        this.saldoDisponiovel = saldoDisponiovel;
    }

    public void exibirCartao(){
        System.out.println("=== Cartão Virtual Criado ===");
        System.out.println("Titular: " + nomeTitular);
        System.out.println("Número: " + numeroDocartao);
        System.out.println("Validade: " + validade);
        System.out.println("CVV: " + codigoCvv);
        System.out.println("Limite: R$ " + limiteCredito);
        System.out.println("Disponível: R$ " + saldoDisponiovel);
        System.out.println("=============================");
    }
}
