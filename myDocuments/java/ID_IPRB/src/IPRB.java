import java.io.*;

public class IPRB {

	public static void main(String[] args) {
		BufferedReader cBr = null;
		FileReader cFr = null;
		String strFile = "rosalind_iprb.txt";
		int iDominantHomo = 0;
		int iHetero = 0;
		int iRecessiveHomo = 0;
		
		try{
			cFr = new FileReader(strFile);
			cBr = new BufferedReader(cFr);
			
			String line;
			line = cBr.readLine();
			String[] strArray = line.split(" ");
			iDominantHomo = Integer.parseInt(strArray[0]);
			iHetero = Integer.parseInt(strArray[1]);
			iRecessiveHomo = Integer.parseInt(strArray[2]);
			
			System.out.println(CalcDominantNumber(iDominantHomo, iHetero, iRecessiveHomo));
			
			cBr.close();
			cFr.close();
			
		}catch(Exception e){
			System.out.println(e);
		}
		

	}
	
	
	public static double CalcDominantNumber(int iDHomo, int iHetero, int iRHomo){

		int iTotal = (iDHomo + iHetero + iRHomo) * (iDHomo + iHetero + iRHomo - 1);
		/*優性の表現型頻度は,ホモ * ホモの全部、ホモ*ヘテロの全部、ヘテロ*ヘテロの3/4、ヘテロ*劣性ホモの1/2
		 * 劣性ホモ * 優性ホモの全部*/
		double dNextDominantExpress = (iDHomo * (iDHomo - 1)) +
				(2 * iDHomo * iHetero) +
				(0.75 * ((double)iHetero * ((double)iHetero -1.0)))+
				(0.5 * 2.0 * (double)iHetero * (double)iRHomo) +
				(2 * iDHomo * iRHomo);
		double dProbability = dNextDominantExpress / (double)iTotal;
		
		return dProbability;

	}

}
