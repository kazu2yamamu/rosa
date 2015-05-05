
import java.io.*;

public class GCCheck {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileReader cFr = null;
		BufferedReader cBr = null;
		String strFile = "rosalind_gc.txt";
		StringBuffer strHighestEntry = null;
		DnaSequence cDna = new DnaSequence();
		double dHighestGC = 0.0;
		StringBuffer strLastEntry = null;
		StringBuffer strSequence = null;
		
		
		try{
			cFr = new FileReader(strFile);
			cBr = new BufferedReader(cFr);
			
			String line;
			while((line = cBr.readLine()) != null){
				if(line.charAt(0) == '>'){
					if(strSequence == null){
						/*�ŏ��̃G���g���[�Ȃ̂Ŗ���*/
						/*do nothing*/
					}else{
						cDna.setString(strSequence.toString());
						if(dHighestGC < cDna.getGCContent()){
							dHighestGC = cDna.getGCContent();
							strHighestEntry = new StringBuffer(strLastEntry);
						}
					}
					/*�擪��1�����͏����Ċi�[*/
					strLastEntry = new StringBuffer(line.substring(1));
					strSequence = null;
				}else{
					if(strSequence == null){
						strSequence = new StringBuffer(line);
					}else{
						strSequence.append(line);
					}
				}
			}
			
			/*�ŏI�s�I������Ō�̃G���g���[���v�Z*/
			cDna.setString(strSequence.toString());
			if(dHighestGC < cDna.getGCContent()){
				dHighestGC = cDna.getGCContent();
				strHighestEntry = new StringBuffer(strLastEntry);
			}
			
			System.out.println(strHighestEntry);
			System.out.println(dHighestGC);
			
			cBr.close();
			cFr.close();
			
			
			
		}catch(Exception e){
			System.out.println(e);
		}
		
		

	}

}
