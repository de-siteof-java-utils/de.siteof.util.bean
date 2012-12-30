package de.siteof.util.bean.filter;

import de.siteof.util.bean.IBeanPropertyWrapper;
import de.siteof.util.filter.IObjectFilter;

/**
 * Abstract object filter class which casts to IBeanPropertyWrapper.
 * 
 * @author Daniel Ecer
 *
 */
public abstract class AbstractBeanPropertyWrapperFilter implements IObjectFilter {

	/* (non-Javadoc)
	 * @see de.siteof.util.IObjectFilter#matches(java.lang.Object)
	 */
	public boolean matches(Object o) {
		return matches((IBeanPropertyWrapper) o);
	}
	
	protected abstract boolean matches(IBeanPropertyWrapper o);

}
