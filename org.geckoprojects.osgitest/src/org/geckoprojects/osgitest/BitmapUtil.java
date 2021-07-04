package org.geckoprojects.osgitest;

public class BitmapUtil {
	public static boolean typeMatchesMask(int type, int mask) {
		return (type & mask) != 0;
	}
}
