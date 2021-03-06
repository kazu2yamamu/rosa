
public class fibd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println(fibona(90,20));

	}
	
	private static final int MAX_MONTH_OLD = 20;
	private static final int MAX_MONTH = 100;
	
	private static long fibona(int iNthMonth, int iAlive){
		long a[][] = new long[MAX_MONTH_OLD][MAX_MONTH];
		long iRetAnser = 0;
		/*a[月齢][月]*/
		/*初期化*/
		a[0][0] = 1;
		for(int i = 1; i < 20; i ++){
			a[i][0] = 0; 
		}
		/*計算*/
		for(int month_i = 1; month_i < iNthMonth; month_i++){
			/*月例0の計算*/
			a[0][month_i] = 0;
			for(int old_i = 1; old_i < iAlive; old_i++){
				a[0][month_i] += a[old_i][month_i - 1];
			}
			/*その他の計算*/
			for(int old_i = 1; old_i < iAlive; old_i++){
				a[old_i][month_i] = a[old_i - 1][month_i - 1];
			}
		}
		
		for(int i = 0; i < iAlive; i++){
			iRetAnser += a[i][iNthMonth -1 ];
		}
		
		return iRetAnser;
	}
		
		
		
}
