
public class Program1_2 {
	
	public static final double x = 0.1; 
	public static final int series = 90000;
	
	public static void main(String[] args) throws InterruptedException {
		
		long begin_time = System.nanoTime();
		
		Taylorseries1 thread1 = new Taylorseries1("Thread 1");
		Taylorseries2 thread2 = new Taylorseries2("Thread 2");
		
		thread1.start();
		thread2.start();
		
		thread1.join();
		thread2.join();
		
		long end_time = System.nanoTime();
		double cost_time = (end_time - begin_time)/(1000000000.0000);

		System.out.printf("Time used for computing -> "+ series +" Taylor series of sin & cos functions = "+ cost_time + " (secs)");
	
	}

}


class Taylorseries1 extends Thread{
	
	private String tid;
	
	public Taylorseries1(String thread_id) {
		this.tid = thread_id;
	}

	public static double Sin(double x, int n) {
		double sin_approx = 0;
		for (int i=0; i<n; i++) {
			sin_approx += Math.pow(-1, i) * Math.pow(x, 2*i+1) * Invfactorial(2*i+1);
		}
		return sin_approx;
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
		this.Sin(Program1_2.x, Program1_2.series);	
	}
	
}

class Taylorseries2 extends Thread{
	
	private String tid;
	
	public Taylorseries2(String thread_id) {
		this.tid = thread_id;
	}
	
	public static void Cos(double x, int n) {
		double cos_approx = 0;
		for (int i=0; i<n; i++) {
			cos_approx += Math.pow(-1, i) * Math.pow(x, 2*i) * Invfactorial(2*i);
		}
		System.out.println("cos(" + x + ") ~= " + cos_approx);
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
		this.Cos(Program1_2.x, Program1_2.series);	
	}
	
}

