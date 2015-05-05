import java.util.ArrayList;
import java.util.HashMap;

public class Lcsm {

	/*suffix tree���g���������A�K�͓I�ɂ��̎����Ŗ��Ȃ��B���K�̂��߂ɂ�suffix tree���g�������B*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FASTAReader cFasta = new FASTAReader();
		cFasta.OpenFile("rosalind_lcsm.txt");
		
		/*fasta�̑S�z����擾*/
		ArrayList<StringBuffer> listStrSeq = new ArrayList<StringBuffer>();
		StringBuffer strEntry = new StringBuffer();	/*�G���g���[�͎g���񂵂Ȃ̂Ń��X�g�s�v*/
		
		while(true){
			StringBuffer strSeq = new StringBuffer();
			int iRet = cFasta.getEntry(strEntry, strSeq);
			if(iRet != FASTAReader.FILE_SUCCESS){
				break;
			}
			listStrSeq.add(strSeq);
		}
		
		/*�ŏ��̓�̔z��̓R���Z���T�X�z�񃊃X�g���擾�̂��߂Ɏg��*/
		ArrayList<StringBuffer> listConsensus = new ArrayList<StringBuffer>();
		HashMap<String,Integer> tmpConsensus = new HashMap<String, Integer>();
		int iTotalIndexLength = listStrSeq.get(0).length() - 1;
		for(int i = iTotalIndexLength; i >= 1; i--){	/*2�ȏ�̕�������擾����*/
			for(int j = 0; j <= (iTotalIndexLength - i) ; j++){
				int iEnd = j + i + 1;
				StringBuffer strTmp = new StringBuffer(listStrSeq.get(0).substring(j,iEnd).toString());
				if(listStrSeq.get(1).indexOf(strTmp.toString()) != -1){
					if(tmpConsensus.containsKey(strTmp.toString()) != true){
						listConsensus.add(strTmp);
						tmpConsensus.put(strTmp.toString(), 1);
					}
				}
			}
		}
		
		/*�R���Z���T�X���X�g����c���fasta�z����r����*/
		for(int i = 0; i < listConsensus.size(); i++){
			boolean bFlag = true;
			String tmpStr = listConsensus.get(i).toString();
			for(int j = 2; j < listStrSeq.size(); j++){
				if(listStrSeq.get(j).indexOf(tmpStr) == -1){
					bFlag = false;
					break;
				}
			}
			if(bFlag == true){
				System.out.println(tmpStr);
				break;
			}
		}
		
		
		return;

	}

}
