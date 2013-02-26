//: com:bruceeckel:simpletest:LineMismatchException.java
package com.bruceeckel.simpletest;

import java.io.PrintStream;

public class LineMismatchException extends SimpleTestException {
	public LineMismatchException(int lineNum, String expected, String output) {
		super("line " + lineNum + " of output did not match expected output\n"
				+ "expected: <" + expected + ">\n" + "output: <" + output + ">");
	}
} // /:~