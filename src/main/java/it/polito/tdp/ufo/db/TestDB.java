package it.polito.tdp.ufo.db;

import java.sql.Connection;
import java.sql.DriverManager;
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
		
			Statement st = conn.createStatement();//la mia navetta
			
			String sql = "SELECT DISTINCT shape "//ci va lo spazio a fine riga perchè poi concatena tutte le righe
					+ "FROM sighting "//scrivere tutto in una riga semmai
					+ "WHERE shape<>'' "
					+ "ORDER BY shape ASC";
			
			
			ResultSet res = st.executeQuery(sql);//assegno alla mia navetta una query da portare
			
			List<String> formeUFO = new ArrayList <String>();
			while(res.next()) {// mi fornisce la riga
				
				String forma = res.getString("shape");
				//il DB ha finito il suo lavoro!
				//Posso lavorare come voglio!
				formeUFO.add(forma);
			}
			
			System.out.println(formeUFO);
			
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}

	}

}
