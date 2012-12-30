/**
 * 
 */
package de.siteof.util.bean.filter;

import de.siteof.util.bean.IBeanClassPropertyWrapper;

/**
 * @author Daniel Ecer
 *
 */
public class PreferredBeanClassPropertyWrapperFilter extends AbstractBeanClassPropertyWrapperFilter {
	
	private static PreferredBeanClassPropertyWrapperFilter instance	= new PreferredBeanClassPropertyWrapperFilter();

	/* (non-Javadoc)
	 * @see de.siteof.util.IObjectFilter#matches(de.siteof.util.bean.IBeanClassPropertyWrapper)
	 */
	public boolean matches(IBeanClassPropertyWrapper o) {
		return o.isPreferred();
	}
	
	public static PreferredBeanClassPropertyWrapperFilter getInstance() {
		return instance;
	}

}
