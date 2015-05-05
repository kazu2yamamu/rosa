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
		/*�D���̕\���^�p�x��,�z�� * �z���̑S���A�z��*�w�e���̑S���A�w�e��*�w�e����3/4�A�w�e��*�򐫃z����1/2
		 * �򐫃z�� * �D���z���̑S��*/
		double dNextDominantExpress = (iDHomo * (iDHomo - 1)) +
				(2 * iDHomo * iHetero) +
				(0.75 * ((double)iHetero * ((double)iHetero -1.0)))+
				(0.5 * 2.0 * (double)iHetero * (double)iRHomo) +
				(2 * iDHomo * iRHomo);
		double dProbability = dNextDominantExpress / (double)iTotal;
		
		return dProbability;

	}

}
