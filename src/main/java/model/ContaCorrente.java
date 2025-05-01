package model;

import java.time.LocalDate;

public class ContaCorrente extends ContaBancaria{

    private Double saqueDia = 0.0;
    private LocalDate ultimoSaqueDia = null;
    private double limiteSaqueDiario = 1000;

    public ContaCorrente(Cliente cliente, String senha) {
        super(cliente, senha);
    }


    @Override
    public boolean sacar(double saque) {
        LocalDate hoje = LocalDate.now();
        Double taxa = 2.50;

        if (ultimoSaqueDia == null || !ultimoSaqueDia.equals(hoje)) {
            saqueDia = 0.0;
            ultimoSaqueDia = hoje;
        }
        if ((saqueDia + saque) > limiteSaqueDiario) {
            System.out.println("Limite diário de saque excedido. Você já sacou R$" + saqueDia + " hoje.");
            return  false;
        }
        boolean sucesso = super.sacar(saque + taxa);
        if (sucesso) {
            saqueDia += saque;
        }
        return sucesso;
    }

}
