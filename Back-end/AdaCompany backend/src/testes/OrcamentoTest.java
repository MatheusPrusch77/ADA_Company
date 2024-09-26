package testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import PersitenciaDados.Serializador;
import dao.exceptions.ExceptionDAO;
import model.Cliente;
import model.Orcamento;
import model.enums.StatusOrcamento;
import model.enums.TipoServico;

class OrcamentoTest {

    @Test
    void testSerializacaoOrcamento() throws ExceptionDAO {
        try {
            // criando cliente teste para orcamento
            Cliente cliente = new Cliente("CNPJ-2323", "Cliente Teste", 1, "cliente@teste.com", "senha123", "123456789");
            
            // orc teste
            Orcamento orcamentoOriginal = new Orcamento(cliente, TipoServico.DESENVOLVIMENTO, "Desenvolvimento de software do zero para....");
            orcamentoOriginal.setStatus(StatusOrcamento.DESENVOLVIMENTO);
            
            // Serializar orc
            Serializador.gravar("orcamento.dat", orcamentoOriginal);
            
            // Desserializar  orc
            Orcamento orcamentoLido = (Orcamento) Serializador.ler("orcamento.dat");
            
            // Verificacao
            assertEquals(orcamentoOriginal.getDescricao(), orcamentoLido.getDescricao());
            assertEquals(orcamentoOriginal.getStatus(), orcamentoLido.getStatus());
            assertEquals(orcamentoOriginal.getUsuario().getNomeCompleto(), orcamentoLido.getUsuario().getNomeCompleto());
            
        } catch (IOException | ClassNotFoundException e) {
            fail("Erro ao serializar ou desserializar o orçamento: " + e.getMessage());
        }
    }
}
