
import java.util.ArrayList;

public class O3_graph {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		FASTAReader cFasta = new FASTAReader();
		cFasta.OpenFile("rosalind_grph.txt");
		
		ArrayList<DnaDigraph> listDnaDigraph = new ArrayList<DnaDigraph>();
		
		while(true){
			/*fastaファイルを取得*/
			int iRet;
			StringBuffer strEntry = new StringBuffer();
			StringBuffer strSeq = new StringBuffer();
			iRet = cFasta.getEntry(strEntry, strSeq);
			if(iRet != FASTAReader.FILE_SUCCESS){
				break;
			}
			/*配列に確保*/
			DnaDigraph cDnaDigraph = new DnaDigraph();
			cDnaDigraph.setSequence(strEntry.toString(), strSeq.toString());
			listDnaDigraph.add(cDnaDigraph);
		}
		
		for(int i = 0; i < listDnaDigraph.size(); i++){
			for(int j = 0; j < listDnaDigraph.size(); j++){
				if(j != i){
					listDnaDigraph.get(i).setList(listDnaDigraph.get(j));
				}
			}
		}
		
		for(int i = 0; i < listDnaDigraph.size(); i++){
			if(listDnaDigraph.get(i).getList().size() != 0){
				for(int j = 0; j < listDnaDigraph.get(i).getList().size(); j++){
					System.out.print(listDnaDigraph.get(i).getEntry() + " ");
					System.out.println(listDnaDigraph.get(i).getList().get(j));
				}
			}
		}

		return;
	}

}
