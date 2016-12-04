package com.charles.productservice.util;

public interface ISelfRelationable {
	
	public Comparable<?> getRelationKey();
	
	public ISelfRelationable getParentRelated();

}
