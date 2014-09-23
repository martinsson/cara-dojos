package dojos;

public class RomanConverter {
	public String tenths[] = {"I", "X"};
	public String five_then_tenths[] = {"V", "L"};
	
	public String fromArabic(int arabicNumber){
		return fromArabicRec(arabicNumber,0);
	}
	
	private String fromArabicRec(int arabicNumber, int index) {
		if(arabicNumber==0)
			return "";
		int rest = arabicNumber%10;
		String result=new String();
		
		if (rest == 9) {
			result += tenths[index] + tenths[index+1];
			rest -= 9;
		} 

		if (rest == 4) {
			result += tenths[index] + five_then_tenths[index];
			rest -= 4;
		}  else if(rest >= 5 && rest < 9){
			result += five_then_tenths[index];
			rest = rest - 5;
		}
		
		for(;rest>0;rest--){
			result+=tenths[index];
		}
		result = fromArabicRec(arabicNumber/10,index+1)+result;
		return result;
	}

}
