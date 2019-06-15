package com.infosys.stg.doc.Utils;

import java.util.Set;

import org.springframework.util.StringUtils;

public class AppUtils {
	public static String convertToKey(String label, Set<String> allKeys) {
		if (StringUtils.isEmpty(label))
			return "";
		String key = label.replace(" ", "").replace("_", "");
		String finalKey = key;
		int counter = 0;
		while(allKeys.contains(finalKey)) {
			finalKey = key + (++counter);
		}
		allKeys.add(finalKey);
		return finalKey;
	}
}
