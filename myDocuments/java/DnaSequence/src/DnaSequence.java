import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

public class DnaSequence {
		
	/*DNA Sequence*/
	private StringBuffer m_strDNA;
	/*DNA Sequence Size(total)*/
	private int m_iLen;
	/*ATGCそれぞれの数*/
	private int m_iCountOfA;
	private int m_iCountOfT;
	private int m_iCountOfG;
	private int m_iCountOfC;
	private int m_iCountOfOther;
	private boolean m_bSeqSet;
	
	/*モチーフサーチ用のハッシュマップ*/
	private HashMap<String, ArrayList<Integer>> m_hDnaMotifMap;
	private boolean m_bTableCreated;
	
	/*アミノ酸とコドンの対応表*/
	private static HashMap<String,String> m_hProteinMap = new HashMap<String, String>();
	
	
	/*コンストラクタ*/
	public DnaSequence(){
		this.m_iLen = 0;
		this.m_iCountOfA = 0;
		this.m_iCountOfT = 0;
		this.m_iCountOfG = 0;
		this.m_iCountOfC = 0;
		this.m_iCountOfOther = 0;
		this.m_hDnaMotifMap = new HashMap<String, ArrayList<Integer>>();
		m_bTableCreated = false;
		m_bSeqSet = false;
	}
	
	public boolean setString(String strDna){
		this.m_strDNA = new StringBuffer(strDna);
		this.m_iLen = m_strDNA.length();
		CountATGC();
		m_bSeqSet = true;
		return true;
	}
	
	private void CountATGC(){
		int i;
		/*新たに追加された場合のために全数初期化*/
		m_iCountOfA = 0;
		m_iCountOfT = 0;
		m_iCountOfG = 0;
		m_iCountOfC = 0;
		m_iCountOfOther = 0;
		/*DNA配列に対して数を数える*/
		for(i = 0; i < m_iLen; i++){
			switch(m_strDNA.charAt(i)){
			case 'A':
			case 'a':
				m_iCountOfA++;
				break;
			case 'T':
			case 't':
				m_iCountOfT++;
				break;
			case 'G':
			case 'g':
				m_iCountOfG++;
				break;
			case 'C':
			case 'c':
				m_iCountOfC++;
				break;
			case '\r':
			case '\n':	/*改行文字は数えない*/
				break;
			default:
				m_iCountOfOther++;
				break;
			}
		}
	}
	
	public String getRnaSequence(){
		
		if(m_bSeqSet != true){
			return null;
		}
		
		StringBuffer strRnaSeq = new StringBuffer();
		int i;
		for(i = 0; i < m_iLen; i++){
			switch(m_strDNA.charAt(i)){
			case 'A':
			case 'a':
			case 'G':
			case 'g':
			case 'C':
			case 'c':
				strRnaSeq.append(m_strDNA.charAt(i));
				break;
			case 'T':
				strRnaSeq.append('U');
				break;
			case 't':
				strRnaSeq.append('u');
				break;
			default:
				/*do nothing*/
				break;
			}
		}
		
		return strRnaSeq.toString();
	}
	
	public String getProteinSequence(){
		
		if(m_bSeqSet != true){
			return null;
		}
		
		StringBuffer strProtein = new StringBuffer();
		StringBuffer strRna = new StringBuffer();
		
		/*RNA配列取得*/
		strRna.append(this.getRnaSequence());
		
		/*タンパク質配列へ*/
		strProtein.append(RnaTranslation(strRna.toString()));
		
		return strProtein.toString();
	}
	
	public int[] getSubstringArray(String strDna){
		
		if(this.m_bSeqSet != true){
			return null;
		}
		
		ArrayList<Integer> aiSubstrings = new ArrayList<Integer>();
		
		/*indexOfを用いてモチーフサーチ。もっと効率的な方法は探せるかも*/
		int iPos = 0;
		while(true){
			int iRet = this.m_strDNA.indexOf(strDna,iPos);
			if(iRet == -1){
				break;
			}else{
				aiSubstrings.add(iRet);
				iPos = iRet + 1;
			}
		}
		
		/*ArrayListを配列に。Integerの場合、toArrayでは配列に戻せない*/
		int iArraySize = aiSubstrings.size();
		int aiReturnArray[] = new int[iArraySize];
		Iterator<Integer> iter = aiSubstrings.iterator();
		for(int i = 0; i < iArraySize; i++){
			aiReturnArray[i] = iter.next();
		}
		return aiReturnArray;
	}
	
