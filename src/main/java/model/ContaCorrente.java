package model;

public class ContaCorrente extends ContaBancaria{


    public ContaCorrente(Cliente cliente, String senha) {
        super(cliente, senha);
    }

    @Override
    public boolean sacar(double saque) {
        Double taxa = 2.50;

        return super.sacar(saque + taxa);
    }

}
