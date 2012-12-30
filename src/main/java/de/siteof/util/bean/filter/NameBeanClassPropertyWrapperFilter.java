/**
 * 
 */
package de.siteof.util.bean.filter;

import de.siteof.util.bean.IBeanClassPropertyWrapper;
import de.siteof.util.filter.IObjectFilter;

/**
 * @author Daniel Ecer
 *
 */
public class NameBeanClassPropertyWrapperFilter extends AbstractBeanClassPropertyWrapperFilter {
	
	private IObjectFilter filter;
	
	public NameBeanClassPropertyWrapperFilter(IObjectFilter filter) {
		this.filter	= filter;
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.IObjectFilter#matches(de.siteof.util.bean.IBeanClassPropertyWrapper)
	 */
	public boolean matches(IBeanClassPropertyWrapper o) {
		return filter.matches(o.getName());
	}

}