	public static String getProteinSequence(String strRna){
		StringBuffer strProtein = new StringBuffer();
		
		strProtein.append(RnaTranslation(strRna));
		
		return strProtein.toString();
	}
	
	private static String RnaTranslation(String strRna){
		StringBuffer strProtein = new StringBuffer();
		boolean bStart = false;
		boolean bEnd = false;
		/*タンパク質配列へ*/
		for(int i = 0; i < strRna.length(); i = i+3){
			String strTmp;
			if(strRna.length() > i + 3){
				strTmp = strRna.substring(i, i+3);
			}else{
				break;
			}
			if(m_hProteinMap.containsKey(strTmp)){
				bStart = true;
				if(m_hProteinMap.get(strTmp) == "End"){
					bEnd = true;	/*終了している*/
					break;
				}else{
					strProtein.append(m_hProteinMap.get(strTmp));
				}
			}else{
				/*開始後に変な文字が来た場合終了*/
				if(bStart == true){
					break;
				}
			}
		}
		if(bEnd == true){
			return strProtein.toString();
		}else{
			return "";
		}
	}
	
	
	public String getReverseDna(){
		
		if(m_bSeqSet != true){
			return null;
		}
		
		StringBuffer strReverseDna = new StringBuffer();
		int i;
		for(i = m_iLen - 1; i >= 0; i--){
			switch(m_strDNA.charAt(i)){
			case 'A':
			case 'a':
				strReverseDna.append('T');
				break;
			case 'T':
			case 't':
				strReverseDna.append('A');
				break;
			case 'G':
			case 'g':
				strReverseDna.append('C');
				break;
			case 'C':
			case 'c':
				strReverseDna.append('G');
				break;
			default:
				/*do nothing*/
				break;
			}
		}
		return strReverseDna.toString();
	}
	
	public double getGCContent(){
		
		if(m_bSeqSet != true){
			return -1.0;
		}
		
		double dGCContent = 0.0;
		
		double dGC = m_iCountOfG + m_iCountOfC;
		double dAll = dGC + m_iCountOfA + m_iCountOfT;
		
		dGCContent = 100*(dGC/dAll);
		
		return dGCContent;
	}
	
	public int getHammingDistance(DnaSequence cDna){
		
		if(m_bSeqSet != true){
			return -1;
		}
		
		int iHammingDistance = 0;
		/*他の文字が含まれていればエラー*/
		if(cDna.m_iCountOfOther != 0 || this.m_iCountOfOther != 0){
			return -1;
		}
		/*長さが異なればエラー*/
		if(cDna.m_iLen != this.m_iLen){
			return -1;
		}
		int i;
		for(i = 0; i < this.m_iLen; i++){
			if(cDna.m_strDNA.charAt(i) != this.m_strDNA.charAt(i)){
				iHammingDistance++;
			}
		}
		
		return iHammingDistance;
	}
	
	public int getLength(){
		if(m_bSeqSet != true){
			return -1;
		}
		return m_iLen;
	}
	
	public int getCountA(){
		if(m_bSeqSet != true){
			return -1;
		}
		return m_iCountOfA;
	}
	
	public int getCountT(){
		if(m_bSeqSet != true){
			return -1;
		}
		return m_iCountOfT;
	}
	
	public int getCountG(){
		if(m_bSeqSet != true){
			return -1;
		}
		return m_iCountOfG;
	}
	
	public int getCountC(){
		if(m_bSeqSet != true){
			return -1;
		}
		return m_iCountOfC;
	}
	
