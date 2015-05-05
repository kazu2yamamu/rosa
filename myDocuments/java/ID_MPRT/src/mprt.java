import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class mprt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FASTAReader cFasta = new FASTAReader();
		String strFileName = "rosalind_mprt.txt";
		ArrayList<String> listEntries = new ArrayList<String>();
		ArrayList<StringBuffer> listFastaEntry = new ArrayList<StringBuffer>();
		ArrayList<StringBuffer> listFastaSeq = new ArrayList<StringBuffer>();

		try{
			/*エントリーリストのファイルを開く*/
			File openFile = new File(strFileName);
			FileReader fileReader = new FileReader(openFile);
			BufferedReader br = new BufferedReader(fileReader);
			String strEntry;
			while((strEntry = br.readLine()) != null){
				listEntries.add(strEntry);
			}
			fileReader.close();
			br.close();
			

			/*URLからファイルを取得*/
			for(int i = 0; i < listEntries.size(); i++){
				int iRet = OpenUrlFasta(listEntries.get(i), "tmp.txt");
				if(iRet != 0){
					System.out.println("ERR");
					return;
				}
				StringBuffer strFastaEntry = new StringBuffer();
				StringBuffer strSeq = new StringBuffer();
				cFasta.OpenFile("tmp.txt");
				cFasta.getEntry(strFastaEntry, strSeq);
				listFastaEntry.add(strFastaEntry);
				listFastaSeq.add(strSeq);
				cFasta.CloseFile();
			}
			
			Pattern cRegPattern = Pattern.compile("N[^P][ST][^P]");
			for(int i = 0;i < listEntries.size(); i++){
				Matcher cMatch = cRegPattern.matcher(listFastaSeq.get(i));
				if(cMatch.find()){
					System.out.println(listEntries.get(i));
					int iTmp = cMatch.start() + 1;
					System.out.print(iTmp + " ");
					while(cMatch.find()){
						int iTmp2 = cMatch.start() + 1;
						System.out.print(iTmp2 + " ");
					}
					System.out.println();
				}
			}
			
			
			
		}catch(Exception e){
			System.out.println(e+ ":exception");
		}

	}

	
	public static int OpenUrlFasta(String strName, String strFileName){
		try{
			StringBuffer strRemoteFile = new StringBuffer("http://www.uniprot.org/uniprot/");
			strRemoteFile.append(strName);
			strRemoteFile.append(".fasta");
			
			URL url = new URL(strRemoteFile.toString()); //ダウンロードするURL
			URLConnection conn = url.openConnection();
			InputStream in = conn.getInputStream();
	
			File file = new File(strFileName); // 保存先
			FileOutputStream out = new FileOutputStream(file, false);
			int b ;
			while( (b = in.read()) != -1){
			    out.write(b);
			}
			
			out.close();
			in.close();
		}catch(Exception e){
			System.out.println(e);
			return -1;
		}
		return 0;

	}
	
	
}
