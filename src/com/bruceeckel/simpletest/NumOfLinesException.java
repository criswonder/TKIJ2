//: com:bruceeckel:simpletest:NumOfLinesException.java
package com.bruceeckel.simpletest;

public class NumOfLinesException extends SimpleTestException {
	public NumOfLinesException(int exp, int out) {
		super("Number of lines of output and "
				+ "expected output did not match.\n" + "expected: <" + exp
				+ ">\n" + "output: <" + out + "> lines)");
	}
} // /:~