import java.io.BufferedReader;
import java.io.FileReader;


public class subs {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileReader cFr = null;
		BufferedReader cBr = null;
		String strFile = "rosalind_subs.txt";
		
		try{
			cFr = new FileReader(strFile);
			cBr = new BufferedReader(cFr);
			
			String line;
			line = cBr.readLine();
			
			DnaSequence cDna = new DnaSequence();
			cDna.setString(line);
			
			line = cBr.readLine();
			
			int aiArray[] = cDna.getSubstringArray(line);
			
			for(int i = 0; i < aiArray.length; i++){
				System.out.print(aiArray[i]+1 + " ");
			}
			
		}catch(Exception e){
			System.out.println(e);
		}
		

	}

}
