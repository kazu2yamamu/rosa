
import java.io.*;

public class Translate_RNA {

	public static void main(String[] args) {
		String strFileName = "rosalind_rna.txt";
		
		
		try{
			File file = new File(strFileName);
			FileReader fileReader = new FileReader(file);
			int iTotalCount = 0;
			int ch;
			StringBuilder strRnaSeq = new StringBuilder();
		
			while((ch = fileReader.read()) != -1){
				iTotalCount++;
				if(iTotalCount > 1000){
					System.out.printf("Too Much Characters\r");
					fileReader.close();
					return;
				}
				if(ch == 'A' || ch == 'a'){
					strRnaSeq.append("A");
				}else if(ch == 'T' || ch == 't'){
					strRnaSeq.append("U");
				}else if(ch == 'G' || ch == 'g'){
					strRnaSeq.append("G");
				}else if(ch == 'C' || ch == 'c'){
					strRnaSeq.append("C");
				}else if(ch == '\r' || ch == '\n'){
					/*do nothing*/
				}else{
					System.out.printf("bad char -> %c/r", ch);
					fileReader.close();
					return;
				}
			}
			
			System.out.println(strRnaSeq);
			fileReader.close();
			
		}catch(Exception e){
			System.out.println(e+ ":exception");
		}

		
	}

}
