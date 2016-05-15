/**
 * Created by Syril on 17-04-2016.
 */
public class BinarySearch {

    public static int firstOccurrence(int[] array, int x) {
        int l = 0, r = array.length - 1, result = -1;

        while (l <= r) {
            int mid = (l + r) / 2;
            if (array[mid] < x) {
                l = mid + 1;
            } else if (array[mid] > x) {
                r = mid - 1;
            } else if (array[mid] == x) {
                result = mid;
                r = mid - 1;
            }
        }

        return result;
    }


    public static int lastOccurrence(int[] array, int x) {

        int l = 0, r = array.length - 1, result = -1;
        while (l <= r) {
            int mid = (l + r) / 2;

            if (array[mid] > x) {
                r = mid - 1;

            } else if (array[mid] < x) {
                l = mid + 1;

            } else if (array[mid] == x) {
                result = mid;
                l = mid + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {

        int[] array1 = {1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 9, 10};  // x = 9, ans = 4
        int[] array2 = {1, 1, 2, 2, 2, 2, 3};  // x = 3, ans = 1

        System.out.println(count(array1, 9));
        System.out.println(count(array2, 3));


    }

    public static int count(int[] array, int x) {

        int first = firstOccurrence(array, x);
        if (first == -1) {
            return 0;
        } else {
            int last = lastOccurrence(array, x);
            return (last - first + 1);
        }
    }
}
