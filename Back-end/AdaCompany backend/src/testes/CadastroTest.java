package testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import PersitenciaDados.Serializador;
import model.Cliente;
import model.Funcionario;

public class CadastroTest {

    @Test
    public void testCadastroUsuario() {
        try {
            // Criando um cliente e serializando
            Cliente cliente = new Cliente("CNPJ-2323", "Cliente Teste", 1, "cliente@teste.com", "senha123", "123456789");
            Serializador.gravar("cliente.dat", cliente);
            
            // Desserializando o cliente
            Cliente clienteLido = (Cliente) Serializador.ler("cliente.dat");
            
            // Verificando se os dados foram preservados corretamente
            assertEquals(cliente.getNomeCompleto(), clienteLido.getNomeCompleto());
            assertEquals(cliente.getID(), clienteLido.getID());
            assertEquals(cliente.getEmail(), clienteLido.getEmail());
            assertEquals(cliente.getSenha(), clienteLido.getSenha());
            assertEquals(cliente.getTelefone(), clienteLido.getTelefone());
            
            // Criando um funcionário e serializando
            Funcionario funcionario = new Funcionario("Admin", "funcionario@teste.com", 1, "senha456", "987654321", "Administrador");
            Serializador.gravar("funcionario.dat", funcionario);
            
            // Desserializando o funcionário
            Funcionario funcionarioLido = (Funcionario) Serializador.ler("funcionario.dat");
            
            // Verificando se os dados foram preservados corretamente
            assertEquals(funcionario.getNomeCompleto(), funcionarioLido.getNomeCompleto());
            assertEquals(funcionario.getID(), funcionarioLido.getID());
            assertEquals(funcionario.getEmail(), funcionarioLido.getEmail());
            assertEquals(funcionario.getSenha(), funcionarioLido.getSenha());
            assertEquals(funcionario.getTelefone(), funcionarioLido.getTelefone());
            assertEquals(funcionario.getCargo(), funcionarioLido.getCargo());
            
        } catch (IOException | ClassNotFoundException e) {
            fail("Exceção ao serializar ou desserializar o usuário: " + e.getMessage());
        }
    }
}
