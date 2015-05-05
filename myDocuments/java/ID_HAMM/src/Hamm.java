import java.io.BufferedReader;
import java.io.FileReader;


public class Hamm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileReader cFr = null;
		BufferedReader cBr = null;
		String strFile = "rosalind_hamm.txt";
		
		try{
			cFr = new FileReader(strFile);
			cBr = new BufferedReader(cFr);
			
			String line;
			line = cBr.readLine();
			
			DnaSequence cDna1 = new DnaSequence();
			cDna1.setString(line);
			
			/*Ç‡Ç§àÍçs*/
			line = cBr.readLine();
			DnaSequence cDna2 = new DnaSequence();
			cDna2.setString(line);
			
			System.out.println(cDna2.getHammingDistance(cDna1));
			
			
		}catch(Exception e){
			System.out.println(e);
		}
		
	}

}
