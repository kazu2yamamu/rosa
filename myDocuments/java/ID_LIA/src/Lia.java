import java.io.BufferedReader;
import java.io.FileReader;


public class Lia {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader cBr = null;
		FileReader cFr = null;
		String strFile = "rosalind_lia.txt";

		try{
			cFr = new FileReader(strFile);
			cBr = new BufferedReader(cFr);
			
			String line;
			line = cBr.readLine();
			String[] strArray = line.split(" ");
			
			int iKth = Integer.parseInt(strArray[0]);
			int iAtLeastN = Integer.parseInt(strArray[1]);
			double dNumberOfAA = 0;
			double dNumberOfAa = 0;
			double dNumberOfaa = 0;
			double dOldAa = 0;
			double dOldAA = 0;
			double dOldaa = 0;
			
			/*F0はTomのみ*/
			dNumberOfAa = 1.0;
			/*Fnの計算*/
			for(int i = 1; i <= iKth; i++){
				dOldAA = dNumberOfAA;
				dOldAa = dNumberOfAa;
				dOldaa = dNumberOfaa;
				dNumberOfAA = 2*dOldAA*(1.0/2.0) + 2*dOldAa*(1.0/4.0);
				dNumberOfAa = 2*dOldAA*(1.0/2.0) + 2*dOldAa*(1.0/2.0) + 2*dOldaa*(1.0/2.0);
				dNumberOfaa = 2*dOldAa*(1.0/4.0) + 2*dOldaa*(1.0/2.0);
			}
		
			double dTotalNumber = Math.pow(2, iKth);
			double dProbabilityOfAa = dNumberOfAa/dTotalNumber;
			double dProbabilityOfAaBb = dProbabilityOfAa * dProbabilityOfAa;
			double dAnswer = 0.0;
			
			for(int i = iAtLeastN; i <= (int)dTotalNumber; i++){
				dAnswer += calcCombi((int)(dTotalNumber), i, Math.pow(dProbabilityOfAaBb,i),Math.pow((1.0 - dProbabilityOfAaBb), (int)dTotalNumber - i));
			}
			
			System.out.println(dAnswer);
			
			cBr.close();
			cFr.close();
			
		}catch(Exception e){
			System.out.println(e);
		}
		

	}
	
	/*オーバーフローしないようにコンビネーションと確率を同時に計算*/
	public static double calcCombi(int n, int k, double dP1, double dP2){
		
		double dTmp = dP1 * dP2;
		int iStart = k;
		
		for(int i = n; i > n-k; i--){
			dTmp *= i;
			dTmp /= iStart;
			iStart--;
		}
		return dTmp;
	}
	


}
