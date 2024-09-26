package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {

	public Connection getConnection() {
		Connection conn = null;

		// tenta carregar o driver do mySQL. Se conseguir, tenta estabelecer conexão.
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			String user = "root";
			String password = "adacompany1234";
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/AdaCompany?useSSL=false", user, password);

			System.out.println("Conexao estabelecida.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void main(String[] args) {
		ConexaoBD conexaoBD = new ConexaoBD();
		Connection conn = conexaoBD.getConnection();

	}

}
