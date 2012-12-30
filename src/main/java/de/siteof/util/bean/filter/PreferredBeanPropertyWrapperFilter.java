/**
 * 
 */
package de.siteof.util.bean.filter;

import de.siteof.util.bean.IBeanPropertyWrapper;

public class PreferredBeanPropertyWrapperFilter extends AbstractBeanPropertyWrapperFilter {
	
	private static PreferredBeanPropertyWrapperFilter instance	= new PreferredBeanPropertyWrapperFilter();

	/* (non-Javadoc)
	 * @see de.siteof.util.IObjectFilter#matches(de.siteof.util.bean.IBeanPropertyWrapper)
	 */
	public boolean matches(IBeanPropertyWrapper o) {
		return o.isPreferred();
	}
	
	public static PreferredBeanPropertyWrapperFilter getInstance() {
		return instance;
	}

}
