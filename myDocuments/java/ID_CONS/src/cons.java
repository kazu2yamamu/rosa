
public class cons {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FASTAReader cFasta = new FASTAReader();
		cFasta.OpenFile("rosalind_cons.txt");
		
		StringBuffer strEntry = new StringBuffer();
		StringBuffer strSeq = new StringBuffer();
		
		/*最初のエントリーは数を数えるために使う*/
		int iRet;
		iRet = cFasta.getEntry(strEntry, strSeq);
		int aiA[] = new int[strSeq.length()];
		int aiT[] = new int[strSeq.length()];
		int aiG[] = new int[strSeq.length()];
		int aiC[] = new int[strSeq.length()];
		/*初期化*/
		for(int i = 0; i < strSeq.length(); i++){
			aiA[i] = 0;
			aiT[i] = 0;
			aiG[i] = 0;
			aiC[i] = 0;
		}
		
		/*カウント処理*/
		CountSeq(strSeq.toString(), aiA, aiT, aiG, aiC);
				
		while(true){
			iRet = cFasta.getEntry(strEntry, strSeq);
			if(iRet != FASTAReader.FILE_SUCCESS){
				break;
			}
			CountSeq(strSeq.toString(), aiA, aiT, aiG, aiC);
		}
		
		/*最大の文字列を取得*/
		StringBuffer strCons = new StringBuffer();
		for(int i = 0; i < aiA.length; i++){
			char cTmp = HighestATGC(aiA[i], aiT[i], aiG[i], aiC[i]);
			strCons.append(cTmp);
		}
		
		System.out.println(strCons);
		System.out.print("A: ");
		OutputCount(aiA);
		
		System.out.print("C: ");
		OutputCount(aiC);
		
		System.out.print("G: ");
		OutputCount(aiG);
		
		System.out.print("T: ");
		OutputCount(aiT);
		
		return;

	}
	
	private static void CountSeq(String strSeq, int[] aiA, int[] aiT, int[] aiG, int[] aiC){
		
		for(int i = 0; i < strSeq.length(); i++){
			switch(strSeq.charAt(i)){
			case 'A':
			case 'a':
				aiA[i]++;
				break;
			case 'T':
			case 't':
				aiT[i]++;
				break;
			case 'G':
			case 'g':
				aiG[i]++;
				break;
			case 'C':
			case 'c':
				aiC[i]++;
				break;
			default:
				/*do nothing*/
				break;
			}
		}
		
		return;
	}
	
	private static char HighestATGC(int iA, int iT, int iG, int iC){
		if(iA >= iT){
			if(iA >= iG){
				if(iA >= iC){
					return 'A';
				}else{
					return 'C';
				}
			}else if(iG >= iC){
				return 'G';
			}else{
				return 'C';
			}
		}else if(iT >= iG){
			if(iT >= iC){
				return 'T';
			}else{
				return 'C';
			}
		}else if(iG >= iC){
			return 'G';
		}else{
			return 'C';
		}
	}
	
	private static void OutputCount(int[] aiArray){
		for(int i = 0; i < aiArray.length; i++){
			System.out.print(aiArray[i] + " ");
		}
		System.out.println();
	}
}
