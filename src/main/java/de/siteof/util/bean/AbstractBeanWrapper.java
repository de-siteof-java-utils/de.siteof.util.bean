package de.siteof.util.bean;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import de.siteof.util.bean.filter.PreferredBeanPropertyWrapperFilter;
import de.siteof.util.filter.IObjectFilter;

/**
 * Abstract bean wrapper class. (Implements partially IBeanWrapper)
 * 
 * @author Daniel Ecer
 */
public abstract class AbstractBeanWrapper implements IBeanWrapper {

	private Map beanPropertyWrapperByNameMap	= new HashMap();
	private Map beanPropertyWrapperByIdMap		= new HashMap();
	private IBeanPropertyWrapper[] beanPropertyWrappers;

	
	protected void init(IBeanPropertyWrapper[] beanPropertyWrappers) {
		Collection propertyNames	= new ArrayList();
		for(int i = 0; i < beanPropertyWrappers.length; i++) {
			IBeanPropertyWrapper beanPropertyWrapper	= beanPropertyWrappers[i];
			Object id	= beanPropertyWrapper.getId();
			String name	= beanPropertyWrapper.getName();
			if (!beanPropertyWrapperByIdMap.containsKey(id)) {
				beanPropertyWrapperByIdMap.put(id, beanPropertyWrapper);
				if (!beanPropertyWrapperByNameMap.containsKey(name)) {
					beanPropertyWrapperByNameMap.put(name, beanPropertyWrapper);
					propertyNames.add(name);
				}
				propertyNames.add(name);
			}
		}
		this.beanPropertyWrappers	= beanPropertyWrappers;
	}

	
	protected String[] getPropertyNames(IBeanPropertyWrapper[] beanPropertyWrappers) {
		String[] propertyNames	= new String[beanPropertyWrappers.length];
		for(int i = 0; i < propertyNames.length; i++) {
			propertyNames[i]	= beanPropertyWrappers[i].getName();
		}
		return propertyNames;
	}

	
	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanWrapper#getBeanPropertyWrapperById(java.lang.Object)
	 */
	public IBeanPropertyWrapper getBeanPropertyWrapperById(Object id) throws NoSuchFieldException {
		IBeanPropertyWrapper beanPropertyWrapper	= (IBeanPropertyWrapper) beanPropertyWrapperByIdMap.get(id);
		if (beanPropertyWrapper == null) {
			throw new NoSuchFieldException(id.toString());
		}
		return beanPropertyWrapper;
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanWrapper#getBeanPropertyWrapperByName(java.lang.String)
	 */
	public IBeanPropertyWrapper getBeanPropertyWrapperByName(String propertyName) throws NoSuchFieldException {
		IBeanPropertyWrapper beanPropertyWrapper	= (IBeanPropertyWrapper) beanPropertyWrapperByNameMap.get(propertyName);
		if (beanPropertyWrapper == null) {
			throw new NoSuchFieldException(propertyName);
		}
		return beanPropertyWrapper;
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanWrapper#setProperty(java.lang.String, java.lang.Object)
	 */
	public void setProperty(String propertyName, Object value)
			throws NoSuchFieldException, NoSuchMethodException,
			InvocationTargetException, IllegalAccessException {
		getBeanPropertyWrapperByName(propertyName).setValue(value);
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanWrapper#getProperty(java.lang.String)
	 */
	public Object getProperty(String propertyName) throws NoSuchFieldException,
			NoSuchMethodException, InvocationTargetException,
			IllegalAccessException {
		return getBeanPropertyWrapperByName(propertyName).getValue();
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanWrapper#getPropertyAsString(java.lang.String)
	 */
	public String getPropertyAsString(String propertyName)
			throws NoSuchFieldException, NoSuchMethodException,
			InvocationTargetException, IllegalAccessException {
		return getBeanPropertyWrapperByName(propertyName).getValueAsString();
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanWrapper#getPropertyType(java.lang.String)
	 */
	public Class getPropertyType(String propertyName)
			throws NoSuchFieldException, NoSuchMethodException,
			InvocationTargetException, IllegalAccessException {
		return getBeanPropertyWrapperByName(propertyName).getType();
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanWrapper#getBeanPropertyWrappers(de.siteof.util.IObjectFilter)
	 */
	public IBeanPropertyWrapper[] getBeanPropertyWrappers(IObjectFilter filter) {
		IBeanPropertyWrapper[] a = this.getBeanPropertyWrappers();
		if (filter != null) {
			Collection list = new ArrayList();
			for(int i = 0; i < a.length; i++) {
				if (filter.matches(a[i])) {
					list.add(a[i]);
				}
			}
			a	= (IBeanPropertyWrapper[]) list.toArray(new IBeanPropertyWrapper[0]);
		}
		return a;
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanWrapper#getBeanPropertyWrappers()
	 */
	public IBeanPropertyWrapper[] getBeanPropertyWrappers() {
		return this.beanPropertyWrappers;
	}


	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanWrapper#getPreferredBeanPropertyWrappers()
	 */
	public IBeanPropertyWrapper[] getPreferredBeanPropertyWrappers() {
		return getBeanPropertyWrappers(PreferredBeanPropertyWrapperFilter.getInstance());
	}


	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanWrapper#getPropertyNames()
	 */
	public String[] getPropertyNames() {
		return getPropertyNames(getBeanPropertyWrappers());
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanWrapper#getPreferredPropertyNames()
	 */
	public String[] getPreferredPropertyNames() {
		return getPropertyNames(getBeanPropertyWrappers());
	}

}
