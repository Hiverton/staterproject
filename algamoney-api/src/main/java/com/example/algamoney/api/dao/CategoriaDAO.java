package com.example.algamoney.api.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.algamoney.api.database.Banco;
import com.example.algamoney.api.model.Categoria;

public class CategoriaDAO {

	private static final String DB_CONN_URL = Banco.getStringApplication("DB_CONN_URL");
	private static final String DB_USER = Banco.getStringApplication("DB_USER");
	private static final String DB_PASSWORD = Banco.getStringApplication("DB_PASSWORD");
	
	
	public List<Categoria> lista() throws SQLException {
		List<Categoria> categorias = new ArrayList<Categoria>();
		
		Connection conn 		= null;
		Statement st 			= null;
		ResultSet rs			= null;		 
		
		try {
			
			StringBuffer query = new StringBuffer();
			query.append("Select * from Categoria ");
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    conn = DriverManager.getConnection(DB_CONN_URL, DB_USER, DB_PASSWORD);
		      
			st = conn.createStatement();
			
			rs = st.executeQuery(query.toString());
			
			while(rs.next()) {
				Categoria categoria = new Categoria();
				categoria.setCodigo(rs.getLong("codigo"));
				categoria.setNome(rs.getString("nome"));
				categorias.add(categoria);
			}
			
		 } catch (Exception e) {
		      System.err.println("Test failed: " + e.getMessage());
		      e.printStackTrace();
	     } finally {
		      if (st != null) {
		        st.close();
		      }
		      if (conn != null) {
		        conn.close();
		      }
	     }
		
		return categorias;
	}
	
	public Categoria save(Categoria categoria) {
		return categoria;
	}
	
	public Categoria findOne(Long codigo) {
		return new Categoria();
	}
	
	
}
