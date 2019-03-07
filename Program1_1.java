public class Program1_1 {

	public static void main(String[] args) {
		
		long begin_time = System.nanoTime();
		
		Taylorseries1 t1 = new Taylorseries1();
		Taylorseries2 t2 = new Taylorseries2();
		double x = 0.1; int series = 90000;
		double sin_taylorseries = t1.Sin(x, series);
		double cos_taylorseries = t2.Cos(x, series);
		
		long end_time = System.nanoTime();
		double cost_time = (end_time - begin_time)/(1000000000.0000);

		System.out.println("sin(" + x + ") ~= " + sin_taylorseries);
		System.out.println("cos(" + x + ") ~= " + cos_taylorseries);
		System.out.printf("Time used for computing -> "+ series +" Taylor series of sin & cos functions = "+ cost_time + " (secs)");
	}

}

class Taylorseries1 {
	
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
	
}

class Taylorseries2 {
	
	public static double Cos(double x, int n) {
		double cos_approx = 0;
		for (int i=0; i<n; i++) {
			cos_approx += Math.pow(-1, i) * Math.pow(x, 2*i) * Invfactorial(2*i);
		}
		return cos_approx;
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
	
}
