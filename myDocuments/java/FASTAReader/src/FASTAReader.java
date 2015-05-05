import java.io.BufferedReader;
import java.io.FileReader;


public class FASTAReader {

	public static final int END_OF_FILE = 1;
	public static final int FILE_SUCCESS = 0;
	public static final int FILE_ERROR = -1;
	public static final int NOT_FASTA_FORMAT = -2;
	
	private FileReader m_cFr;
	private BufferedReader m_cBr;
	
	private String m_strNextEntry;
	
	private boolean m_bOpened;
	
	public FASTAReader(){
		m_cFr = null;
		m_cBr = null;
		m_bOpened = false;
		m_strNextEntry = null;
	}
	
	
	public int OpenFile(String strFile){
		try{
			m_cFr = new FileReader(strFile);
			m_cBr = new BufferedReader(m_cFr);
			
			m_bOpened = true;

		}catch(Exception e){
			System.out.println(e);
			return FILE_ERROR;
		}
		
		return FILE_SUCCESS;
	}
	
	
	public int CloseFile(){
		try{
			if(m_bOpened != true){
				return FILE_ERROR;
			}
			m_cFr.close();
			m_cBr.close();
			m_bOpened = false;
		}catch(Exception e){
			System.out.println(e);
			return FILE_ERROR;
		}
		return FILE_SUCCESS;
	}
	
	
	public int getEntry(StringBuffer strEntry, StringBuffer strSequence){
		
		if(m_bOpened != true){
			return FILE_ERROR;
		}
		/*appendしているので削除*/
		strEntry.delete(0, strEntry.length());
		strSequence.delete(0, strSequence.length());
		
		StringBuffer strReturnSeq = new StringBuffer();
		String strSeq;
		try{
			if(m_strNextEntry == null){	/*次のエントリーを持っていない(最初のエントリー）*/
				String line = m_cBr.readLine();
				if(line.charAt(0) != '>'){
					return NOT_FASTA_FORMAT;
				}
				/*1文字目を除いてエントリーに*/
				strEntry.append(line.substring(1));
				
				while((strSeq = m_cBr.readLine()) != null){
					if(strSeq.charAt(0) == '>'){
						this.m_strNextEntry = strSeq.substring(1);
						break;
					}else{
						strReturnSeq.append(strSeq);
					}
				}
			}else{	/*前のエントリーがある*/
				boolean bGetFlag = false;	/*すでにデータを取得しているかどうかのフラグ*/
				strEntry.append(m_strNextEntry);
				while(true){
					strSeq = m_cBr.readLine();
					if(strSeq == null){
						if(bGetFlag == false){
							return END_OF_FILE;
						}else{
							break;
						}
					}else{
						bGetFlag = true;
						if(strSeq.charAt(0) == '>'){
							this.m_strNextEntry = strSeq.substring(1);
							break;
						}else{
							strReturnSeq.append(strSeq);
						}
					}
				}				
			}
			/*配列は最後に格納*/
			strSequence.append(strReturnSeq.toString());
		}catch(Exception e){
			System.out.println(e);
			return FILE_ERROR;
		}
		
		return FILE_SUCCESS;
	}
	
	
	
	

}
