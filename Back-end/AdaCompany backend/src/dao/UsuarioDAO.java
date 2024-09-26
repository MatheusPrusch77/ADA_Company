package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.exceptions.ExceptionDAO;
import model.Cliente;
import model.Funcionario;
import model.Usuario;

public class UsuarioDAO {

	public void cadastrar(Usuario user) throws ExceptionDAO {
		String sqlUsuario = "insert into Usuario(NomeCompleto, Email, Senha, Telefone, TipoUsuario) value (?,?,?,?,?)";
		String sqlCliente = "insert into Cliente(NomeCliente, Telefone, Endereco, CNPJ, fk_UsuarioID) value (?,?,?,?,?)";
		String sqlFuncionario = "insert into Funcionario(NomeFuncionario, Cargo, fk_UsuarioID) VALUES (?, ?, ?)";
		
		Connection connection = null;
		PreparedStatement pStatementUsuario = null;
		PreparedStatement pStatementCliente = null;
		PreparedStatement pStatementFuncionario = null;
		ResultSet generatedKeys = null;
		
		try {
			connection = new ConexaoBD().getConnection();
			pStatementUsuario = connection.prepareStatement(sqlUsuario,  PreparedStatement.RETURN_GENERATED_KEYS);
			pStatementUsuario.setString(1, user.getNomeCompleto());
			pStatementUsuario.setString(2, user.getEmail());
			pStatementUsuario.setString(3, user.getSenha());
			pStatementUsuario.setString(4, user.getTelefone());
			
			//TipoUsuario BIT no BD.
			if (user instanceof Cliente) {
				pStatementUsuario.setBoolean(5, false); //Cliente
			} else {
				pStatementUsuario.setBoolean(5, true); //Admin
			}
			pStatementUsuario.executeUpdate();
			
			//O ID do user deve ser referenciado na tabela Cliente/Funcionario como chave estrangeira.
			generatedKeys = pStatementUsuario.getGeneratedKeys();
            if (generatedKeys.next()) {
                int userID = generatedKeys.getInt(1);
			
				if (user instanceof Cliente) {
		            Cliente cliente = (Cliente) user;
		            pStatementCliente = connection.prepareStatement(sqlCliente);
	                pStatementCliente.setString(1, cliente.getNomeCompleto());
	                pStatementCliente.setString(2, cliente.getTelefone());
	                pStatementCliente.setString(3, cliente.getEndereco());
	                pStatementCliente.setString(4, cliente.getCNPJ());
	                pStatementCliente.setInt(5, userID);
	                pStatementCliente.executeUpdate();
					
				}
				
				else if (user instanceof Funcionario) {
					Funcionario funcionario = (Funcionario) user;
					pStatementFuncionario = connection.prepareStatement(sqlFuncionario);
	                pStatementFuncionario.setString(1, funcionario.getNomeCompleto());
	                pStatementFuncionario.setString(2, funcionario.getCargo());
	                pStatementFuncionario.setInt(3, userID);
	                pStatementFuncionario.executeUpdate();
				}
            }
		} catch (SQLException e) {
			throw new ExceptionDAO("Erro ao cadastrar o novo usuário: " + e);
			
		} finally {
			try {
				
				if (pStatementUsuario != null) {pStatementUsuario.close();}
				else if (pStatementCliente != null) {pStatementCliente.close();}
				else if (pStatementFuncionario != null) {pStatementFuncionario.close();}
				
			} catch (SQLException e) {
				throw new ExceptionDAO("Erro ao fechar o statement: " + e);
				
			} try {
				if (connection != null) {connection.close();} 
			} catch (SQLException e) {
				throw new ExceptionDAO("Erro ao fechar a conexao do BD:" + e);
				}
			}
		}
		
