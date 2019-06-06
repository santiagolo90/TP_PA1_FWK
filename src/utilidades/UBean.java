package utilidades;

import java.util.ArrayList;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class UBean {
	
	 
	
	/***
	 * Devuelve un ArrayList<Field> con todos los atributos que posee el parámetro Object
	 * @param Object objeto
	 * @return ArrayList Field
	 */
	public static ArrayList<Field> obtenerAtributos(Object o){
		ArrayList<Field> atributos =  new ArrayList<Field>();
		Class c = o.getClass();
		//Field[] atr = c.getFields();
		Field[] atr = c.getDeclaredFields();

		for(Field f: atr ){
			atributos.add(f);
		}
		return atributos;
	}
	
	/***
	 * ejecutarSet(Object o, String att, Object valor): Se debe ejecutar el método 
	 * Setter del String dentro del Object.
	 * @param o
	 * @param att
	 * @param valor
	 */
	public static void ejecutarSet(Object o, String att, Object valor) {
		
	}
	
	/***
	 * ejecutarGet(Object o, String att): devolverá el valor del atributo pasado por
	 * parámetro, ejecutando el getter dentro del objeto
	 * @param o
	 * @param att
	 * @return
	 */
	public static String ejecutarGet(Object o, String att) {
		return "";
	}
}
