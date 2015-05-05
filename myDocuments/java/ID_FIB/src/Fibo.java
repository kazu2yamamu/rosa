
public class Fibo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("fibo 31 4 ->" + fibona(31,4));

	}
	
	private static long fibona(int iNum, int iK){
		if(iNum <= 0){
			return -1;
		}
		if(iNum == 1 || iNum == 2){
			return 1;
		}else{
			return (fibona(iNum - 1, iK) + (iK * fibona(iNum - 2, iK)) );
		}	
	}

}
