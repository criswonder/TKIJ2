package c11;

class VeryBig {
	private static final int SZ = 10000;
	private double[] d = new double[SZ];
	private String ident;

	public VeryBig(String id) {
		ident = id;
	}

	public String toString() {
		return ident;
	}

	public void finalize() {
		System.out.println("Finalizing " + ident);
	}
}