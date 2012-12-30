package de.siteof.util.bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Hashtable;

/**
 * Wrapps a bean class.
 * 
 * @author Daniel Ecer
 */
public class BeanClassWrapper extends AbstractBeanClassWrapper {
	
	private static Hashtable beanClassWrappersByClass = new Hashtable();
	
	private Class beanClass;

	public BeanClassWrapper(Class beanClass) throws IntrospectionException {
		this.beanClass	= beanClass;
		BeanInfo bi = Introspector.getBeanInfo(beanClass);
		PropertyDescriptor[] properties = bi.getPropertyDescriptors();
		IBeanClassPropertyWrapper[]	beanClassPropertyWrappers	= new IBeanClassPropertyWrapper[properties.length];
		for(int i = 0; i < properties.length; i++) {
			IBeanClassPropertyWrapper beanClassPropertyWrapper	=
				new BeanClassPropertyWrapper(beanClass, properties[i]);
			beanClassPropertyWrappers[i]	= beanClassPropertyWrapper;
		}
		this.init(beanClassPropertyWrappers);
	}
	
	public static BeanClassWrapper getInstance(Class beanClass) throws IntrospectionException {
		BeanClassWrapper beanClassWrapper = (BeanClassWrapper) beanClassWrappersByClass.get(beanClass);
		if (beanClassWrapper == null) {
			beanClassWrapper	= new BeanClassWrapper(beanClass);
			beanClassWrappersByClass.put(beanClass, beanClassWrapper);
		}
		return beanClassWrapper;
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassWrapper#getBeanClass()
	 */
	public Class getBeanClass() {
		return beanClass;
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassWrapper#getNewBean()
	 */
	public Object getNewBean() throws InstantiationException, IllegalAccessException {
		return beanClass.newInstance();
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanClassWrapper#getNewBeanWrapper()
	 */
	public IBeanWrapper getNewBeanWrapper() throws InstantiationException, IllegalAccessException {
		return new BeanWrapper(this, getNewBean());
	}

}
