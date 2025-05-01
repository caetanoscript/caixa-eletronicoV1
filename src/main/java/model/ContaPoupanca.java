package model;

public class ContaPoupanca extends ContaBancaria{


    public ContaPoupanca(Cliente cliente, String senha) {
        super(cliente, senha);
    }

    @Override
    public boolean sacar(double saque) {
        Double taxa = 5.0;

        return super.sacar(saque + taxa);
    }




}
