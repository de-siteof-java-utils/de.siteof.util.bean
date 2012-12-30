/**
 * 
 */
package de.siteof.util.bean.filter;

import de.siteof.util.filter.EqualsAnyObjectFilter;

/**
 * @author Daniel Ecer
 *
 */
public class IncludeNamesBeanClassPropertyWrapperFilter extends NameBeanClassPropertyWrapperFilter {
	
	public IncludeNamesBeanClassPropertyWrapperFilter(String[] names) {
		super(new EqualsAnyObjectFilter(names));
	}

}
