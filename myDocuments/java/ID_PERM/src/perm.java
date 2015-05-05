import java.io.BufferedReader;
import java.io.FileReader;


public class perm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String strFile = "rosalind_perm.txt";
		try{
			FileReader cFr = new FileReader(strFile);
			BufferedReader cBr = new BufferedReader(cFr);
			
			String strLine = cBr.readLine();
			int iNum = Integer.parseInt(strLine);
			
			int[] array = new int[100];
			
			make_perm(3,1,0,array);
			
			cBr.close();
			cFr.close();
		}catch(Exception e){
			System.out.println(e);
		}
		
	}
	
	private static void make_perm(int iTotal, int iNext,int iCount, int[] array){
		int i,iCandidate;
		System.out.println("y");
		/*全部終わっていればここで表示*/
		if(iNext > iTotal){
			iCount++;
			System.out.println(iCount);
			return;
		}
		
		for(iCandidate = 1; iCandidate < iTotal; iCandidate++){
			/*すでに出ている要素かどうか確認*/
			for(i = 1; i < iNext; i++){
				if(array[i] == iCandidate){
					break;
				}
			}
			
			if(i == iNext){
				array[iNext] = iCandidate;
				make_perm(iTotal, iNext+1, iCount, array);
			}
		}
		return;
	}

}
