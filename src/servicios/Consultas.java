package servicios;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.jdbc.ResultSetMetaData;

import anotaciones.Columna;
import anotaciones.Id;
import anotaciones.Tabla;
import utilidades.Persona;
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
	
	
	public static Boolean modificar(Object o) throws Exception {
		Boolean result = Boolean.FALSE;
		Class clase = o.getClass();
		ArrayList<Field> atributos =  UBean.obtenerAtributos(o);
		Tabla t = (Tabla)clase.getAnnotation(Tabla.class);
        String tabla = t.nombre();
        if (tabla.isEmpty()) {
        	tabla = clase.getSimpleName().toLowerCase();
        }
		String query = "UPDATE " .concat(tabla).concat(" SET ");
        String id ="";
        
		for(Field f: atributos ){
			//if (!f.getName().equals("id")) {}
			if (f.getAnnotation(Id.class) == null) {
				query = query.concat(f.getAnnotation(Columna.class).nombre()).concat("=");
				query = query.concat("'");
				query = query.concat(String.valueOf(UBean.ejecutarGet(o, f.getAnnotation(Columna.class).nombre())));
				query = query.concat("',");
			}else {
				if(id.isEmpty()) {

					id = String.valueOf(UBean.ejecutarGet(o, f.getName()));
					
				}
				
			}

		}
		if(query.endsWith(",")){
			query = query.substring(0,query.length() - 1);
		}
		query = query.concat(" WHERE id=").concat(id);
		//System.out.println("query: "+query);
		result = ejecutarQuery(query);
		
		return result;
	}
	
	public static Boolean eliminar(Object o) throws Exception {
		//DELETE FROM `auto` WHERE 0
		Boolean result = Boolean.FALSE;
		Class clase = o.getClass();
		ArrayList<Field> atributos =  UBean.obtenerAtributos(o);
		Tabla t = (Tabla)clase.getAnnotation(Tabla.class);
        String tabla = t.nombre();
        if (tabla.isEmpty()) {
        	tabla = clase.getSimpleName().toLowerCase();
        }
		String query = "DELETE FROM " .concat(tabla);
        String id ="";
        
		for(Field f: atributos ){
			//if (!f.getName().equals("id")) {}
			if (f.getAnnotation(Id.class) != null) {
				if(id.isEmpty()) {
					id = String.valueOf(UBean.ejecutarGet(o, f.getName()));
				}
			}

		}
		query = query.concat(" WHERE id=").concat(id);
        //System.out.println("tabla: "+query);
		result = ejecutarQuery(query);
		return result;
	}
	
	public static Boolean  obtenerPorId(Class c, Object id) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		
		Class clase = c;
		
		
		Field[] atributos = c.getDeclaredFields();
		Tabla t = (Tabla)clase.getAnnotation(Tabla.class);
        String tabla = t.nombre();
        if (tabla.isEmpty()) {
        	tabla = clase.getSimpleName().toLowerCase();
        }
        
        String idNombre = "";
        String idValor = "";
		String query = "SELECT ";
		
		for(Field f: atributos ){
			if (f.getAnnotation(Id.class) == null) {
				query = query.concat(f.getAnnotation(Columna.class).nombre()).concat(" ,");
			}else {
				if(idNombre.isEmpty()) {

					query = query.concat(f.getName());
					query = query.concat(", ");
					idNombre = f.getName();
					idValor = String.valueOf(id);
					
				}
			}

		}
		if(query.endsWith(",")){
			query = query.substring(0,query.length() - 1);
		}
		query = query.concat("FROM ").concat(tabla).concat(" WHERE ").concat(idNombre).concat("=").concat(idValor);
		try {
			UConexion UC = UConexion.getInstance();
			conn = UC.getConexion();
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
            ResultSetMetaData metadata = (ResultSetMetaData) rs.getMetaData();
            int cantColumnas = metadata.getColumnCount();
            
			while(rs.next()){
                for (int i = 1; i <= cantColumnas; i++) {
                    String colNombre = metadata.getColumnName(i);
                    UBean.ejecutarSet(c, colNombre, rs.getObject(i));
                    System.out.println(rs.getObject(i));
                }
			}
			
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
	
	
	private static Boolean ejecutarQuery(String query) {
		Connection conn = null;
		PreparedStatement ps = null;
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
