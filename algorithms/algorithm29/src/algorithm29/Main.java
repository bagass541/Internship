package algorithm29;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Main {

	public static void main(String[] args) {
		System.out.println(orderWeight("103 123 4444 99 2000"));
		System.out.println(orderWeight("2000 10003 1234000 44444444 9999 11 11 22 123"));
		System.out.println(orderWeight(""));
	}

//	public static String orderWeight(String strng) {
//		Map<Integer, Integer> map = new TreeMap<>();
//		String[] strs = strng.split("\\s+");
//
//		if (strs.length == 0)
//			return "";
//
//		for (String string : strs) {
//			Integer i = Integer.parseInt(string);
//			map.put(i, sumDigits(i));
//		}
//
//		return map.entrySet().stream().sorted(Map.Entry.comparingByValue()).map(i -> String.valueOf(i.getKey())).reduce((s1, s2) -> s1 + " " + s2).get();
//	}
	
	public static String orderWeight(String strng) {
		
		List<Entry<Long, Long>> list = new ArrayList<>();
		String[] strs = strng.split("\\s+");
		
		strs = Arrays.stream(strs).sorted().toArray(String[]::new);
		
		if (strs.length == 0 || strs[0] == "")
			return "";

		for (String string : strs) {
			Long i = Long.parseLong(string);
			list.add(Map.entry(i, sumDigits(i)));
		}

		return list.stream().sorted(Map.Entry.comparingByValue()).map(i -> String.valueOf(i.getKey())).reduce((s1, s2) -> s1 + " " + s2).get();
	}

	private static Long sumDigits(Long i) {
		Long sum = 0l;
		while (i > 0) {
			sum += i % 10;
			i /= 10;
		}

		return sum;
	}
}
