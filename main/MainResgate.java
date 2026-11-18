package br.com.starlog.main;

import java.util.HashSet;
import java.util.Set;

import br.com.starlog.exception.CapacidadeExcedidaException;
import br.com.starlog.model.Carga;
import br.com.starlog.model.ModuloCarga;

public class MainResgate {
    public static void main(String[] args) {
        testarCodigoVazio();
        testarIgualdade();
        testarCapacidade();
        testarSeguroTotal();
    }

    private static void testarCodigoVazio() {
        try {
            new Carga("   ", "Eletronico", 2.0, 50.0);
            System.out.println("Falha: codigo vazio foi aceito.");
        } catch (IllegalArgumentException e) {
            System.out.println("Teste 1 concluido: codigo vazio foi rejeitado.");
        }
    }

    private static void testarIgualdade() {
        Set<Carga> cargas = new HashSet<>();
        cargas.add(new Carga("STAR-001", "Eletronico", 2.0, 50.0));
        cargas.add(new Carga("STAR-001", "Fragil", 5.0, 100.0));
        System.out.println("Teste 2: quantidade no HashSet = " + cargas.size());
    }

    private static void testarCapacidade() {
        ModuloCarga modulo = new ModuloCarga("MOD-TESTE", 2);
        try {
            modulo.carregarCarga(new Carga("STAR-002", "Eletronico", 1.0, 50.0));
            modulo.carregarCarga(new Carga("STAR-003", "Fragil", 3.0, 50.0));
            modulo.carregarCarga(new Carga("STAR-004", "Geral", 4.0, 75.0));
            System.out.println("Falha: capacidade excedida foi aceita.");
        } catch (CapacidadeExcedidaException e) {
            System.out.println("Teste 3 concluido: " + e.getMessage());
        }
    }

    private static void testarSeguroTotal() {
        ModuloCarga modulo = new ModuloCarga("MOD-SEGURO", 5);
        try {
            modulo.carregarCarga(new Carga("STAR-005", "Eletronico", 2.0, 50.0));
            modulo.carregarCarga(new Carga("STAR-006", "Fragil", 3.0, 50.0));
            System.out.println("Teste 4: seguro total = R$ " + modulo.calcularSeguroTotal());
        } catch (CapacidadeExcedidaException e) {
            System.out.println(e.getMessage());
        }
    }
}
