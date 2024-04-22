package algorithm23;

import java.util.HexFormat;

public class Main {

	public static void main(String[] args) {
		System.out.println(rgb(255, 255, 255));
		System.out.println(rgb(255, 255, 300));
		System.out.println(rgb(148, 0, 211));
		System.out.println(rgb(1, 2, 3));
		System.out.println(rgb(-20, 275, 125));
	}

	public static String rgb(int r, int g, int b) {
		HexFormat hexFormat = HexFormat.of();
		
		r = Math.max(0, Math.min(r, 255));
		g = Math.max(0, Math.min(g, 255));
		b = Math.max(0, Math.min(b, 255));
		
		return (hexFormat.toHexDigits(r, 2) + hexFormat.toHexDigits(g, 2) + hexFormat.toHexDigits(b, 2)).toUpperCase();
    }
}
