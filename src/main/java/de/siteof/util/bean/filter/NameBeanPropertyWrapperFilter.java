/**
 * 
 */
package de.siteof.util.bean.filter;

import de.siteof.util.bean.IBeanPropertyWrapper;
import de.siteof.util.filter.IObjectFilter;

/**
 * @author Daniel Ecer
 *
 */
public class NameBeanPropertyWrapperFilter extends AbstractBeanPropertyWrapperFilter {
	
	private IObjectFilter filter;
	
	public NameBeanPropertyWrapperFilter(IObjectFilter filter) {
		this.filter	= filter;
	}

	/* (non-Javadoc)
	 * @see de.siteof.util.IObjectFilter#matches(de.siteof.util.bean.IBeanPropertyWrapper)
	 */
	public boolean matches(IBeanPropertyWrapper o) {
		return filter.matches(o.getName());
	}

}
