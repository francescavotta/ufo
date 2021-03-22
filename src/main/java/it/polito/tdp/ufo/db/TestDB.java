package it.polito.tdp.ufo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class TestDB {

	public static void main(String[] args) {
		
		String jdbcURL = "jdbc:mysql://localhost/ufo_sightings?user=root&password=root";
		try {
			Connection conn = DriverManager.getConnection(jdbcURL);
		
			String sql = "SELECT DISTINCT shape "//ci va lo spazio a fine riga perchè poi concatena tutte le righe
					+ "FROM sighting "//scrivere tutto in una riga semmai
					+ "WHERE shape<>'' "
					+ "ORDER BY shape ASC";
			PreparedStatement st = conn.prepareStatement(sql);//la mia navetta
			
			
			ResultSet res = st.executeQuery(sql);//assegno alla mia navetta una query da portare
			
			List<String> formeUFO = new ArrayList <String>();
			while(res.next()) {// mi fornisce la riga
				
				String forma = res.getString("shape");
				//il DB ha finito il suo lavoro!
				//Posso lavorare come voglio!
				formeUFO.add(forma);
			}
			
			st.close();//libero la memoria, lascio lo statment
			
			System.out.println(formeUFO);
			
			String sql2 = "SELECT COUNT(*) AS cnt FROM sighting "
					+ "WHERE shape = ?";
			
			String shapeScelta = "circle";
			
			PreparedStatement st2 = conn.prepareStatement(sql2);
			st2.setString(1, shapeScelta);
			
			ResultSet res2= st2.executeQuery();
			res2.first();
			int count = res2.getInt("cnt");
			st2.close();
			
			conn.close();//libero tutti gli statment
		}catch(SQLException e) {
			e.printStackTrace();
		}

	}

}
