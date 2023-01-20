package com.phonesystem.unitls;

import static org.junit.Assert.*;

import org.junit.Test;

public class MD5Test {

	@Test
	public void test() {
		String string=MD5.getMD5("1111111");
		System.out.println(string);
	}

}
