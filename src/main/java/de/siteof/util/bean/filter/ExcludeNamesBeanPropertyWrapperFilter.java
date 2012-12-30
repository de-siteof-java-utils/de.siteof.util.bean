/**
 * 
 */
package de.siteof.util.bean.filter;

import de.siteof.util.filter.NotObjectFilter;

/**
 * @author Daniel Ecer
 *
 */
public class ExcludeNamesBeanPropertyWrapperFilter extends NotObjectFilter {
	
	public ExcludeNamesBeanPropertyWrapperFilter(String[] names) {
		super(new IncludeNamesBeanPropertyWrapperFilter(names));
	}

}
