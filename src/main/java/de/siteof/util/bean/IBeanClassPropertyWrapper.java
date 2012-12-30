package de.siteof.util.bean;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Defines an interface to access interface to access a property of a bean class.
 *
 * @author Daniel Ecer
 *
 */
public interface IBeanClassPropertyWrapper {

	/* bean-class */

	Class getType();

	Object getId();

	String getName();

	void setName(String name);

	String getDisplayName();

	public void setDisplayName(String displayName);

	String getDescription();

	void setDescription(String description);

	boolean isStatic();

	void setStatic(boolean staticProperty);

	boolean isPreferred();

	void setPreferred(boolean preferred);

	boolean isHidden();

	void setHidden(boolean hidden);

	boolean isExpert();

	void setExpert(boolean expert);

	boolean isEditable();

	void setEditable(boolean editable);

	boolean isNullAllowed();

	void setNullAllowed(boolean nullAllowed);

	void setAttribute(Object key, Object value);

	Object getAttribute(Object key);

	Map getAttributes();

	Object[] getIndexValues();

	void setIndexValues(Object[] indexValues);

	IBeanClassWrapper getListElementType();

	void setListElementType(IBeanClassWrapper listElementType);


	/* bean-object */

	IBeanPropertyWrapper getBeanPropertyWrapper(Object bean);

	void setValue(Object bean, Object value)
			throws NoSuchFieldException, NoSuchMethodException,
			InvocationTargetException, IllegalAccessException;

	public Object getValue(Object bean) throws NoSuchFieldException,
			NoSuchMethodException, InvocationTargetException,
			IllegalAccessException;

	public String getValueAsString(Object bean) throws NoSuchFieldException,
			NoSuchMethodException, InvocationTargetException,
			IllegalAccessException;

}
