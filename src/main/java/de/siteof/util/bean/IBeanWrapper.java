package de.siteof.util.bean;

import java.lang.reflect.InvocationTargetException;

import de.siteof.util.filter.IObjectFilter;

/**
 * Defines an interface to access a bean instance.
 * 
 * @author Daniel Ecer
 *
 */
public interface IBeanWrapper {

	IBeanPropertyWrapper getBeanPropertyWrapperById(Object id) throws NoSuchFieldException;
	
	IBeanPropertyWrapper getBeanPropertyWrapperByName(String propertyName) throws NoSuchFieldException;

	void setProperty(String propertyName, Object value)
			throws NoSuchFieldException, NoSuchMethodException,
					InvocationTargetException, IllegalAccessException;
	
	Object getProperty(String propertyName)
			throws NoSuchFieldException, NoSuchMethodException,
					InvocationTargetException, IllegalAccessException;

	String getPropertyAsString(String propertyName)
			throws NoSuchFieldException, NoSuchMethodException,
					InvocationTargetException, IllegalAccessException;

	Class getPropertyType(String propertyName)
			throws NoSuchFieldException, NoSuchMethodException,
					InvocationTargetException, IllegalAccessException;

	IBeanPropertyWrapper[] getBeanPropertyWrappers(IObjectFilter filter);
	
	IBeanPropertyWrapper[] getBeanPropertyWrappers();

	IBeanPropertyWrapper[] getPreferredBeanPropertyWrappers();

	String[] getPropertyNames();

	String[] getPreferredPropertyNames();

}
