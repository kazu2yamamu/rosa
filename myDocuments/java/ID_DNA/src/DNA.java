
import java.io.*;

public class DNA {
	public static void main(String[] args){
		String strFileName = "rosalind_dna.txt";
		int iCountOfA = 0;
		int iCountOfT = 0;
		int iCountOfG = 0;
		int iCountOfC = 0;
		int iTotalCount = 0;
		int ch;
		try{
			File openFile = new File(strFileName);
			FileReader fileReader = new FileReader(openFile);
			
			while((ch = fileReader.read()) != -1){
				iTotalCount++;
				if(iTotalCount > 1000){
					System.out.printf("Too Much Characters\r");
					fileReader.close();
					return;
				}
				if(ch == 'A' || ch == 'a'){
					iCountOfA++;
				}else if(ch == 'T' || ch == 't'){
					iCountOfT++;
				}else if(ch == 'G' || ch == 'g'){
					iCountOfG++;
				}else if(ch == 'C' || ch == 'c'){
					iCountOfC++;
				}else if(ch == '\r' || ch == '\n'){
					/*do nothing*/
				}else{
					System.out.printf("Bad char -> %c", ch);
					fileReader.close();
					return;
				}
			}
			
			System.out.printf("%d %d %d %d\r",iCountOfA, iCountOfC, iCountOfG, iCountOfT);
			
			fileReader.close();
		}catch(Exception e){
			System.out.println(e+ ":exception");
		}
	}

}
