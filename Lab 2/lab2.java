import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Lab2
{
	public static void main( String[] args)
	{
		int[] arr = { 3, 5, 5, 9, 10, 10, 11 };
		System.out.println( binarySearch( arr, 3) );

		final int LENGTH = 20;
		final int TOTAL = 100000;
		final String FILENAME = "test.txt";
		createPairTestCases( "testPair.txt");
		System.out.println( "Execution of Random Test Result -> " + executePairTestCases( "testPair.txt", LENGTH) );
		/*if ( !createRandomTestCases( FILENAME, LENGTH, TOTAL) )
		{
			return;
		}*/
		//System.out.println( "Execution of Random Test Result -> " + executeRandomTestCases( FILENAME, LENGTH, TOTAL) );

		//createRandomTestCases( FILENAME, LENGTH, TOTAL);
		//System.out.println( executeRandomTestCases( FILENAME, LENGTH, TOTAL) );
	}

	public static boolean isSorted( int[] arr)
	{
		for ( int i = 0; i + 1 < arr.length; i++)
		{
			if ( arr[ i] > arr[ i + 1] )
			{
				return false;
			}
		}
		return true;
	}

	public static boolean createPairTestCases( String filename)
	{
		int[] def = { 9, 10, 4, -3, 21, 87, 32, -12, 43, 12, 10, 1, -1, 0, 0, 23, -99, -3, 9, 71, 21};
		try
		{
			FileWriter myWriter = new FileWriter( filename);
			// 0 - pair
			for ( int i = 0; i < def.length; i++)
			{
				myWriter.write( def[ i] + " ");
			}
			myWriter.write( "\n");

			// 1 - pair
			for ( int i = 0; i < def.length; i++)
			{
				int temp = def[ i];
				def[ i] = (int) (((Math.random() * 2) - 1) * 100);
				for ( int j = 0; j < def.length; j++ )
				{
					myWriter.write( def[ j] + " ");
				}
				myWriter.write( "\n");
				def[ i] = temp;
			}


			// 2 - pair
			for ( int i = 0; i < def.length; i++)
			{
				for ( int j = i + 1; j < def.length; j++)
				{
					int temp1 = def[ i];
					int temp2 = def[ j];

					def[ i] = (int) (((Math.random() * 2) - 1) * 100);
					def[ j] = (int) (((Math.random() * 2) - 1) * 100);

					for ( int z = 0; z < def.length; z++ )
					{
						myWriter.write( def[ z] + " ");
					}
					myWriter.write( "\n");
					def[ i] = temp1;
					def[ j] = temp2;
				}
			}
			myWriter.close();
			return true;
		}
		catch ( IOException e )
		{
			System.out.println( "Error in createTestCases");
		}
		return false;
	}

	public static boolean createRandomTestCases( String filename, int length, int total)
	{
		try
		{
			FileWriter myWriter = new FileWriter( filename);
			for ( int testNo = 1; testNo <= total; testNo++)
			{
				for ( int i = 0; i < length; i++)
				{
					int randomInt = (int) (( (Math.random() * 2) - 1) * 10000 );
					myWriter.write( randomInt + " ");
				}
				myWriter.write( "\n");
			}
			myWriter.close();
			return true;
		}
		catch ( IOException e )
		{
			System.out.println( "Error in createTestCases");
		}
		return false;
	}

	public static int executeRandomTestCases( String filename, int length, int total)
	{
		try
		{
			Scanner scan = new Scanner( new File( filename) );
			for ( int curTest = 1; curTest <= total; curTest++)
			{
				int[] test = new int[ length];
				int i = 0;
				while ( scan.hasNextInt() )
				{
					test[ i++] = scan.nextInt();

					if ( i == length)
					{
						break;
					}
				}

				if ( !mergeAndFind( test) )
				{
					return curTest;
				}
			}
		}
		catch ( IOException e)
		{
			System.out.println( e.getMessage() );
		}
		return -1;
	}

	public static int executePairTestCases( String filename, int length)
	{
		try
		{
			Scanner scan = new Scanner( new File( filename) );
			int[] test = new int[ length];
			int i = 0;
			int curTest = 1;
			while ( scan.hasNextInt() )
			{
				test[ i++] = scan.nextInt();

				if ( i == length)
				{
					int key = scan.nextInt();
					if ( !mergeAndFind( test, key) )
					{
						return curTest;
					}
					curTest++;
					i = 0;
				}
			}
		}
		catch ( IOException e)
		{
			System.out.println( e.getMessage() );
		}
		return -1;
	}

	//Program 2 Membership queries on sorted arrays of arbitrary length using binary search.
	//Precondition is that array passed in is sorted.
    public static int membershipQ(int[] arr, int key){
        int binarySearchResult = binarySearch(arr, key);
        if(binarySearchResult>=0){
            return 1;
        }
        else{
            return -1;
        }
    }

	//Program 3 Membership queries
	public static boolean mergeAndFind( int[] arr, int key){
		//sort an array and return boolean value if it is sorted
		if (mergeSort(arr)){
			//result takes in either index of key or -1
			int result = binarySearch( arr, key);
			if ( result != -1 ){
				if (arr[result] == key ){
					return true; //occurs when element at the index matches the key provided
				}
				return false; //occurs when element at the index does not match the key provided
			}
			else{
				for ( int i = 0; i < arr.length; i++){
					if (arr[i] == key){
						return false; //occurs when binarySearch claims that key doesn't occur 
									  //in the array but in fact, it does.
					}
				}
			}
			return true;
		}
		return false; //occurs when arr passed in is null or is not sorted
	}

	public static boolean mergeAndFind( int[] arr)
	{
		if ( mergeSort( arr) ){
			int randomIndex = (int)(Math.random() * arr.length);
			int randomNum = arr[ randomIndex ];
			int result = binarySearch( arr, randomNum);

			if ( result != -1 && arr[ result] != arr[ randomIndex] ){
				System.out.println( "Checkpoint 2");
				return false; //occurs when element at the index 
						      //does not match the randomNum key
			}

			randomNum = (int) (( (Math.random() * 2) -1) * 10000 );
			result = binarySearch( arr, randomNum);
			if ( result != -1 ){
				if ( arr[ result] == randomNum ){
					return true;//occurs when element at the index 
								//matches the randomNum key
				}
				return false; //occurs when element at the index 
							  //does not match the key provided
			}
			else{
				for ( int i = 0; i < arr.length; i++){
					if ( arr[ i] == randomNum ){
						System.out.println( "Checkpoint 3");
						return false;//occurs when binarySearch claims 
									 //that randomNum key doesn't occur 
									 //in array,but in fact it does
					}
				}
			}
			return true;
		}
		return false;//occurs when arr passed in is null or is not sorted
	}




	public static int binarySearch( int[] arr, int key)
	{
		int x, l, r;
		l = 0;
		r = arr.length - 1;
		x = ( l + r ) / 2;

		while ( (key != arr[x] ) && ( l <= r) ){
			if ( key < arr[x] ){
				r = x - 1;
			}
			else{
				l = x + 1;
			}
			x = ( l + r ) / 2;
		}

		if ( key == arr[ x] ){
			return x;
		}
		else{
			return -1;
		}
	}

	public static boolean mergeSort( int[] arr )
	{
		if ( arr != null )
		{
			mergeSort(arr, 0, arr.length - 1);
			return isSorted( arr);
		}
		return false;
	}

	public static void mergeSort( int[] arr, int start, int end)
	{
		if ( start >= end)
		{
			return;
		}

		int middle = start + (end - start) / 2;
		mergeSort( arr, start, middle);
		mergeSort( arr, middle + 1, end);

		merge( arr, start, middle, end);
	}

	private static void merge( int[] arr, int start, int middle, int end){

		int[] temp = new int[ end - start + 1];
		int left = start;
		int right = middle + 1;
		int index = 0;

		while ( left <= middle && right <= end){
			
			if ( arr[ left] <= arr[ right] ){
				temp[ index++] = arr[ left];
				left++;
			}
			else{
				temp[ index++] = arr[ right];
				right++;
			}
		}

		while ( left <= middle ){
			temp[ index++] = arr[ left];
			left++;
		}
		while ( right <= end){
			temp[ index++] = arr[ right];
			right++;
		}

		left = start;
		index = 0;
		while ( left <= end){
			arr[left++] = temp[index++];
		}
	}
}
