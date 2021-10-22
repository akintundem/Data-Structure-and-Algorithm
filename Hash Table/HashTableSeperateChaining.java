public class HashTableSeperateChaining {
    public static void main(String[] args) {
        SeperateChaining mayokunPlayList = new SeperateChaining(100);
        mayokunPlayList.insert("Olamide", "Turn Up", "Pheelz", "Olamide Adedeji", "YBNL", "2014");
        mayokunPlayList.insert("Olamide", "TurnUp", "Pheelz", "Olamide Adedeji", "YBNL", "2014");
        mayokunPlayList.insert("Olamide", "TurUp", "Pheelz", "Olamide Adedeji", "YBNL", "2014");
        mayokunPlayList.insert("Olamide", "TurUp", "Pheelz", "Olamide Adedeji", "YBNL", "2014");
        mayokunPlayList.delete("Olamide", "TurUp", "Pheelz", "Olamide Adedeji", "YBNL", "2014");
        mayokunPlayList.insert("Wizkid", "Yemi My Lover", "Pheelz", "Olamide Adedeji", "YBNL", "2014");
        mayokunPlayList.insert("Davido", "Fall", "Tekno", "David Adeleke", "DMW", "2018");
        mayokunPlayList.delete("Davido", "Fall", "Tekno", "David Adeleke", "DMW", "2018");
        mayokunPlayList.insert("Davido", "Fall", "Tekno", "David Adeleke", "DMW", "2018");
        System.out.println(mayokunPlayList);
    }
}
class SeperateChaining{
    private int size; // initial of size hash table array.
    private int count; // how many words are in the hash table array.
    private SingleLinkedList[] temp; // temporary hash table used for rehashing.
    private SingleLinkedList[] AllSongs; // hash table array.
    private float loadFactor =0;

    public SeperateChaining(int size){
        size = primeOutput(size);
        this.size = size;
        AllSongs = new SingleLinkedList[size];
    } //Constructor

    private int primeOutput(int size){
        while (!prime(size)){
            size++;
        }
        return size;
    }
    private boolean prime(int size){
        int j =2;
        while (j*j <= size){
            if(size%j == 0){
                return false;
            }
            j++;
        }
        return true;
    }
    private int hornerMethod(String words,int newArraySize){
        int a = 27; // constant for the horner method
        int sum =0;
        for(int i =0; i<=words.length()-1; i++){
            if((int) words.charAt(i)>=96 && (int) words.charAt(i) <=122) { // you are only dealing with small letter words.
                sum += ((int) words.charAt(i) - 96);
            }
            if(i != words.length()-1) {
                sum = (sum * a)%newArraySize;
            }
        } // horner method
        return sum;
    }

    private void inserter(SingleLinkedList[] Allsongs,String artisteName, int newArraySize, Song newSong){
        int IntegerHash = hornerMethod(artisteName,newArraySize);
        int insert =  (IntegerHash%newArraySize);
        if(Allsongs[insert] == null){
            Allsongs[insert] = new SingleLinkedList();
        }
        Allsongs[insert].insertUnordered(newSong);
    }

    public void insert(String artisteName,
                       String songsName,
                       String producer,
                       String writer,
                       String label,
                       String date){
        artisteName = artisteName.toLowerCase();
        Song newSong = new Song(artisteName,songsName,producer,writer,label,date);
        if (!search(artisteName,songsName,producer,writer,label,date)) { // if the word is not empty or not a duplicate.
            if (loadFactor < 2) {
                inserter(AllSongs, artisteName,AllSongs.length,newSong);
                count++;
                loadFactor = count / AllSongs.length;
            } else {
                size = primeOutput(AllSongs.length * 2);
                temp = new SingleLinkedList[size]; // temporary hash table's array
                for (int i = 0; i < AllSongs.length; i++) {
                    if (AllSongs[i] != null) {
                        Node curr = AllSongs[i].getTop();
                        while (curr != null) {
                            inserter(temp, curr.item.getArtisteName(),size,curr.item);
                            curr = curr.next;
                        }
                    }
                } // used to increase the hash table and rehash all words again.
                AllSongs = new SingleLinkedList[size];
                AllSongs = temp;
                inserter(AllSongs, artisteName,size,newSong);
                count++;
                loadFactor = count / AllSongs.length;
            }
        }
    }