	public ArrayList<Usuario> consultarUsuarios(String nome, int tipoConsulta) throws ExceptionDAO {
        String sqlCliente = "SELECT u.*, c.Telefone AS clienteTelefone, c.Endereco, c.NomeCliente, c.CNPJ " +
                            "FROM Usuario u JOIN Cliente c ON u.ID = c.fk_UsuarioID " +
                            "WHERE ";
        
        String sqlFuncionario = "SELECT u.*, f.NomeFuncionario, f.Cargo " +
                                "FROM Usuario u JOIN Funcionario f ON u.ID = f.fk_UsuarioID " +
                                "WHERE ";
        if (tipoConsulta == 1) {
        	sqlCliente += "u.NomeCompleto LIKE ?";
        	sqlFuncionario += "u.NomeCompleto LIKE ?";
        	
        } else {//consulta no email
        	sqlCliente += "u.Email LIKE ?";
        	sqlFuncionario += "u.Email LIKE ?";
        }

        PreparedStatement pStatementCliente = null;
        PreparedStatement pStatementFuncionario = null;
        Connection connection = null;
        ArrayList<Usuario> usuarios = new ArrayList<>();

        try {
            connection = new ConexaoBD().getConnection();
            
            // cliente
            pStatementCliente = connection.prepareStatement(sqlCliente);
            pStatementCliente.setString(1, "%" + nome + "%");
            ResultSet rsCliente = pStatementCliente.executeQuery();
            while (rsCliente.next()) {
                Cliente cliente = new Cliente();
                cliente.setNomeCompleto(rsCliente.getString("NomeCompleto"));
                cliente.setEmail(rsCliente.getString("Email"));
                cliente.setTelefone(rsCliente.getString("clienteTelefone"));
                cliente.setEndereco(rsCliente.getString("Endereco"));
                cliente.setCNPJ(rsCliente.getString("CNPJ"));
                usuarios.add(cliente);
            }
            
            // funcionario
            pStatementFuncionario = connection.prepareStatement(sqlFuncionario);
            pStatementFuncionario.setString(1, "%" + nome + "%");
            ResultSet rsFuncionario = pStatementFuncionario.executeQuery();
            while (rsFuncionario.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setNomeCompleto(rsFuncionario.getString("NomeCompleto"));
                funcionario.setEmail(rsFuncionario.getString("Email"));
                funcionario.setTelefone(rsFuncionario.getString("Telefone"));
                funcionario.setCargo(rsFuncionario.getString("Cargo"));
                usuarios.add(funcionario);
            }
            
        } catch (SQLException e) {
            throw new ExceptionDAO("Erro consultando usuários: " + e);
        } finally {
            try {
                if (pStatementCliente != null) pStatementCliente.close();
                if (pStatementFuncionario != null) pStatementFuncionario.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new ExceptionDAO("Erro ao fechar conexão: " + e);
            }
        }
        return usuarios;
    }
	
