package it.polito.tdp.ufo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SightingDAO {

	public List<String> readShapes(){
		try {
			Connection conn = DBConnect.getConnection();

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

			st.close();
			conn.close();
			return formeUFO;
		} catch(SQLException e) {
			throw new RuntimeException("Datebase error in readShape",e);
		}
	}

	public int countByShape(String shape) {
		try {
			Connection conn = DBConnect.getConnection();
			String sql2 = "SELECT COUNT(*) AS cnt FROM sighting "
					+ "WHERE shape = ?";

			PreparedStatement st2 = conn.prepareStatement(sql2);
			st2.setString(1, shape);

			ResultSet res2= st2.executeQuery();
			res2.first();
			int count = res2.getInt("cnt");
			//System.out.println(count);
			st2.close();
			conn.close();

			return count;
		}catch(SQLException e) {
			throw new RuntimeException("Database error in countShape",e);
		}

	}
}