    public Song retrieve(String artisteName,
                         String songsName,
                         String producer,
                         String writer,
                         String label,
                         String date){
        artisteName = artisteName.toLowerCase();
        Song newSong = new Song(artisteName,songsName,producer,writer,label,date);
        int IntegerHash = hornerMethod(artisteName,AllSongs.length);
        int insert = (IntegerHash%AllSongs.length);
        if(AllSongs[insert] != null && AllSongs[insert].search(newSong) != null) {
           return AllSongs[insert].search(newSong).item;
        }
        return null;
    }

    public boolean search(String artisteName,
                          String songsName,
                          String producer,
                          String writer,
                          String label,
                          String date){
        return retrieve(artisteName,songsName,producer,writer,label,date) != null;
    } //working


    public void delete(
            String artisteName,
            String songsName,
            String producer,
            String writer,
            String label,
            String date
    ){
        artisteName = artisteName.toLowerCase();
        Song newSong = new Song(artisteName,songsName,producer,writer,label,date);
        if (search(artisteName,songsName,producer,writer,label,date)) { // if the word is not empty or not a duplicate.
            int IntegerHash = hornerMethod(artisteName,AllSongs.length);
            int insert =  (IntegerHash%AllSongs.length);
            AllSongs[insert].delete(newSong);
            count--;
            loadFactor = count / AllSongs.length;
        }
    } // working

    public boolean Empty(){
        return count==0;
    }

    public String toString(){
        String answer ="";
        for(int i =0; i < AllSongs.length; i++){
            answer += i + "------>>"+ AllSongs[i] +"\n";
        }
        return answer;

    }
}

class Node{
    public Song item; // item / value of current node.
    public Node next; // the node next to the current node.

    public Node(Song newItem, Node newNext) {
        this.item = newItem;
        this.next = newNext;
    } //constructor
}//end class Node

class SingleLinkedList {
    private Node top; // top Node of the singly linked list.


    public SingleLinkedList() {
        this.top = null;
    } //Constructor for singly linked list.

    public boolean isEmpty(){
        return top==null;
    }

    public void insertUnordered(Song newItem){
        Node newNode = new Node(newItem,top);
        top = newNode;
    }

    public Node search(Song key){
        Node curr = top;
        while(curr != null && !curr.item.equals(key)) curr = curr.next; // if the current node is not null and the current node value is not equal to key.
        return curr;
    }

    public int getSize(){
        Node curr = top;
        int count = 0;
        while (curr != null){ // if the linked list is not empty or not at the end and if you have not found your right space in the linked yet.
            curr = curr.next;
            count++;
        } // run the search through the list.
        return count;
    }

    public Node getTop() {
        return top;
    }

    public void delete(Song newSong){
        Node prev = null;
        Node curr = top;
        while (curr != null && !curr.item.equals(newSong)){ // if the linked list is not empty or not at the end and if you have not found your right space in the linked yet.
            prev = curr;
            curr = curr.next;
        } // run the search through the list.
        if(prev != null) {
            prev.next = curr.next;
        } else {
            top = curr.next;
        }// if it is at the beginning of the linked list.
    }

    public String toString() {
        String answer = "";
        Node curr = top;
        while (curr != null) {
            answer += curr.item +" ";
            curr = curr.next;
        }
        return answer;
    }
} //end class SingleLinkedList

class Song{
    private String artisteName;
    private String songsName;
    private String producer;
    private String writer;
    private String label;
    private String date;

    public Song(String artisteName,
                String songsName,
                String producer,
                String writer,
                String label,
                String date){
        this.artisteName = artisteName;
        this.songsName = songsName;
        this.producer = producer;
        this.writer = writer;
        this.label = label;
        this.date = date;
    }

    public String getArtisteName() {
        return artisteName;
    }

    public boolean equals(Song test){
        return artisteName.compareTo(test.artisteName) == 0 && songsName.compareTo(test.songsName) ==0 && producer.compareTo(test.producer) ==0 && writer.compareTo(test.writer) == 0 && label.compareTo(test.label) ==0 && date.compareTo(test.date) ==0;
    }

    public String toString(){
        return "-----------------------------------\n" + "Artiste Name: " + artisteName + "\n" + "Name of Song: " + songsName + "\n" + "Produced by: " + producer + "\n" + "Written by: " + writer +"\n" + "Record Label: " + label +"\n" + "Date: " + date;
    }

}
