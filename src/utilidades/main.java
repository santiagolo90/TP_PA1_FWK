package utilidades;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		PreparedStatement ps = null;
		ArrayList<Persona> personas = new ArrayList<>();
		try {


			UConexion UC = UConexion.getInstance();
			
			conn = UC.getConexion();

			//ps = conn.prepareStatement("INSERT INTO PERSONAS ( nombre, apellido, edad) VALUES ('Mario','Argento','50' )");
			//ps.execute();
			
			ps = conn.prepareStatement("SELECT * FROM PERSONAS");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				personas.add(new Persona(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
			}
			personas.stream().forEach(p -> System.out.println(p));
		
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {;
				ps.close();
				conn.close();
			} catch (Exception e) {
				// TODO: handle exception
			}			
		}
	}

}
