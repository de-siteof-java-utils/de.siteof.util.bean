package de.siteof.util.bean;

import java.lang.reflect.InvocationTargetException;

/**
 * Defines an interface to access a bean property.
 *
 * @author Daniel Ecer
 *
 */
public interface IBeanPropertyWrapper {

	IBeanClassPropertyWrapper getBeanClassPropertyWrapper();

	Object getId();

	String getName();

	String getDisplayName();

	String getDescription();

	void setValue(Object value)
			throws NoSuchFieldException, NoSuchMethodException,
			InvocationTargetException, IllegalAccessException;

	Object getValue() throws NoSuchFieldException,
			NoSuchMethodException, InvocationTargetException,
			IllegalAccessException;

	String getValueAsString() throws NoSuchFieldException,
			NoSuchMethodException, InvocationTargetException,
			IllegalAccessException;

	Class getType()
			throws NoSuchFieldException, NoSuchMethodException,
			InvocationTargetException, IllegalAccessException;

	boolean isStatic();

	boolean isPreferred();

	boolean isHidden();

	boolean isEditable();

}
