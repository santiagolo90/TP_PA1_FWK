package servicios;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import anotaciones.Columna;
import anotaciones.Id;
import anotaciones.Tabla;
import utilidades.UBean;
import utilidades.UConexion;

public class Consultas {

	public static Boolean guardar(Object o) throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		
		Class clase = o.getClass();
		ArrayList<Field> atributos =  UBean.obtenerAtributos(o);
		Tabla t = (Tabla)clase.getAnnotation(Tabla.class);
		//System.out.println("Clase: "+clase.getSimpleName());
        String tabla = t.nombre().toLowerCase();
        
        Object valores = "";
        if (tabla.isEmpty()) {
        	tabla = clase.getSimpleName().toLowerCase();
        }
        
		String query = "INSERT INTO " .concat(tabla).concat(" (");
		
		//System.out.println("Columnas: "+columnas);
		for(Field f: atributos ){
			//if (!f.getName().equals("id")) {}
			if (f.getAnnotation(Id.class) == null) {
				query = query.concat(f.getAnnotation(Columna.class).nombre()).concat(",");
				valores += String.valueOf("'");
				valores += String.valueOf(UBean.ejecutarGet(o, f.getAnnotation(Columna.class).nombre()));
				valores += String.valueOf("'");
				valores += String.valueOf(",");
			}

		}
		if(query.endsWith(",")){
			query = query.substring(0,query.length() - 1);
			query = query.concat(") VALUES (");
		}
		if(valores.toString().endsWith(",")){
			valores = valores.toString().substring(0,valores.toString().length() - 1);
		}
		query = query.concat(valores.toString()).concat(")");
		
		//System.out.println("tabla: "+query);
		
		try {
			UConexion UC = UConexion.getInstance();
			conn = UC.getConexion();
			ps = conn.prepareStatement(query);
			ps.execute();
			
			return Boolean.TRUE;
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
		return Boolean.FALSE;
	}
}
