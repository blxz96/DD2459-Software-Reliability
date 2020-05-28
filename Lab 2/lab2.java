class Lab2{

    public static void main(String[] args){

        int[] arr = {3,1,4,5,2,54,32,67,21,10};
        System.out.println("Original:");
        for(int i : arr){
            System.out.print(i + " "); //3 1 4 5 2 54 32 67 21 10
        }

        System.out.println();

        Lab2.insertionSort(arr);
        System.out.println("After Insertion Sort:");
        for(int i : arr){
            System.out.print(i + " "); //1 2 3 4 5 10 21 32 54 67
        }

        System.out.println();

        int binarySearchResult = Lab2.binarySearch(arr, 21);
        System.out.print("Binary Search Result:" + binarySearchResult); //6

        System.out.println();

        int membershipQResult = Lab2.membershipQ(arr, 21);
        System.out.println("Membership Queries:" + membershipQResult); //1


    }
    //Implement sorting of integer arrays of arbitrary length
    public static void insertionSort(int[] arr){
        for(int i=1; i< arr.length; i++){
            for(int j=i-1; j>=0;j--){
                if(arr[j]>arr[i]){
                    int buffer = arr[j];
                    arr[j] = arr[i];
                    arr[i] = buffer;
                    i--;
                }
            }
        }
    }

    //Binary search will return index of the key or -1
    public static int binarySearch(int[] arr, int key){
        int x,l,r;
        l = 1;
        r = arr.length;
        do{
            x = (l+r)/2;
            if(key<arr[x]){
                r = x - 1;
            }
            else{
                l = x + 1;
            }
        }while((key!=arr[x]) && (l<=r));
        
        if(key==arr[x]){
            return x;
        }
        else{
            return -1;
        }
    }

    //Membership queries on sorted arrays of arbitrary length using binary search
    public static int membershipQ(int[] arr, int key){
        int binarySearchResult = binarySearch(arr, key);
        if(binarySearchResult>=0){
            return 1;
        }
        else{
            return -1;
        }
    }

}