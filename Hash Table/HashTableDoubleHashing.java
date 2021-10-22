public class HashTableDoubleHashing {
    public static void main(String[] args) {

         DoubleHashing mayokunPlayList = new DoubleHashing(100);
         mayokunPlayList.insert("Olamide", "Turn Up", "Pheelz", "Olamide Adedeji", "YBNL", "2014");
         mayokunPlayList.insert("Wizkid", "Yemi My Lover", "Pheelz", "Olamide Adedeji", "YBNL", "2014");
         mayokunPlayList.insert("Davido", "Fall", "Tekno", "David Adeleke", "DMW", "2018");
         mayokunPlayList.delete("Davido", "Fall", "Tekno", "David Adeleke", "DMW", "2018");
         //mayokunPlayList.insert("Davido", "Fall", "Tekno", "David Adeleke", "DMW", "2018");
         System.out.println(mayokunPlayList);
         }
    }

class DoubleHashing{
    private Song[] AllSongs;
    private Song[] temp;
    private int size;
    private int count;
    private float loadfactor = 0;

    public DoubleHashing(int size){
     size = primeOutput(size);
     this.size = size;
     this.AllSongs = new Song[size] ;
    }

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

    private int hornerMethod(String artisteName,int newArraySize){
        final int CONSTANT = 27;
        int sum =0;
        for(int i =0; i<=artisteName.length()-1; i++){
            if((int) artisteName.charAt(i)>=96 && (int) artisteName.charAt(i) <=122) {
                sum += ((int) artisteName.charAt(i) - 96);
            }
            if(i != artisteName.length()-1) {
                sum = (sum * CONSTANT)%newArraySize;
            }
        }
        return sum;
    }

    private int compressionMap(Song[] songs,String artiseName, int newArraySize){
        final int CONSTANT = 41;
        artiseName = artiseName.toLowerCase();
        int IntegerHash = hornerMethod(artiseName,newArraySize);
        int insert = (IntegerHash%newArraySize)%newArraySize;
        int i =1;
        while (songs[insert] != null && songs[insert].getArtisteName().compareTo("N/A") != 0){
            insert = (IntegerHash%newArraySize + (i * (CONSTANT - IntegerHash%CONSTANT)) % newArraySize); //main focus
            i++;
            if(insert > newArraySize-1){
                insert = insert - newArraySize;
            }
        }
        return insert;
    }

    private void inserter(Song[] songs, String artisteName, int newArraySize, Song song){
        songs[compressionMap(songs,artisteName,newArraySize)] = song;
    }

    private boolean initialSearch(int insert,Song newSong){
        if(AllSongs[insert] != null && AllSongs[insert].equals(newSong)) {
            return true;
        }
        return false;
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
            if(loadfactor < 0.6){
                inserter(AllSongs,artisteName,AllSongs.length,newSong);
                count++;
                loadfactor = count/AllSongs.length;
            } else {
                size = primeOutput(AllSongs.length * 2);
                temp = new Song[size];
                for (int i = 0; i < AllSongs.length; i++) {
                    if(AllSongs[i] != null){
                        inserter(temp,AllSongs[i].getArtisteName(),size,AllSongs[i]);
                    }
                } // used to increase the hash table and rehash all words again.
                AllSongs = new Song[primeOutput(size)];
                AllSongs = temp;
                inserter(AllSongs,artisteName,size,newSong);
                count++;
                loadfactor = count/AllSongs.length;
            }
        }
    } // Working

    public void delete(
            String artisteName,
            String songsName,
            String producer,
            String writer,
            String label,
            String date
    ) {
        artisteName = artisteName.toLowerCase();
        Song newSong = new Song(artisteName,songsName,producer,writer,label,date);
        if (search(artisteName,songsName,producer,writer,label,date)) { // if the word is not empty or not a duplicate.
            final int CONSTANT = 41;
            int IntegerHash = hornerMethod(artisteName,AllSongs.length);
            int insert = (IntegerHash%AllSongs.length)%AllSongs.length;
            int i =1;
            if(AllSongs[insert].equals(newSong)){
                AllSongs[insert] = new Song("N/A","N/A","N/A","N/A","N/A","N/A");
                count--;
                loadfactor = count/AllSongs.length;
            } else {
            while (AllSongs[insert] != null && AllSongs[insert].getArtisteName().compareTo("N/A") != 0){
                insert = (IntegerHash%AllSongs.length + (i * (CONSTANT - IntegerHash%CONSTANT)) % AllSongs.length); //main focus
                i++;
                if(insert > AllSongs.length-1){
                    insert = insert - AllSongs.length;
                }
                if(AllSongs[insert].equals(newSong)){
                    AllSongs[insert] = new Song("N/A","N/A","N/A","N/A","N/A","N/A");
                    count--;
                    loadfactor = count/AllSongs.length;
                    }
                }
            }
        }
    } // working

    public Song retrieve(String artisteName,
                          String songsName,
                          String producer,
                          String writer,
                          String label,
                          String date){
        artisteName = artisteName.toLowerCase();
        Song newSong = new Song(artisteName,songsName,producer,writer,label,date);
        int constant = 41;
        int IntegerHash = hornerMethod(artisteName,AllSongs.length);
        int insert = IntegerHash%AllSongs.length;
        int i =1;
        if(initialSearch(insert,newSong)){
            return AllSongs[insert];
        } else{
            while (AllSongs[insert] != null) {
                insert = (IntegerHash % AllSongs.length + (i * (constant - IntegerHash % constant)) % AllSongs.length); //main focus
                i++;
                if (insert > AllSongs.length - 1) {
                    insert = insert - AllSongs.length;
                }

                if (AllSongs[insert] != null && AllSongs[insert].equals(newSong)) {
                    return AllSongs[insert];
                }
            }
        }
        return null;
    } // working



    public boolean search(String artisteName,
                         String songsName,
                         String producer,
                         String writer,
                         String label,
                         String date){
            return retrieve(artisteName,songsName,producer,writer,label,date) != null;
        } //working


    public boolean Empty(){
        return count==0;
    } //working

    public String toString(){
        String answer ="";
        for(int i =0; i < AllSongs.length; i++){
            answer += i +"-->"+ AllSongs[i] +"\n";
        }
        return answer;
    } // working
}

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