	public Usuario realizarLogin(String email, String senha) throws ExceptionDAO {
        String sql = "SELECT * FROM Usuario WHERE Email = ? AND Senha = ?";
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;

        try {
            connection = new ConexaoBD().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, email);
            pStatement.setString(2, senha);
            rs = pStatement.executeQuery();

            if (rs.next()) {
                int tipoUsuario = rs.getBoolean("TipoUsuario") ? 1 : 0;
                int userId = rs.getInt("ID");
                String nomeCompleto = rs.getString("NomeCompleto");
                String telefone = rs.getString("Telefone");

                if (tipoUsuario == 1) {
                    // Funcionario
                    String sqlFuncionario = "SELECT * FROM Funcionario WHERE fk_UsuarioID = ?";
                    PreparedStatement pStatementFuncionario = connection.prepareStatement(sqlFuncionario);
                    pStatementFuncionario.setInt(1, userId);
                    ResultSet rsFuncionario = pStatementFuncionario.executeQuery();
                    if (rsFuncionario.next()) {
                        Funcionario funcionario = new Funcionario();
                        funcionario.setNomeCompleto(nomeCompleto);
                        funcionario.setEmail(email);
                        funcionario.setTelefone(telefone);
                        funcionario.setCargo(rsFuncionario.getString("Cargo"));
                        funcionario.setID(rsFuncionario.getInt("ID"));
                        return funcionario;
                    }
                } else {
                    // Cliente
                    String sqlCliente = "SELECT * FROM Cliente WHERE fk_UsuarioID = ?";
                    PreparedStatement pStatementCliente = connection.prepareStatement(sqlCliente);
                    pStatementCliente.setInt(1, userId);
                    ResultSet rsCliente = pStatementCliente.executeQuery();
                    if (rsCliente.next()) {
                        Cliente cliente = new Cliente();
                        cliente.setNomeCompleto(nomeCompleto);
                        cliente.setEmail(email);
                        cliente.setTelefone(telefone);
                        cliente.setEndereco(rsCliente.getString("Endereco"));
                        cliente.setCNPJ(rsCliente.getString("CNPJ"));
                        cliente.setID(rsCliente.getInt("ID"));
                        return cliente;
                    }
                }
            } else {
                return null; // Login inválido
            }
        } catch (SQLException e) {
            throw new ExceptionDAO("Erro ao realizar login: " + e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pStatement != null) pStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new ExceptionDAO("Erro ao fechar conexão: " + e);
            }
        }
        return null;
    }

    public boolean validaLogin(String email, String senha) throws ExceptionDAO {
        String sql = "SELECT * FROM Usuario WHERE Email = ? AND Senha = ?";
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;

        try {
            connection = new ConexaoBD().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, email);
            pStatement.setString(2, senha);
            rs = pStatement.executeQuery();

            return rs.next(); // Retorna verdadeiro se existir um usuário com o email e senha fornecidos
        } catch (SQLException e) {
            throw new ExceptionDAO("Erro ao validar login: " + e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pStatement != null) pStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new ExceptionDAO("Erro ao fechar conexão: " + e);
            }
        }
    }
    
    public void editarUsuario(Usuario user) throws ExceptionDAO {
        String sqlUsuario = "UPDATE Usuario SET NomeCompleto = ?, Email = ?, Senha = ?, Telefone = ? WHERE ID = ?";
        String sqlCliente = "UPDATE Cliente SET NomeCliente = ?, Telefone = ?, Endereco = ?, CNPJ = ? WHERE fk_UsuarioID = ?";
        String sqlFuncionario = "UPDATE Funcionario SET NomeFuncionario = ?, Cargo = ? WHERE fk_UsuarioID = ?";
        
        Connection connection = null;
        PreparedStatement pStatementUsuario = null;
        PreparedStatement pStatementCliente = null;
        PreparedStatement pStatementFuncionario = null;
        
        try {
            connection = new ConexaoBD().getConnection();
            pStatementUsuario = connection.prepareStatement(sqlUsuario);
            pStatementUsuario.setString(1, user.getNomeCompleto());
            pStatementUsuario.setString(2, user.getEmail());
            pStatementUsuario.setString(3, user.getSenha());
            pStatementUsuario.setString(4, user.getTelefone());
            pStatementUsuario.setInt(5, user.getID());
            pStatementUsuario.executeUpdate();
            
            if (user instanceof Cliente) {
                Cliente cliente = (Cliente) user;
                pStatementCliente = connection.prepareStatement(sqlCliente);
                pStatementCliente.setString(1, cliente.getNomeCompleto());
                pStatementCliente.setString(2, cliente.getTelefone());
                pStatementCliente.setString(3, cliente.getEndereco());
                pStatementCliente.setString(4, cliente.getCNPJ());
                pStatementCliente.setInt(5, user.getID());
                pStatementCliente.executeUpdate();
                
            } else if (user instanceof Funcionario) {
                Funcionario funcionario = (Funcionario) user;
                pStatementFuncionario = connection.prepareStatement(sqlFuncionario);
                pStatementFuncionario.setString(1, funcionario.getNomeCompleto());
                pStatementFuncionario.setString(2, funcionario.getCargo());
                pStatementFuncionario.setInt(3, user.getID());
                pStatementFuncionario.executeUpdate();
            }
            
        } catch (SQLException e) {
            throw new ExceptionDAO("Erro ao editar o usuário: " + e);
            
        } finally {
            try {
                if (pStatementUsuario != null) { pStatementUsuario.close(); }
                if (pStatementCliente != null) { pStatementCliente.close(); }
                if (pStatementFuncionario != null) { pStatementFuncionario.close(); }
                if (connection != null) { connection.close(); }
            } catch (SQLException e) {
                throw new ExceptionDAO("Erro ao fechar conexão: " + e);
            }
        }
    }
}