package me.im97penis.shitnoob.util;

import java.util.Map;
import java.util.Map.Entry;

public class MapUtil {

//	public static <K, V> Long getLargestValue(Map<K, List<EntryConstruct<Long, V>>> construct) {
//		Long largestKey = 0L;
//
//		for(Entry<K, List<EntryConstruct<Long, V>>> entry : construct.entrySet()) {
//			for(EntryConstruct<Long, V> entryConstruct : entry.getValue()) {
//				if(entryConstruct.getKey() > largestKey)
//					largestKey = entryConstruct.getKey();
//			}
//		}
//		
//		return largestKey;
//	}
//	
//	public static <K, V> Long getLowestValue(Map<K, List<EntryConstruct<Long, V>>> construct) {
//		Long lowestKey = getLargestValue(construct);
//
//		for(Entry<K, List<EntryConstruct<Long, V>>> entry : construct.entrySet()) {
//			for(EntryConstruct<Long, V> entryConstruct : entry.getValue()) {
//				if(entryConstruct.getKey() < lowestKey)
//					lowestKey = entryConstruct.getKey();
//			}
//		}
//		
//		return lowestKey;
//	}

	public static <V> Long getLargestValue(Map<Long, V> map) {
		Long largestKey = 0L;

		for (Entry<Long, V> entry : map.entrySet()) {
			if (entry.getKey() > largestKey)
				largestKey = entry.getKey();
		}

		return largestKey;
	}

	public static <V> Long getLowestValue(Map<Long, V> map) {
		Long lowestKey = getLargestValue(map);

		for (Entry<Long, V> entry : map.entrySet()) {
			if (entry.getKey() < lowestKey)
				lowestKey = entry.getKey();
		}

		return lowestKey;
	}

}
