public class TwoReaders {

	public static void main(String[] args) throws Exception {
		
		System.out.println("Two processes start synchronously");
		
		ProcessBuilder pa = new ProcessBuilder("notepad.exe", "C:\\book1.txt");
		ProcessBuilder pb = new ProcessBuilder("notepad.exe", "C:\\book2.txt");
		
		Process p1 = pa.start();
		Process p2 = pb.start();
		
		p1.waitFor();
		p2.waitFor();
		
		System.out.println("Wait until both processes completed!");
	}
}