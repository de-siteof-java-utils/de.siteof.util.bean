/**
 * 
 */
package de.siteof.util.bean.filter;

import de.siteof.util.filter.NotObjectFilter;

/**
 * @author Daniel Ecer
 *
 */
public class ExcludeNamesBeanClassPropertyWrapperFilter extends NotObjectFilter {
	
	public ExcludeNamesBeanClassPropertyWrapperFilter(String[] names) {
		super(new IncludeNamesBeanClassPropertyWrapperFilter(names));
	}

}
