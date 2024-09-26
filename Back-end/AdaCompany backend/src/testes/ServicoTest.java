package testes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import dao.exceptions.ExceptionDAO;
import model.Servico;
import model.enums.TipoServico;

public class ServicoTest {
	//lembrando que essa classe serve apenas para instaciar no orçamento.
	
    @Test
    public void testGetValorAdaptacao() {
        double valorEsperado = 5000.00;
        double valorRetornado = 0;
		try {
			valorRetornado = Servico.getValorBD(TipoServico.ADAPTACAO);
		} catch (ExceptionDAO e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        assertEquals(valorEsperado, valorRetornado, 0.01); // Utilizamos uma margem de erro pequena devido à precisão dos valores double
    }

    @Test
    public void testGetValorDesenvolvimento() {
        double valorEsperado = 7000.00;
        double valorRetornado = 0;
		try {
			valorRetornado = Servico.getValorBD(TipoServico.DESENVOLVIMENTO);
		} catch (ExceptionDAO e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        assertEquals(valorEsperado, valorRetornado, 0.01);
    }

    @Test
    public void testGetValorConsultoria() throws ExceptionDAO {
        double valorEsperado = 4000.00;
        double valorRetornado = Servico.getValorBD(TipoServico.CONSULTORIA);
        assertEquals(valorEsperado, valorRetornado, 0.01);
    }
}
