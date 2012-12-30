package de.siteof.util.bean;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import de.siteof.util.bean.filter.PreferredBeanClassPropertyWrapperFilter;
import de.siteof.util.filter.IObjectFilter;

/**
 * Abstract bean class wrapper. (implements partially IBeanClassWrapper)
 * 
 * @author Daniel Ecer
 */
public abstract class AbstractBeanClassWrapper implements IBeanClassWrapper {

	private Map	beanClassPropertyWrapperByIdMap		= new HashMap();
	private Map	beanClassPropertyWrapperByNameMap	= new HashMap();
	private IBeanClassPropertyWrapper[]	beanClassPropertyWrappers;

	protected void init(IBeanClassPropertyWrapper[] beanClassPropertyWrappers) {
		for(int i = 0; i < beanClassPropertyWrappers.length; i++) {
			IBeanClassPropertyWrapper beanClassPropertyWrapper	= beanClassPropertyWrappers[i];
			Object id	= beanClassPropertyWrapper.getId();
			String name	= beanClassPropertyWrapper.getName();
			if (!beanClassPropertyWrapperByIdMap.containsKey(id)) {
				beanClassPropertyWrapperByIdMap.put(id, beanClassPropertyWrapper);
			}
			if (!beanClassPropertyWrapperByNameMap.containsKey(name)) {
				beanClassPropertyWrapperByNameMap.put(name, beanClassPropertyWrapper);
			}
		}
		this.beanClassPropertyWrappers	= beanClassPropertyWrappers;
	}
	
	protected String[] getPropertyNames(IBeanClassPropertyWrapper[] beanClassPropertyWrappers) {
		String[] propertyNames	= new String[beanClassPropertyWrappers.length];
		for(int i = 0; i < propertyNames.length; i++) {
			propertyNames[i]	= beanClassPropertyWrappers[i].getName();
		}
		return propertyNames;
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassWrapper#getBeanClassPropertyWrapperById(java.lang.Object)
	 */
	public IBeanClassPropertyWrapper getBeanClassPropertyWrapperById(Object id) throws NoSuchFieldException {
		IBeanClassPropertyWrapper beanClassPropertyWrapper	=
			(IBeanClassPropertyWrapper) beanClassPropertyWrapperByIdMap.get(id);
		if (beanClassPropertyWrapper == null) {
			throw new NoSuchFieldException(id.toString());
		}
		return beanClassPropertyWrapper;
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassWrapper#getBeanClassPropertyWrapperByName(java.lang.String)
	 */
	public IBeanClassPropertyWrapper getBeanClassPropertyWrapperByName(String propertyName) throws NoSuchFieldException {
		IBeanClassPropertyWrapper beanClassPropertyWrapper	=
			(IBeanClassPropertyWrapper) beanClassPropertyWrapperByNameMap.get(propertyName);
		if (beanClassPropertyWrapper == null) {
			throw new NoSuchFieldException(propertyName);
		}
		return beanClassPropertyWrapper;
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassWrapper#setProperty(java.lang.Object, java.lang.String, java.lang.Object)
	 */
	public void setProperty(Object bean, String propertyName, Object value) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		getBeanClassPropertyWrapperByName(propertyName).setValue(bean, value);
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassWrapper#getProperty(java.lang.Object, java.lang.String)
	 */
	public Object getProperty(Object bean, String propertyName) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		return getBeanClassPropertyWrapperByName(propertyName).getValue(bean);
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassWrapper#getPropertyType(java.lang.String)
	 */
	public Class getPropertyType(String propertyName) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		return getBeanClassPropertyWrapperByName(propertyName).getType();
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassWrapper#getBeanClassPropertyWrappers()
	 */
	public IBeanClassPropertyWrapper[] getBeanClassPropertyWrappers() {
		return this.beanClassPropertyWrappers;
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassWrapper#getBeanPropertyWrappers(java.lang.Object)
	 */
	public IBeanPropertyWrapper[] getBeanPropertyWrappers(Object bean) {
		return getBeanPropertyWrappers(getBeanClassPropertyWrappers(), bean);
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassWrapper#getPropertyNames()
	 */
	public String[] getPropertyNames() {
		return getPropertyNames(getBeanClassPropertyWrappers());
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassWrapper#getBeanClassPropertyWrappers(de.siteof.util.IObjectFilter)
	 */
	public IBeanClassPropertyWrapper[] getBeanClassPropertyWrappers(IObjectFilter filter) {
		IBeanClassPropertyWrapper[] a = this.getBeanClassPropertyWrappers();
		if (filter != null) {
			Collection list = new ArrayList();
			for(int i = 0; i < a.length; i++) {
				if (filter.matches(a[i])) {
					list.add(a[i]);
				}
			}
			a	= (IBeanClassPropertyWrapper[]) list.toArray(new IBeanClassPropertyWrapper[0]);
		}
		return a;
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassWrapper#getBeanPropertyWrappers(de.siteof.util.bean.IBeanClassPropertyWrapper[], java.lang.Object)
	 */
	public IBeanPropertyWrapper[] getBeanPropertyWrappers(IBeanClassPropertyWrapper[] beanClassPropertyWrappers, Object bean) {
		IBeanPropertyWrapper[] a = new IBeanPropertyWrapper[beanClassPropertyWrappers.length];
		for(int i = 0; i < a.length; i++) {
			a[i]	= beanClassPropertyWrappers[i].getBeanPropertyWrapper(bean);
		}
		return a;
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassWrapper#getBeanPropertyWrappers(java.lang.Object, de.siteof.util.IObjectFilter)
	 */
	public IBeanPropertyWrapper[] getBeanPropertyWrappers(Object bean, IObjectFilter filter) {
		return getBeanPropertyWrappers(getBeanClassPropertyWrappers(filter), bean);
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassWrapper#getPropertyNames(de.siteof.util.IObjectFilter)
	 */
	public String[] getPropertyNames(IObjectFilter filter) {
		return getPropertyNames(getBeanClassPropertyWrappers(filter));
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassWrapper#getPreferredBeanClassPropertyWrappers()
	 */
	public IBeanClassPropertyWrapper[] getPreferredBeanClassPropertyWrappers() {
		return getBeanClassPropertyWrappers(PreferredBeanClassPropertyWrapperFilter.getInstance());
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassWrapper#getPreferredBeanPropertyWrappers(java.lang.Object)
	 */
	public IBeanPropertyWrapper[] getPreferredBeanPropertyWrappers(Object bean) {
		return getBeanPropertyWrappers(getPreferredBeanClassPropertyWrappers(), bean);
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassWrapper#getPreferredPropertyNames()
	 */
	public String[] getPreferredPropertyNames() {
		return getPropertyNames(getPreferredBeanClassPropertyWrappers());
	}


}
