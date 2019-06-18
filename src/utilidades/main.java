package utilidades;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

import servicios.Consultas;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		PreparedStatement ps = null;
		ArrayList<Persona> personas = new ArrayList<>();
		ArrayList<Field> atributos =  new ArrayList<Field>();
		Persona p = new Persona();
		Auto a = new Auto();
		try {


			//UConexion UC = UConexion.getInstance();
			
			//conn = UC.getConexion();

			//ps = conn.prepareStatement("INSERT INTO PERSONAS ( nombre, apellido, edad) VALUES ('Mario','Argento','50' )");
			//ps.execute();
			
			//ps = conn.prepareStatement("SELECT * FROM PERSONAS");
			//ResultSet rs = ps.executeQuery();
			//while(rs.next()){
			//	personas.add(new Persona(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
			//}
			//personas.stream().forEach(p -> System.out.println(p));
			UBean.ejecutarSet(p,"id",85);
			UBean.ejecutarSet(p,"nombre","Pepe");
			UBean.ejecutarSet(p,"apellido","Argento");
			UBean.ejecutarSet(p,"edad",5);
			
			UBean.ejecutarSet(a,"id",3);
			UBean.ejecutarSet(a,"marca","chery");
			UBean.ejecutarSet(a,"color","amarillo");
			//System.out.println(UBean.ejecutarGet(p, "apellido"));
			//for(Field f: atributos ){
				
			//	System.out.println("Atributo for: "+f.getName());//solo el atributo
			//	System.out.println("tipo for: "+ f.getType());// de que tipo es el atributo
			//}
			
			//Consultas.guardar(p);
			//Consultas.guardar(a);
			//Consultas.modificar(a);
			//Consultas.eliminar(a);
			Consultas.obtenerPorId(p.getClass(), 1);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {;
				//ps.close();
				//conn.close();
			} catch (Exception e) {
				// TODO: handle exception
			}			
		}
	}

}
