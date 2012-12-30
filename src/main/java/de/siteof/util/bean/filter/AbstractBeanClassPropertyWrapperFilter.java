package de.siteof.util.bean.filter;

import de.siteof.util.bean.IBeanClassPropertyWrapper;
import de.siteof.util.filter.IObjectFilter;

/**
 * Abstract object filter which does a class cast to IBeanClassPropertyWrapper.
 * 
 * @author Daniel Ecer
 *
 */
public abstract class AbstractBeanClassPropertyWrapperFilter implements IObjectFilter {

	/* (non-Javadoc)
	 * @see de.siteof.util.IObjectFilter#matches(java.lang.Object)
	 */
	public boolean matches(Object o) {
		return matches((IBeanClassPropertyWrapper) o);
	}
	
	protected abstract boolean matches(IBeanClassPropertyWrapper o);

}
