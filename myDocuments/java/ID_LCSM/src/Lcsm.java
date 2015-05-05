import java.util.ArrayList;
import java.util.HashMap;

public class Lcsm {

	/*suffix treeを使いたいが、規模的にこの実装で問題なし。練習のためにはsuffix treeを使いたい。*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FASTAReader cFasta = new FASTAReader();
		cFasta.OpenFile("rosalind_lcsm.txt");
		
		/*fastaの全配列を取得*/
		ArrayList<StringBuffer> listStrSeq = new ArrayList<StringBuffer>();
		StringBuffer strEntry = new StringBuffer();	/*エントリーは使い回しなのでリスト不要*/
		
		while(true){
			StringBuffer strSeq = new StringBuffer();
			int iRet = cFasta.getEntry(strEntry, strSeq);
			if(iRet != FASTAReader.FILE_SUCCESS){
				break;
			}
			listStrSeq.add(strSeq);
		}
		
		/*最初の二つの配列はコンセンサス配列リストを取得のために使う*/
		ArrayList<StringBuffer> listConsensus = new ArrayList<StringBuffer>();
		HashMap<String,Integer> tmpConsensus = new HashMap<String, Integer>();
		int iTotalIndexLength = listStrSeq.get(0).length() - 1;
		for(int i = iTotalIndexLength; i >= 1; i--){	/*2以上の文字列を取得する*/
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
		
		/*コンセンサスリストから残りのfasta配列を比較する*/
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
