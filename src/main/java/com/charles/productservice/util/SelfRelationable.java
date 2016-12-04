package com.charles.productservice.util;

public interface SelfRelationable {
	
	public Comparable<?> getRelationKey();
	
	public SelfRelationable getParentRelated();

}
