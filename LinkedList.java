// written by Nilesh Domah

public class LinkedList<T extends Comparable<T>> implements List<T> {
    private boolean isSorted;
    Node<T> head;
    Node<T> currentNode;
    Node<T> holdNodes;
    private int currentIndex;

    //non-headed list

    public LinkedList(){

        Node now = new Node(null);
        now.setData(-1);
        head=now;
        holdNodes=null;
        isSorted = true;
        currentIndex=0;
    }

    @Override
    public boolean add(T element) {
        if (element==null) {
            return false;
        }
       Node<T> newNode = head;
        currentNode = head;

            while (newNode.getNext() != null) {     //while the element has a next, iterate

                newNode = newNode.getNext();
            }
            Node newt= new Node(element);
            newt.setData(currentIndex);
            newNode.setNext(newt);
        isSorted = false;
        return true;
    }

    @Override
    public boolean add(int index, T element) {

        if (head == null || element == null || ((currentIndex == 0) && index == 0) || index > size() || (index < 0)) {
            return false;
        }

       int count = -1;
        Node<T> oldNode = head;
        while(oldNode.getData()<index-1){
         oldNode=oldNode.getNext();
         int num=oldNode.getData();
           }
        Node newt= new Node(element);
        Node temp= oldNode.getNext();
        oldNode.setNext(newt);
        newt.setNext(temp);
       reIndex();
       currentIndex++;

        isSorted = false;
        return true;
    }

    @Override
    public void clear() {
        head.setData(null);    //only have to clear the head
        head.setNext(null);
        head = null;
    }

    @Override
    public boolean contains(T element) {
        currentNode = head;                //check the entire list for matching element
        while (currentNode != null) {
            if (currentNode.getData().equals(element)){
                return true;
            }
            currentNode = currentNode.getNext();
        }
        return false;
    }

    @Override
    public T get(int index) {
        if(index<0 || index>=size())
            return null;
        currentNode = head;                      //iterate until index
        while (currentNode != null) {
            if (currentNode.getData() == index)
                return currentNode.getData();
            currentNode = currentNode.getNext();
        }
        return null;
    }

    public int indexOf(T element) {
        currentNode = head;
        while (currentNode != null) {                     //iterate until element is found
            if (currentNode.getData().equals(element)) {
                return currentNode.getData();
            }
            currentNode = currentNode.getNext();
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        if (head==null)           //head always contains first element, if it is null, list is empty
            return true;
        return false;
    }

    @Override
    public int lastIndexOf(T element) {
        int storeIndex = -1;
        currentNode = head;
        while (currentNode != null) {          //continues through the entire list, takes last place element is found
            if (currentNode.getData().equals(element)) {
                storeIndex = currentNode.getData();
            }
        }
        return storeIndex;
    }

    @Override
    public T set(int index, T element) {
        if (element==null || index<0 || index>=size())
            return null;
        currentNode = head;
        while (currentNode != null) {     //while the element has a next, iterate
            if (currentNode.getData() == index){
                T storeElement = currentNode.getData();
                currentNode.setData(element);
                return storeElement;
            }
            currentNode = currentNode.getNext();
        }
        isSorted=false;
        return null;
    }

    @Override
    public int size() {
        int size=0;
        if(head==null){
            return 0;
        }
        currentNode = head;
        Node now = head;
        while (now.getNext() != null) {
            now = now.getNext(); // getting to the last node
          size++;
        }
   return(currentIndex);
    }

    @Override
    public void sort(boolean order) {
        if ( isSorted == false) {
            Node temp = head.getNext();
            currentNode = temp;
            //Node temp2 = currentNode.getNext();

            int i=0;
            int len=size();
            while (currentNode.getNext() != null) {
                Node<T> nextNode=temp;
                int j = 1;
                while ((j<len-i)){
                    if (order==true&& nextNode.getData().compareTo(nextNode.getNext().getData()) >= 1) {
                        T hold = nextNode.getData();
                        nextNode.setData(nextNode.getNext().getData());
                        nextNode.getNext().setData(hold);
                    }
                    if (order==false&& nextNode.getData().compareTo(nextNode.getNext().getData()) <= -1) {
                        T hold = nextNode.getData();
                        nextNode.setData(nextNode.getNext().getData());
                        nextNode.getNext().setData(hold);
                    }
                        nextNode=nextNode.getNext();
                    j++;
                }
                i++;
                currentNode=currentNode.getNext();
            }

            isSorted = true;
        }
    }

    @Override
    public boolean remove(T element) {
        boolean found = false;
        Node<T> oldNode = head.getNext();
        int count=0;
        while((count<size())&&(!oldNode.getData().equals(element))){

            oldNode=oldNode.getNext();
            count++;

        }
        if(count<=size()&&(oldNode.getData().equals(element))){
            oldNode.setNext(oldNode.getNext());
            currentIndex=currentIndex-1;
            found=true;
        }

        if (found==true){

            return true;}
        else
            return false;
    }

    @Override
    public T remove(int index) {
        if (index<0||index>size())
            return null;
        currentNode = head.getNext();
        int currentIndex = 0;
        while (currentNode!=null && currentIndex<index-1) { //sets current node to the node
            currentNode = currentNode.getNext();                           //one before specified index
            currentIndex++;
        }
        T storedElement = currentNode.getNext().getData();  //store data at element with index index
        currentNode.setNext(currentNode.getNext().getNext());  //sets next of previous node to next of specified node
        currentNode=currentNode.getNext();
        currentIndex+=1;
        while(currentNode != null) {                //decrements all indices of nodes to the right of newNode
            currentNode.setData(currentIndex);
            currentNode=currentNode.getNext();
            currentIndex++;
        }
        return storedElement;
    }

    @Override
    public String toString() {
        String total = "";
        currentNode = head.getNext();
        while (currentNode != null){
            total += currentNode.getData() + " " + currentNode.getData().toString() + "\n";
            currentNode = currentNode.getNext();
        }
        return total;
    }
}
