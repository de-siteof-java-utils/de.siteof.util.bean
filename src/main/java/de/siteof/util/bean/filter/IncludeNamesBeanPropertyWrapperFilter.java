/**
 * 
 */
package de.siteof.util.bean.filter;

import de.siteof.util.filter.EqualsAnyObjectFilter;

/**
 * @author Daniel Ecer
 *
 */
public class IncludeNamesBeanPropertyWrapperFilter extends NameBeanPropertyWrapperFilter {
	
	public IncludeNamesBeanPropertyWrapperFilter(String[] names) {
		super(new EqualsAnyObjectFilter(names));
	}

}
