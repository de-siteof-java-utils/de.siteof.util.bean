package de.siteof.util.bean;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Merges the mutiple bean wrappers as if it were one.
 * 
 * @author Daniel Ecer
 *
 */
public class MergedBeanWrapper extends AbstractBeanWrapper {
	
	public MergedBeanWrapper(IBeanWrapper[] beanWrappers) {
		Collection beanPropertyWrappers	= new ArrayList();
		for(int i = 0; i < beanWrappers.length; i++) {
			IBeanWrapper beanWrapper	= beanWrappers[i];
			IBeanPropertyWrapper[] a	= beanWrapper.getBeanPropertyWrappers();
			for(int j = 0; j < a.length; j++) {
				beanPropertyWrappers.add(a[j]);
			}
		}
		init((IBeanPropertyWrapper[]) beanPropertyWrappers.toArray(new IBeanPropertyWrapper[0]));
	}
	
	public MergedBeanWrapper(IBeanPropertyWrapper[] beanPropertyWrappers) {
		init(beanPropertyWrappers);
	}

}
