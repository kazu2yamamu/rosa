
import java.io.*;

public class REVC {

	public static void main(String[] args) {
	
		BufferedReader br;
		String strFile = "rosalind_revc.txt";
		StringBuffer strBuf = new StringBuffer();
		
		try{
			br = new BufferedReader(new InputStreamReader(new FileInputStream(strFile)) );
			
			int ch;
			while( (ch = br.read()) != -1 ){
				strBuf.append((char)ch);
			}
			br.close();
		}catch(Exception e){
			System.out.println(e);
		}
		
		DnaSequence cDna = new DnaSequence();
		
		cDna.setString(strBuf.toString());
		
		System.out.println(cDna.getReverseDna());
		

	}

}
