import java.io.BufferedReader;
import java.io.FileReader;


public class protein {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileReader cFr = null;
		BufferedReader cBr = null;
		String strFile = "rosalind_prot.txt";
		
		try{
			cFr = new FileReader(strFile);
			cBr = new BufferedReader(cFr);
			
			String line;
			line = cBr.readLine();
			
			System.out.println(DnaSequence.getProteinSequence(line));
			
			
		}catch(Exception e){
			System.out.println(e);
		}
		
	}

}
