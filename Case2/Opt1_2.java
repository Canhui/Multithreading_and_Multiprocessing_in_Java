
public class Opt1_2 {
	
	public static final double x = 0.1;
	public static final int series0 = 0;
	public static final int series1 = 29999;
	public static final int series2 = 30000;
	public static final int series3 = 59999;
	public static final int series4 = 60000;
	public static final int series5 = 60001;
	public static final int series6 = 90000;
	
	public static void main(String[] args) throws InterruptedException {
		
		long begin_time = System.nanoTime();
		
		// Thread 1, 2, 3, 4, 5, 6
		Taylorseries1 th1 = new Taylorseries1("Thread 1", series0, series1);
		Taylorseries1 th2 = new Taylorseries1("Thread 2", series2, series3);
		Taylorseries1 th3 = new Taylorseries1("Thread 3", series4, series5);
		Taylorseries2 th4 = new Taylorseries2("Thread 4", series0, series1);
		Taylorseries2 th5 = new Taylorseries2("Thread 5", series2, series3);
		Taylorseries2 th6 = new Taylorseries2("Thread 6", series4, series5);
		
		Thread t1 = new Thread(th1);
		Thread t2 = new Thread(th2);
		Thread t3 = new Thread(th3);
		Thread t4 = new Thread(th4);
		Thread t5 = new Thread(th5);
		Thread t6 = new Thread(th6);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		t5.join();
		t6.join();
		
		long end_time = System.nanoTime();
		double cost_time = (end_time - begin_time)/(1000000000.0000);
		
		double sin_summary = 0.0;
		double cos_summary = 0.0;
		sin_summary += th1.sin_approx + th2.sin_approx + th3.sin_approx;
		cos_summary += th4.cos_approx + th5.cos_approx + th6.cos_approx;
		System.out.println("sin(" + x + ") ~= " + sin_summary);
		System.out.println("cos(" + x + ") ~= " + cos_summary);
		
		System.out.printf("Time used for computing -> "+ 90000 +" Taylor series of sin & cos functions = "+ cost_time + " (secs)");
	}

}


class Taylorseries1 implements Runnable{
	
	private String tid;
	private int series_start;
	private int series_end;
	public double sin_approx;
	
	public Taylorseries1(String thread_id, int series_start, int series_end) {
		this.tid = thread_id;
		this.series_start = series_start;
		this.series_end = series_end;
	}

	public void Sin(double x) {
		double sin_approx = 0;
		for (int i=this.series_start; i<this.series_end; i++) {
			sin_approx += Math.pow(-1, i) * Math.pow(x, 2*i+1) * Invfactorial(2*i+1);
		}
		this.sin_approx = sin_approx;
	}
	
	public static double Invfactorial(int n) {
		double inv_factorial = 1.0;
		if (n == 0) {
			return inv_factorial;
		}
		else {
			for (int i=1; i<=n; i++) {
				inv_factorial *= 1.0/i;
			}
		}	
		return inv_factorial;
	}
	
	@Override
	public void run() {
		this.Sin(Opt1_2.x);	
	}
	
}

class Taylorseries2 implements Runnable{
	
	private String tid;
	private int series_start;
	private int series_end;
	public double cos_approx;
	
	public Taylorseries2(String thread_id, int series_start, int series_end) {
		this.tid = thread_id;
		this.series_start = series_start;
		this.series_end = series_end;
	}

	public void Cos(double x) {
		double cos_approx = 0;
		for (int i=this.series_start; i<this.series_end; i++) {
			cos_approx += Math.pow(-1, i) * Math.pow(x, 2*i) * Invfactorial(2*i);
		}
		this.cos_approx = cos_approx;
	}
	
	public static double Invfactorial(int n) {
		double inv_factorial = 1.0;
		if (n == 0) {
			return inv_factorial;
		}
		else {
			for (int i=1; i<=n; i++) {
				inv_factorial *= 1.0/i;
			}
		}	
		return inv_factorial;
	}
	
	@Override
	public void run() {
		this.Cos(Opt1_2.x);	
	}
	
}
