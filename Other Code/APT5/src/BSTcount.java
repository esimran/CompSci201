import java.util.Arrays;

public class BSTcount {
	
	long[] myCache;
	
	public long howMany(int [] values){
		myCache = new long[values.length+1];
		Arrays.fill(myCache, -1);
		if(values.length == 1){
			return 1;
		}
		if(values.length == 2){
			return 2;
		}
		myCache[0] = 1;
		myCache[1] = 1;
		myCache[2] = 2;
		return howMany(values.length);
	}
	
	public long howMany(int size){
		if(myCache[size] != -1){
			return myCache[size];
		}
		long result = 0;
		for(int i = 0; i<size; i++){
			result += howMany(i)*howMany(size-1-i);
		}
		myCache[size] = result;
		return result;
	}
}