	public int getCountOther(){
		if(m_bSeqSet != true){
			return -1;
		}
		return m_iCountOfOther;
	}
	
	
	/*未完成*/
	public void CreateSearchTable(){
		if(m_bSeqSet != true){
			return;
		}
		/*ATGCを4文字のバリエーション全部を作り、Tableに登録する*/
		StringBuffer strATGC = new StringBuffer();
		for(int a = 0; a < 4; a++){
			for(int t = 0; t < 4; t++){
				for(int g = 0; g < 4; g++){
					for(int c = 0; c < 4; c++){
						strATGC.delete(0, strATGC.length());
						AppendATGC(a,strATGC);
						AppendATGC(t,strATGC);
						AppendATGC(g,strATGC);
						AppendATGC(c,strATGC); 
					}
				}
			}
		}
		
		this.m_bTableCreated = true;
	}
	
	private void AppendATGC(int iValue, StringBuffer strATGC){
		switch(iValue){
		case 0:
			strATGC.append("A");
			break;
		case 1:
			strATGC.append("T");
			break;
		case 2:
			strATGC.append("G");
			break;
		case 3:
			strATGC.append("C");
			break;
		default:
			break;
		}
	}
	
	/*static初期化子*/
	static{
		m_hProteinMap.put("UUU", "F");
		m_hProteinMap.put("UUC", "F");
		m_hProteinMap.put("UUA", "L");
		m_hProteinMap.put("UUG", "L");
		m_hProteinMap.put("UCU", "S");
		m_hProteinMap.put("UCC", "S");
		m_hProteinMap.put("UCA", "S");
		m_hProteinMap.put("UCG", "S");
		m_hProteinMap.put("UAU", "Y");
		m_hProteinMap.put("UAC", "Y");
		m_hProteinMap.put("UAA", "End");
		m_hProteinMap.put("UAG", "End");
		m_hProteinMap.put("UGU", "C");
		m_hProteinMap.put("UGC", "C");
		m_hProteinMap.put("UGA", "End");
		m_hProteinMap.put("UGG", "W");
		m_hProteinMap.put("CUU", "L");
		m_hProteinMap.put("CUC", "L");
		m_hProteinMap.put("CUA", "L");
		m_hProteinMap.put("CUG", "L");
		m_hProteinMap.put("CCU", "P");
		m_hProteinMap.put("CCC", "P");
		m_hProteinMap.put("CCA", "P");
		m_hProteinMap.put("CCG", "P");
		m_hProteinMap.put("CAU", "H");
		m_hProteinMap.put("CAC", "H");
		m_hProteinMap.put("CAA", "Q");
		m_hProteinMap.put("CAG", "Q");
		m_hProteinMap.put("CGU", "R");
		m_hProteinMap.put("CGC", "R");
		m_hProteinMap.put("CGA", "R");
		m_hProteinMap.put("CGG", "R");
		m_hProteinMap.put("AUU", "I");
		m_hProteinMap.put("AUC", "I");
		m_hProteinMap.put("AUA", "I");
		m_hProteinMap.put("AUG", "M");
		m_hProteinMap.put("ACU", "T");
		m_hProteinMap.put("ACC", "T");
		m_hProteinMap.put("ACA", "T");
		m_hProteinMap.put("ACG", "T");
		m_hProteinMap.put("AAU", "N");
		m_hProteinMap.put("AAC", "N");
		m_hProteinMap.put("AAA", "K");
		m_hProteinMap.put("AAG", "K");
		m_hProteinMap.put("AGU", "S");
		m_hProteinMap.put("AGC", "S");
		m_hProteinMap.put("AGA", "R");
		m_hProteinMap.put("AGG", "R");
		m_hProteinMap.put("GUU", "V");
		m_hProteinMap.put("GUC", "V");
		m_hProteinMap.put("GUA", "V");
		m_hProteinMap.put("GUG", "V");
		m_hProteinMap.put("GCU", "A");
		m_hProteinMap.put("GCC", "A");
		m_hProteinMap.put("GCA", "A");
		m_hProteinMap.put("GCG", "A");
		m_hProteinMap.put("GAU", "D");
		m_hProteinMap.put("GAC", "D");
		m_hProteinMap.put("GAA", "E");
		m_hProteinMap.put("GAG", "E");
		m_hProteinMap.put("GGU", "G");
		m_hProteinMap.put("GGC", "G");
		m_hProteinMap.put("GGA", "G");
		m_hProteinMap.put("GGG", "G");
	}
	
}
