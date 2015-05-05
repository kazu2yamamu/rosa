import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ORF_id {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FASTAReader cFasta = new FASTAReader();
		
		cFasta.OpenFile("rosalind_orf.txt");
		StringBuffer strEntry = new StringBuffer();
		StringBuffer strSeq = new StringBuffer();
		cFasta.getEntry(strEntry, strSeq);
		
		DnaSequence cDna = new DnaSequence();
		cDna.setString(strSeq.toString());
		DnaSequence cDnaR = new DnaSequence();
		cDnaR.setString(cDna.getReverseDna());
		StringBuffer strSeqR = new StringBuffer(cDna.getReverseDna());
		
		StringBuffer strRna = new StringBuffer(cDna.getRnaSequence());
		StringBuffer strRnaR = new StringBuffer(cDnaR.getRnaSequence());
		/*ORF‚ÌƒŠƒXƒg‚ðŽæ“¾*/
		ArrayList<Integer> listStart = new ArrayList<Integer>();
		for(int i = 0; i < strRna.length(); i++){
			int iTmp = strRna.indexOf("AUG",i);
			if(iTmp != -1){
				listStart.add(iTmp);
				i = iTmp;
			}else{
				break;
			}
		}
		ArrayList<Integer> listStartR = new ArrayList<Integer>();
		for(int i = 0; i < strRnaR.length(); i++){
			int iTmp = strRnaR.indexOf("AUG", i);
			if(iTmp != -1){
				listStartR.add(iTmp);
				i = iTmp;
			}else{
				break;
			}
		}
		
		HashMap<String, Integer> hashAnswer = new HashMap<String, Integer>();
		
		/*ORF‚ð‚·‚×‚ÄŽæ“¾*/
		for(int i = 0; i < listStart.size(); i++){
			cDna.setString(strSeq.substring(listStart.get(i)));
			if(cDna.getProteinSequence().equals("") != true){
				hashAnswer.put(cDna.getProteinSequence(), 1);
			}
		}
		for(int i = 0; i < listStartR.size();i++){
			cDna.setString(strSeqR.substring(listStartR.get(i)));
			if(cDna.getProteinSequence().equals("") != true){
				hashAnswer.put(cDna.getProteinSequence(), 1);
			}
		}
		Iterator<String> it = hashAnswer.keySet().iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
		cFasta.CloseFile();
		
	}

}
