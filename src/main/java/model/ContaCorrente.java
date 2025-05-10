package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContaCorrente extends ContaBancaria{

    private Double saqueDia = 0.0;
    private LocalDate ultimoSaqueDia = null;
    private double limiteSaqueDiario = 1000;
    List<CartaoVirtual> seusCartoes;

    public ContaCorrente(Cliente cliente, String senha) {
        super(cliente, senha);
        seusCartoes = new ArrayList<>();
    }
    public void adicionarCartao(CartaoVirtual cartao){
        seusCartoes.add(cartao);
    }
    public void removerCartao(String numeroC){
        CartaoVirtual cartaoRemover = null;
        for(CartaoVirtual c : seusCartoes){
           if (c.getNumeroDocartao().equals(numeroC)){
               cartaoRemover = c;
               break;
           }
           if (cartaoRemover != null){
               seusCartoes.remove(cartaoRemover);
               System.out.println("cartão removido! ");
           }else{
               System.out.println("cartão não encontrado! ");
           }
       }
    }
    public CartaoVirtual localizarCartao(String numeroCartao){
        for(CartaoVirtual c: seusCartoes){
            if (c.getNumeroDocartao().equals(numeroCartao)){
                return c;
            }
        }
        return null;
    }
    public boolean transferirCredito(ContaBancaria destino, double valor, String numeroDoCartao) {
        double taxa = 5.0;
        double valorTotal = valor + taxa;

        CartaoVirtual cartaoLocalizado = localizarCartao(numeroDoCartao);

        if (cartaoLocalizado == null) {
            System.out.println("Cartão não encontrado.");
            return false;
        }
        if (cartaoLocalizado.getLimiteCredito() < valorTotal) {
            System.out.println("Limite de crédito insuficiente.");
            return false;
        }

        double novoLimite = cartaoLocalizado.getLimiteCredito() - valorTotal;
        cartaoLocalizado.setSaldoDisponiovel(novoLimite);
        destino.depositar(valor);
        extrato.add(String.format("Transferência no crédito para %s | Valor: R$ %.2f (taxa: R$ %.2f)",
                destino.getCliente().getCpf(), valor, taxa));

        System.out.println("Transferência no crédito realizada com sucesso!");
        return true;
    }
    public void exibirCartoes() {
        System.out.println("Seus cartões:");
        for (CartaoVirtual cartao : seusCartoes) {
            cartao.exibirCartao();
        }
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
