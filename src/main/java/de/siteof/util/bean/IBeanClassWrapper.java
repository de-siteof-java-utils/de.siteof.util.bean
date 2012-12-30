package de.siteof.util.bean;

import java.lang.reflect.InvocationTargetException;

import de.siteof.util.filter.IObjectFilter;

/**
 * Defines an interface to access a bean class.
 * 
 * @author Daniel Ecer
 *
 */
public interface IBeanClassWrapper {

	IBeanClassPropertyWrapper getBeanClassPropertyWrapperById(Object id) throws NoSuchFieldException;
	
	IBeanClassPropertyWrapper getBeanClassPropertyWrapperByName(String propertyName) throws NoSuchFieldException;

	void setProperty(Object bean, String propertyName, Object value)
		throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException;

	Object getProperty(Object bean, String propertyName)
		throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException ;

	Class getPropertyType(String propertyName)
		throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException;

	IBeanClassPropertyWrapper[] getBeanClassPropertyWrappers();
	
	IBeanPropertyWrapper[] getBeanPropertyWrappers(Object bean);
	
	String[] getPropertyNames();

	IBeanClassPropertyWrapper[] getBeanClassPropertyWrappers(IObjectFilter filter);
	
	IBeanPropertyWrapper[] getBeanPropertyWrappers(IBeanClassPropertyWrapper[] beanClassPropertyWrappers, Object bean);
	
	IBeanPropertyWrapper[] getBeanPropertyWrappers(Object bean, IObjectFilter filter);
	
	String[] getPropertyNames(IObjectFilter filter);

	IBeanClassPropertyWrapper[] getPreferredBeanClassPropertyWrappers();
	
	IBeanPropertyWrapper[] getPreferredBeanPropertyWrappers(Object bean);
	
	String[] getPreferredPropertyNames();
	
	Class getBeanClass();

	Object getNewBean() throws InstantiationException, IllegalAccessException;

	IBeanWrapper getNewBeanWrapper() throws InstantiationException, IllegalAccessException;
}
