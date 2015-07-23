package com.pylypchak.airfast.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pylypchak.airfast.annotation.Column;





public class Transformer {

	public static <T> List<T> transformResultSetIntoList(ResultSet rs, 
			Class<T> classT) throws SQLException,ReflectiveOperationException {
	     List<T> result=new ArrayList<T>();
		List<Field> annotatedFields = new ArrayList<Field>();
		List<Method> setMethods = new ArrayList<Method>();
		
		for (Field f : classT.getDeclaredFields()) {
			if (f.isAnnotationPresent(Column.class)) {
				annotatedFields.add(f);
				setMethods.add(getSetMethodByField(f, classT));
			}
		}
	
		while (rs.next()) {
			
			T temp = classT.newInstance();
			for (int i = 0; i < annotatedFields.size(); i++) {
				String columnName = annotatedFields.get(i)
						.getAnnotation(Column.class).name();
				setMethods.get(i).invoke(
						temp, rs.getObject(columnName));
			}
			result.add(temp);
		}
       return result;
	}
    
	public static <T> T transformResultSetIntoObject(ResultSet rs, Class<T> classT) throws SQLException, ReflectiveOperationException{
		List<T> resultList=transformResultSetIntoList(rs, classT);
		if (resultList.size()>0){
			return resultList.get(0);
		}
		return null;
	}
    public static <T >void valueIntoPreparedStatement(PreparedStatement ps, T value, String[] queue) throws  ReflectiveOperationException,   SQLException{
    	Class<?> classT=value.getClass();
    	List<Field> annotatedFields = new ArrayList<Field>();
		List<Method> getMethods = new ArrayList<Method>();
		
		for (Field f : classT.getDeclaredFields()) {
			if (f.isAnnotationPresent(Column.class)) {
				annotatedFields.add(f);
				getMethods.add(getGetMethodByField(f, classT));
			}
		}
		
		for (int i=0; i<queue.length; i++){
			int index=findFieldByColumnName(annotatedFields, queue[i]);
			if (index==-1) throw new RuntimeException();
			ps.setObject(i+1,getMethods.get(index).invoke(value));
		}
    }
    
    private static int findFieldByColumnName(List<Field> fields, String columnName){
    	for (int i=0; i<fields.size(); i++){
    		if (fields.get(i).getAnnotation(Column.class).name().equals(columnName)){
    			return i;
    		}
    	}
    	return -1;
    }
    
	private static Method getSetMethodByField(Field f, Class<?> c)
			throws NoSuchMethodException, SecurityException {
	
		String upperCaseFirstLetter = new Character(f.getName().charAt(0))
				.toString().toUpperCase();
		
		return c.getMethod("set" + upperCaseFirstLetter
				+ f.getName().substring(1), f.getType());
	}
	

	private static Method getGetMethodByField(Field f, Class<?> c)
			throws NoSuchMethodException, SecurityException {
	
		String upperCaseFirstLetter = new Character(f.getName().charAt(0))
				.toString().toUpperCase();
		
		return c.getMethod("get" + upperCaseFirstLetter
				+ f.getName().substring(1));
	}
}
