package de.siteof.util.bean;

import java.beans.IntrospectionException;

/**
 * Wrapps a bean instance.
 * 
 * @author Daniel Ecer
 */
public class BeanWrapper extends AbstractBeanWrapper {
	
	private IBeanClassWrapper beanClassWrapper;
	private Object bean;
	private IBeanPropertyWrapper[] beanPropertyWrappers;
	
	public BeanWrapper(IBeanClassWrapper beanClassWrapper, Object bean) {
		this.beanClassWrapper	= beanClassWrapper;
		this.bean				= bean;
		this.init(beanClassWrapper.getBeanPropertyWrappers(bean));
	}
	
	public BeanWrapper(Object bean) throws IntrospectionException {
		this(BeanClassWrapper.getInstance(bean.getClass()), bean);
	}

}
