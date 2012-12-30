package de.siteof.util.bean;

import java.lang.reflect.InvocationTargetException;

/**
 * Wrapps a bean property.
 *
 * @author Daniel Ecer
 */
public class BeanPropertyWrapper implements IBeanPropertyWrapper {

	private IBeanClassPropertyWrapper beanClassPropertyWrapper;
	private Object bean;

	public BeanPropertyWrapper(IBeanClassPropertyWrapper beanClassPropertyWrapper, Object bean) {
		this.beanClassPropertyWrapper	= beanClassPropertyWrapper;
		this.bean						= bean;
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanPropertyWrapper#getBeanClassPropertyWrapper()
	 */
	public IBeanClassPropertyWrapper getBeanClassPropertyWrapper() {
		return beanClassPropertyWrapper;
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanPropertyWrapper#getId()
	 */
	public Object getId() {
		return beanClassPropertyWrapper.getId();
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanPropertyWrapper#getName()
	 */
	public String getName() {
		return beanClassPropertyWrapper.getName();
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanPropertyWrapper#getDescription()
	 */
	public String getDescription() {
		return beanClassPropertyWrapper.getDescription();
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanPropertyWrapper#getDisplayName()
	 */
	public String getDisplayName() {
		return beanClassPropertyWrapper.getDisplayName();
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanPropertyWrapper#setValue(java.lang.Object)
	 */
	public void setValue(Object value) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		beanClassPropertyWrapper.setValue(bean, value);
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanPropertyWrapper#getValue()
	 */
	public Object getValue() throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		return beanClassPropertyWrapper.getValue(bean);
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanPropertyWrapper#getValueAsString()
	 */
	public String getValueAsString() throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		return beanClassPropertyWrapper.getValueAsString(bean);
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanPropertyWrapper#getType()
	 */
	public Class getType() throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		return beanClassPropertyWrapper.getType();
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanPropertyWrapper#isStatic()
	 */
	public boolean isStatic() {
		return beanClassPropertyWrapper.isStatic();
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanPropertyWrapper#isPreferred()
	 */
	public boolean isPreferred() {
		return beanClassPropertyWrapper.isPreferred();
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanPropertyWrapper#isEditable()
	 */
	public boolean isEditable() {
		return beanClassPropertyWrapper.isEditable();
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.bean.IBeanPropertyWrapper#isHidden()
	 */
	public boolean isHidden() {
		return beanClassPropertyWrapper.isHidden();
	}

}
