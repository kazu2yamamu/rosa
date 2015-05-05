import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.HashMap;

public class mrna {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String strFileName = "rosalind_mrna.txt";
		try{
			File openFile = new File(strFileName);
			FileReader fileReader = new FileReader(openFile);
			BufferedReader cBr = new BufferedReader(fileReader);
			HashMap<String, Integer> hashAminoAcid =new HashMap<String, Integer>();
			
			hashAminoAcid.put("F", 2);
			hashAminoAcid.put("L", 6);
			hashAminoAcid.put("I", 3);
			hashAminoAcid.put("M", 1);
			hashAminoAcid.put("V", 4);
			hashAminoAcid.put("S", 6);
			hashAminoAcid.put("P", 4);
			hashAminoAcid.put("T", 4);
			hashAminoAcid.put("A", 4);
			hashAminoAcid.put("Y", 2);
			hashAminoAcid.put("H", 2);
			hashAminoAcid.put("Q", 2);
			hashAminoAcid.put("N", 2);
			hashAminoAcid.put("K", 2);
			hashAminoAcid.put("D", 2);
			hashAminoAcid.put("E", 2);
			hashAminoAcid.put("C", 2);
			hashAminoAcid.put("W", 1);
			hashAminoAcid.put("R", 6);
			hashAminoAcid.put("G", 4);
			
			
			long lValue = 1;
			StringBuffer strSeq = new StringBuffer();
			String strTmp;
			while((strTmp = cBr.readLine()) != null){
				strSeq.append(strTmp);
			}
			
			for(int i = 0; i < strSeq.length(); i++){
			
				lValue *= hashAminoAcid.get(strSeq.substring(i, i+1));
				if(lValue > 1000000){
					lValue %= 1000000;
				}
			}
			
			/*ストップコドンの数をかける*/
			lValue *= 3;
			if(lValue > 1000000){
				lValue %= 1000000;
			}
			
			System.out.println(lValue);
			
			
			fileReader.close();
		}catch(Exception e){
			System.out.println(e+ ":exception");
		}
	}

}
