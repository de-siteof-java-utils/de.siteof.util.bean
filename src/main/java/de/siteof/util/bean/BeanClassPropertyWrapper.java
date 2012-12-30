package de.siteof.util.bean;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * Bean class property wrapper. (implements IBeanClassPropertyWrapper)
 *
 * @author Daniel Ecer
 *
 */
public class BeanClassPropertyWrapper implements IBeanClassPropertyWrapper {

	private Class beanClass;
	private Class type;
	private Object id;
	private String name;
	private String displayName;
	private String description;
	private boolean staticProperty;
	private boolean preferred;
	private boolean hidden;
	private boolean expert;
	private boolean editable;
	private boolean nullAllowed	= true;
	private Map attributeMap	= new HashMap();
	private Method readMethod;
	private Method writeMethod;
	private Method writeNullMethod;
	private Object[] indexValues;
	private IBeanClassWrapper listElementType;

	public BeanClassPropertyWrapper(Class beanClass, Object id, Class type, Method readMethod, Method writeMethod) {
		init(beanClass, id, type, readMethod, writeMethod);
	}

	public BeanClassPropertyWrapper(Class beanClass, Object id, Class type, Method readMethod) {
		init(beanClass, id, type, readMethod, null);
	}

	public BeanClassPropertyWrapper(Class beanClass, PropertyDescriptor propertyDescriptor) {
		init(beanClass, propertyDescriptor.getName(), propertyDescriptor.getPropertyType(), propertyDescriptor.getReadMethod(), propertyDescriptor.getWriteMethod());
	}

	protected void init(Class beanClass, Object id, Class type, Method readMethod, Method writeMethod) {
		this.beanClass		= beanClass;
		this.id			= id;
		this.readMethod	= readMethod;
		this.writeMethod	= writeMethod;
		this.type			= type;
		this.name			= id.toString();
		this.displayName	= this.name;
		hidden				= (readMethod == null);
		editable			= (writeMethod != null);
		expert				= (((readMethod == null) && (writeMethod == null)) || (type == null));
		preferred			= (!hidden) && (editable) && (type != null);
		staticProperty		= (((readMethod != null) && (Modifier.isStatic(readMethod.getModifiers()))) ||
				((writeMethod != null) && (Modifier.isStatic(writeMethod.getModifiers()))));
		if (writeMethod != null) {
			try {
				Method writeNullMethod	= beanClass.getMethod("un" + writeMethod.getName(), new Class[0]);
				if (Modifier.isPublic(writeNullMethod.getModifiers())) {
					this.writeNullMethod	= writeNullMethod;
				}
			} catch (SecurityException e) {
			} catch (NoSuchMethodException e) {
				// ignore errors, we will use regular write method
			}
		}
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassPropertyWrapper#getType()
	 */
	public Class getType() {
		return type;
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassPropertyWrapper#getId()
	 */
	public Object getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassPropertyWrapper#getName()
	 */
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassPropertyWrapper#setName(java.lang.String)
	 */
	public void setName(String name) {
		this.name	= name;
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassPropertyWrapper#getDisplayName()
	 */
	public String getDisplayName() {
		return displayName;
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassPropertyWrapper#setDisplayName(java.lang.String)
	 */
	public void setDisplayName(String displayName) {
		this.displayName	= displayName;
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassPropertyWrapper#getDescription()
	 */
	public String getDescription() {
		return description;
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassPropertyWrapper#setDescription(java.lang.String)
	 */
	public void setDescription(String description) {
		this.description	= description;
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassPropertyWrapper#isPreferred()
	 */
	public boolean isPreferred() {
		return preferred;
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassPropertyWrapper#setPreferred(boolean)
	 */
	public void setPreferred(boolean preferred) {
		this.preferred	= preferred;
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassPropertyWrapper#isHidden()
	 */
	public boolean isHidden() {
		return hidden;
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassPropertyWrapper#setHidden(boolean)
	 */
	public void setHidden(boolean hidden) {
		this.hidden	= hidden;
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassPropertyWrapper#isExpert()
	 */
	public boolean isExpert() {
		return expert;
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassPropertyWrapper#setExpert(boolean)
	 */
	public void setExpert(boolean expert) {
		this.expert	= expert;
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassPropertyWrapper#isEditable()
	 */
	public boolean isEditable() {
		return editable;
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassPropertyWrapper#setEditable(boolean)
	 */
	public void setEditable(boolean editable) {
		this.editable	= editable;
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassPropertyWrapper#isNullAllowed()
	 */
	public boolean isNullAllowed() {
		return this.nullAllowed;
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassPropertyWrapper#setNullAllowed(boolean)
	 */
	public void setNullAllowed(boolean nullAllowed) {
		this.nullAllowed	= nullAllowed;
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassPropertyWrapper#setAttribute(java.lang.Object, java.lang.Object)
	 */
	public void setAttribute(Object key, Object value) {
		this.attributeMap.put(key, value);
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassPropertyWrapper#getAttribute(java.lang.Object)
	 */
	public Object getAttribute(Object key) {
		return this.attributeMap.get(key);
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassPropertyWrapper#getAttributes()
	 */
	public Map getAttributes() {
		return this.attributeMap;
	}


	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassPropertyWrapper#getIndexValues()
	 */
	public Object[] getIndexValues() {
		return indexValues;
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassPropertyWrapper#setIndexValues(java.lang.Object[])
	 */
	public void setIndexValues(Object[] indexValues) {
		this.indexValues	= indexValues;
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassPropertyWrapper#getListElementType()
	 */
	public IBeanClassWrapper getListElementType() {
		return listElementType;
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassPropertyWrapper#setListElementType(de.siteof.util.bean.IBeanClassWrapper)
	 */
	public void setListElementType(IBeanClassWrapper listElementType) {
		this.listElementType	= listElementType;
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassPropertyWrapper#getBeanPropertyWrapper(java.lang.Object)
	 */
	public IBeanPropertyWrapper getBeanPropertyWrapper(Object bean) {
		return new BeanPropertyWrapper(this, bean);
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassPropertyWrapper#setValue(java.lang.Object, java.lang.Object)
	 */
	public void setValue(Object bean, Object value)
			throws NoSuchFieldException, NoSuchMethodException,
			InvocationTargetException, IllegalAccessException {
		if (writeMethod == null) {
			throw new NoSuchMethodException("set " + name);
		}
		if ((value == null) && (writeNullMethod != null)) {
			writeNullMethod.invoke(bean, new Object[0]);
		} else {
			writeMethod.invoke(bean, new Object[] {value});
		}
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassPropertyWrapper#getValue(java.lang.Object)
	 */
	public Object getValue(Object bean) throws NoSuchFieldException,
			NoSuchMethodException, InvocationTargetException,
			IllegalAccessException {
		if (readMethod == null) {
			throw new NoSuchMethodException("get " + name);
		}
		return readMethod.invoke(bean, new Object[] {});
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassPropertyWrapper#getValueAsString(java.lang.Object)
	 */
	public String getValueAsString(Object bean) throws NoSuchFieldException,
			NoSuchMethodException, InvocationTargetException,
			IllegalAccessException {
		Object value = this.getValue(bean);
		if (value != null) {
			return value.toString();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassPropertyWrapper#isStatic()
	 */
	public boolean isStatic() {
		return this.staticProperty;
	}

	/*
	 * (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassPropertyWrapper#setStatic(boolean)
	 */
	public void setStatic(boolean staticProperty) {
		this.staticProperty	= staticProperty;
	}

}
