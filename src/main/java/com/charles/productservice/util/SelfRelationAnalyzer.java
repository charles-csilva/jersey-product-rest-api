package com.charles.productservice.util;

import java.util.HashSet;
import java.util.Set;

public class SelfRelationAnalyzer {

	public static boolean findCircularReference(ISelfRelationable o, int maxDepth){
		
		ISelfRelationable currObject = o;
		ISelfRelationable currentParent = o.getParentRelated();
		Set<Comparable<?>> elementsKeys = new HashSet<Comparable<?>>();
		elementsKeys.add(o.getRelationKey());
		
		int i = 0;
		while(i < maxDepth){
			
			if(currentParent == null)
				return false;
			
			if(elementsKeys.contains(currObject.getRelationKey()))
				return true;
			
			currObject = currentParent;
			currentParent = currObject.getParentRelated();
			++i;
		}
		
		return true;
	}
}
