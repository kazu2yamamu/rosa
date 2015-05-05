import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class IEV {

	public static void main(String[] args){
		BufferedReader cBr = null;
		FileReader cFr = null;
		String strFile = "rosalind_iev.txt";

		
		try{
			cFr = new FileReader(strFile);
			cBr = new BufferedReader(cFr);
			
			String line;
			line = cBr.readLine();
			String[] strArray = line.split(" ");
			int iAAxAA = Integer.parseInt(strArray[0]);
			int iAAxAa = Integer.parseInt(strArray[1]);
			int iAAxaa = Integer.parseInt(strArray[2]);
			int iAaxAa = Integer.parseInt(strArray[3]);
			int iAaxaa = Integer.parseInt(strArray[4]);
			int iaaxaa = Integer.parseInt(strArray[5]);
			
			double dDominantPhenotype = 0;
			
			dDominantPhenotype += iAAxAA * 2;
			dDominantPhenotype += iAAxAa * 2;
			dDominantPhenotype += iAAxaa * 2;
			dDominantPhenotype += (double)iAaxAa * 2.0 *(3.0/4.0);
			dDominantPhenotype += (double)iAaxaa * 2.0 * (1.0/2.0);
			dDominantPhenotype += iaaxaa * 0;
			
			System.out.println(dDominantPhenotype);
			
			cBr.close();
			cFr.close();
			
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
