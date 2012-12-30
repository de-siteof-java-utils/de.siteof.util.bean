package de.siteof.util.bean;

/**
 * Returns an empty bean wrapper which may be used like any other bean wrapper.
 * 
 * @author Daniel Ecer
 *
 */
public class EmptyBeanWrapper extends MergedBeanWrapper {
	
	private static EmptyBeanWrapper emptyBeanWrapperInstance;

	public EmptyBeanWrapper() {
		super(new IBeanPropertyWrapper[0]);
	}
	
	public static EmptyBeanWrapper getEmptyBeanWrapperInstance() {
		if (emptyBeanWrapperInstance != null) {
			emptyBeanWrapperInstance	= new EmptyBeanWrapper();
		}
		return emptyBeanWrapperInstance;
	}

}
