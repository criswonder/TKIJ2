package c11;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class References {
	private static ReferenceQueue rq = new ReferenceQueue();

	public static void checkQueue() {
		Object inq = rq.poll();
		if (inq != null)
			System.out
					.println("In queue: " + (VeryBig) ((Reference) inq).get());
	}

	public static void main(String[] args) {
		int size = 10;
		// Or, choose size via the command line:
		if (args.length > 0)
			size = Integer.parseInt(args[0]);
		SoftReference[] sa = new SoftReference[size];
		for (int i = 0; i < sa.length; i++) {
			sa[i] = new SoftReference(new VeryBig("Soft " + i), rq);
			System.out.println("Just created: " + (VeryBig) sa[i].get());
			checkQueue();
		}
		WeakReference[] wa = new WeakReference[size];
		for (int i = 0; i < wa.length; i++) {
			wa[i] = new WeakReference(new VeryBig("Weak " + i), rq);
			System.out.println("Just created: " + (VeryBig) wa[i].get());
			checkQueue();
		}
		SoftReference s = new SoftReference(new VeryBig("Soft"));
		WeakReference w = new WeakReference(new VeryBig("Weak"));
		System.gc();
		PhantomReference[] pa = new PhantomReference[size];
		for (int i = 0; i < pa.length; i++) {
			pa[i] = new PhantomReference(new VeryBig("Phantom " + i), rq);
			System.out.println("Just created: " + pa[i]);
			System.out.println("Just created: " + (VeryBig) pa[i].get());
			checkQueue();
		}
	}
} // /:~