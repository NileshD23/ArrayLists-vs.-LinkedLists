// Written by omoto011 and domah001

public class ArrayList<T extends Comparable<T>>  implements List<T> {

    // variable to store the index of the next available (null) space in the array:
    private int trackIndex;
    private boolean isSorted;
    private int size = 2;
    public T[] a = (T[]) new Comparable[size];


    public ArrayList() {
        isSorted = true;
        trackIndex = 0;
    } // end constructor

    @Override
    public boolean add(T element) {

        if (element == null) {
            return false;
        }

        if (trackIndex == a.length) {
            T[] someList = (T[]) new Comparable[a.length * 2];
            for(int i = 0; i < trackIndex; i++) {
                someList[i] = a[i];
            }
            a = someList;
        }

        // adds the element at the next available index:
        a[trackIndex] = element;
        // increments index tracker to the next available index:
        trackIndex += 1;
        isSorted = false;
        return true;
    } // end add

    @Override
    public boolean add(int index, T element) {

        if (element == null || (trackIndex == 0 && (index <= 0)) || index >= a.length || (index < 0)) {
            return false;
        }

        if (trackIndex == a.length) {
            T[] someList = (T[]) new Comparable[a.length * 2];
            for(int i = 0; i < trackIndex; i++) {
                someList[i] = a[i];
            }
            a = someList;
        }

        for (int i = (a.length - 1); i > index; i--) {
            // element at index after gets element at it's previous:
            a[i] = a[i - 1];
        }

        // add specified element at specified index:
        a[index] = element;
        trackIndex += 1;
        isSorted = false;
        return true;
    } // end add


    @Override
    public void clear() {

        for (int i = 0; i < a.length; i++) {
            a[i] = null;
        }

        // index tracker reset to 0:
        trackIndex = 0;
        // sets array to original size:
        a = (T[]) new Comparable[size];
    } // end clear

    @Override
    public boolean contains(T element) {

        // search when is sorted:
        if (isSorted == true) {
            int beginning = 0;
            int end = a.length - 1;
            while (beginning <= end) {
                int middle = (beginning + end) / 2;
                if (a[middle] == null)
                    break;
                if (element == a[middle])
                    return true;
                if (element.compareTo(a[middle]) >= 0)
                    end = middle - 1;
                else
                    beginning = middle + 1;
            }
        }

        else
            // not sorted, looks through entire list:
            for (int i = 0; i < (a.length - 1); i++) {
                if (a[i].equals(element)) {
                    return true;
                }
            }

        return false;
    } // end contains

    @Override
    public T get(int index) {

        // if index is out of bounds:
        if (index < 0 || index > (a.length - 1)) {
            return null;
        }

        return a[index];
    } // end get

    @Override
    public int indexOf(T element) {

        if (element == null)
            return -1;

        // search when is sorted:
        if (isSorted == true) {
            int beginning = 0;
            int end = a.length - 1;
            int middle;

            while (beginning <= end) {
                middle = (beginning + end) / 2;
                if (element.equals(a[middle])) {
                    return middle;
                }
                else if (element.compareTo(a[middle]) < 0) {
                    end = middle - 1;
                }
                else if (element.compareTo(a[middle]) >= 0) {
                    beginning = middle + 1;
                }
            }

        }
        else {
            // not sorted, looks through entire list until found first occurrence:
            for (int i = 0; i < (a.length - 1); i++) {
                if (a[i].equals(element)) {
                    return i;
                }
            }
        }

        // element not found:
        return -1;
    } // end indexOf

    @Override
    public boolean isEmpty() {

        // looks through list until finds a non-null element:
        for (int i = 0; i < (a.length - 1); i++) {
            if (a[i] != null)
                return false;
        }

        return true;
    } // end isEmpty

    @Override
    public int lastIndexOf(T element) {

        if (element == null) {
            return -1;
        }

        // search when is sorted:
        if (isSorted == true) {
            int beginning = 0;
            int end = a.length - 1;
            int middle;

            while (beginning <= end) {
                middle = (beginning + end) / 2;
                if (element.equals(a[middle])) {
                    while (middle + 1 < a.length && element.equals(a[middle + 1])) {
                        middle += 1;
                    }
                    return middle;
                }
                else if (element.compareTo(a[middle]) < 0) {
                    end = middle - 1;
                }
                else if (element.compareTo(a[middle]) >= 0) {
                    beginning = middle + 1;
                }
            }

        }
        
        else {
            for (int i = (trackIndex - 1); i >= 0; i--) {
                // goes through entire list backwards, returns for first occurrence:
                if (a[i].equals(element)) {
                    return i;
                }
                System.out.println(a[i]);
            }
        }

        // element not listed:
        return -1;
    } // end lastIndexOf

    @Override
    public T set(int index, T element) {

        if (element == null || index < 0 || index >= a.length) {
            return null;
        }

        // temp pointer:
        T temp = a[index];
        a[index] = element;
        return temp;
    } // end set

    @Override
    public int size() {

        int count = 0;

        // goes through list, if element then count increments:
        for (int i = 0; i < a.length; i++) {
            if (a[i] != null)
                count += 1;
        }

        //updates isSorted:
        return count;
    } // end size

    @Override
    public void sort(boolean order) {

        T stored;

        for (int i = 1; i < a.length; i++) {
            stored = a[i];
            if (a[i] == null)
                break;
            // decreasing or reverse alphabetical order:
            if (order == false){
                for (int j = i - 1; (j >= 0) && (stored.compareTo(a[j]) >= 0); j--) {
                    a[j + 1] = a[j];
                    a[j] = stored;
                }
            }
            // alphabetical order:
            if (order == true && isSorted == false){
                for (int j = i-1; (j >= 0) && (stored.compareTo(a[j]) < 0); j--) {
                    a[j + 1] = a[j];
                    a[j] = stored;
                }
            }
        }

        if (order == true) {
            isSorted = true;
        }

    } // end sort

    @Override
    public boolean remove(T element) {

        boolean returnElement = false;

        // looks through entire list to see element is there:
        for (int i = 0; i < (a.length - 1); i++) {
            if (element.equals(a[i])) {
                a[i] = null;
                returnElement = true;
                for (int j = i; j < a.length - 1; j++) {
                    // element at index after gets next element:
                    a[j] = a[j + 1];
                }
                break;
            }
        }

        // removes specified element at specified index:
        a[a.length - 1] = null;
        // update index tracker to the next available index:
        trackIndex -= 1;
        return returnElement;
    } // end remove

    @Override
    public T remove(int index) {

        if (index<0 || index>=a.length) {
            return null;
        }

        // stores value at given index:
        T returnElement = a[index];
        for (int j = index; j < (a.length - 1); j++) {
            // element at index after it gets next element
            a[j] = a[j + 1];
        }

        a[a.length - 1] = null;
        return returnElement;
    } // end remove

    @Override
    public String toString() {

        String total = "";

        for (int i = 0; i < (a.length); i++) {
            if (a[i] == null)
                break;
            total += a[i] + "\n";
        }

        return total;
    } // end toString
} // end ArrayList
