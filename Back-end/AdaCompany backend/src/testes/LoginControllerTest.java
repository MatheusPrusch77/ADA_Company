package testes;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import controller.LoginController;
import dao.exceptions.ExceptionDAO;
import model.Usuario;
import service.LoginService;

public class LoginControllerTest {
	//necessario estar com bd conectado. testes já validados.

    private LoginController loginController;

    @Before
    public void setUp() {
        loginController = new LoginController();
    }

    @Test
    public void testRealizarLogin() throws ExceptionDAO {
        String email = "rafael@example.com";
        String senha = "senhaRafael";
        Usuario usuario = loginController.realizarLogin(email, senha);
        
        assertNotNull(usuario);
        assertEquals(email, usuario.getEmail());
    }

    @Test
    public void testValidaLogin() throws ExceptionDAO {
        String email = "rafael@example.com";
        String senha = "senhaRafael";
        boolean result = loginController.validaLogin(email, senha);
        assertTrue(result);
    }

    @Test
    public void testLogout() {
        loginController.logout();
        assertNull(loginController.getUsuarioLogado());
    }
}
