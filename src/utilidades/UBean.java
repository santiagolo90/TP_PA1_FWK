package utilidades;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.sun.org.apache.xpath.internal.compiler.OpCodes;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

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
		ArrayList<Field> atributos =  obtenerAtributos(o);
		
		atributos.forEach(field->{
			
			if(field.getName().equals(att)){
				final boolean acc = field.isAccessible();
				try {
					field.setAccessible(true);
					field.set(o, valor);
					field.setAccessible(acc);	
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				} finally {
					field.setAccessible(acc);
				}
			}
			
		});
	}
	
	/***
	 * ejecutarGet(Object o, String att): devolverá el valor del atributo pasado por
	 * parámetro, ejecutando el getter dentro del objeto
	 * @param o
	 * @param att
	 * @return
	 * @throws Exception 
	 */
	public static Object ejecutarGet(Object o, String att) throws Exception {
		ArrayList<Field> atributos =  obtenerAtributos(o);
		Object valor;
		////java.util.List<Field> atributos =  obtenerAtributos(o);
		//java.util.List<Field> lalala = atributos.stream().filter(field -> field.getName().equals(att)).collect(Collectors.toList());
		//Optional<Field> attributo = atributos.stream().filter(field -> field.getName().equals(att)).findFirst();
		
		//Field field = attributo.orElseThrow(() -> new Exception("No conozco el atributo"));
		
		for(Field f: atributos ){
			if(f.getName().equals(att)){
				f.setAccessible(true);
				valor = f.get(o);
				return valor;
				}
			}

		return "No conozco el atributo";
	}
}
