package com.charles.productservice.util;

import java.util.HashSet;
import java.util.Set;

public class SelfRelationAnalyzer {

	public static boolean findCircularReference(SelfRelationable o, int maxDepth){
		
		SelfRelationable currObject = o;
		SelfRelationable currentParent = o.getParentRelated();
		Set<Comparable<?>> elementsKeys = new HashSet<Comparable<?>>();
		//elementsKeys.add(o.getRelationKey());
		
		int i = 0;
		while(i <= maxDepth){
			System.out.println("i: " + i);
			if(currentParent == null)
				return false;
			
			if(elementsKeys.contains(currObject.getRelationKey()))
				return true;
			
			currObject = currentParent;
			currentParent = currObject.getParentRelated();
			elementsKeys.add(currObject.getRelationKey());
			++i;
		}
		
		return true;
	}
}
