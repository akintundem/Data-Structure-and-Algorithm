import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class AkintundeMayokunA2Q2 {
    public static void main(String [] args) {
        Scanner file; // used to input the name of file to be used
        Scanner input = new Scanner(System.in);  // Create a Scanner object
        try {
            System.out.println("Please enter the input file name (.txt files only):");
            String filename = input.nextLine();
            FileReader inputTxt = new FileReader(new File(filename));
            file = new Scanner(inputTxt);
            processing(file);
            System.out.println("End of processing.");
        } catch (Exception e) {
            System.out.println("File not found");
        }
    }
    public static void processing(Scanner file){r
        String[] lines = new String[100];
        int count = 0;
        while (file.hasNext()){
            lines[count++] = file.nextLine();
        }
        String[][] table = new String[count][3];
        for(int i = 0; i < count; i++){
            if(((lines[i].split(" ").length)) == 2) {
                table[i][0] = lines[i].split(" ")[0];
                table[i][1] = lines[i].split(" ")[1];
            } else  if(((lines[i].split(" ").length)) == 1) {
                table[i][0] = lines[i].split(" ")[0];
            } else if(((lines[i].split(" ").length)) == 3) {
                table[i][0] = lines[i].split(" ")[0];
                table[i][1] = lines[i].split(" ")[1];
                table[i][2] = lines[i].split(" ")[2];
            }
        }

Train CN = new Train();
for (int i =0; i<count; i++){
            if(table[i][0].compareTo("PICKUP") == 0){
                System.out.println("Processing command: " + table[i][0] + " " + table[i][1]);
                int curr = i+1;
                int engine =0;
                int cargo =0;
                int finalPick = i+ Integer.parseInt(table[i][1]) + 1;
                for(int num = curr; num < finalPick; num++){
                    int value =0;
                    if(table[num][0].compareTo("engine") == 0) {engine +=1;} else {
                        cargo+=1;
                    }
                    if(table[num][1] != null){
                        value = Integer.parseInt(table[num][1]);
                    }
                    CN.pickUp(table[num][0],value);
                }
                System.out.println(engine +" engines and " + cargo+" cars added to train");
            } else if(table[i][0].compareTo("DROPLAST") == 0){
                int value = Integer.parseInt(table[i][1]);
                System.out.println("Processing command: " + table[i][0] + " " +  value);
                System.out.println(CN.dropLast(value) + " cars dropped from train");
            }else if(table[i][0].compareTo("PRINT") == 0){
                System.out.println("Processing command: " + table[i][0] );
                System.out.println(CN);
            }else if(table[i][0].compareTo("DROP") == 0){
                int value = Integer.parseInt(table[i][2]);
                System.out.println("Processing command: " + table[i][0] + " " +  table[i][1] + " " + value);
                System.out.println(CN.drop(table[i][1],value) + " cars dropped from train");
            }else if(table[i][0].compareTo("DROPFIRST") == 0){
                int value = Integer.parseInt(table[i][1]);
                System.out.println("Processing command: " + table[i][0] + " " + value);
                System.out.println(CN.dropFirst(value)  + " cars dropped from train");
            }
        }
    }

}

class TrainCar{
    String typeCargo;
    int valueCargo;
    public TrainCar(String typeCargo, int valueCargo){
        this.typeCargo = typeCargo;
        this.valueCargo = valueCargo;
    }
}

 class Node {
    public TrainCar trainCar;
    public Node fwdTrainCar;
    public Node backTrainCar;

    public Node(TrainCar trainCar, Node fwdTrainCar, Node backTrainCar ){
        this.trainCar = trainCar;
        this.fwdTrainCar = fwdTrainCar;
        this.backTrainCar = backTrainCar;
    }
}

class Train{
    private Node top;
    private Node tail;
    private int numberCargo ;
    private int numberEngine =1;
    private int totalValue;
    public Train(){
        Node newNode = new Node(new TrainCar("engine", 0), tail, null );
        this.top = newNode;
        this.tail = newNode;
    }

    public void pickUp(String cargoType, int value){
        TrainCar newData = new TrainCar(cargoType,value);
        if (newData.typeCargo.compareTo("engine") == 0) {
            top = new Node(newData, top, null);
            if(top.fwdTrainCar != null){
                top.fwdTrainCar.backTrainCar = top;
            }
            numberEngine++;
        } else {
            tail = new Node( newData,null, tail);
            if(tail.backTrainCar != null) {
                tail.backTrainCar.fwdTrainCar = tail;
                totalValue += value;
            }
            numberCargo = numberCargo + 1;
        }
    }

    public boolean isEmpty(){
        return top.fwdTrainCar == null;
    }

    public int dropFirst(int numberDrop) {
        Node curr = top;
        int count = 0;
        while (curr != null && count < numberDrop) {
            if (curr.trainCar.typeCargo.compareTo("engine") != 0) {
                if (top == tail){
                    top = null;
                }
                else if(curr.fwdTrainCar == null){
                    curr.backTrainCar.fwdTrainCar = null;
                    tail = curr.backTrainCar;
                }else if(curr.backTrainCar == null){
                    curr.fwdTrainCar.backTrainCar = null;
                    top = curr.fwdTrainCar;
                } else {
                curr.backTrainCar.fwdTrainCar = curr.fwdTrainCar;
                curr.fwdTrainCar.backTrainCar = curr.backTrainCar;
                }
                totalValue = totalValue - curr.trainCar.valueCargo;
                numberCargo = numberCargo - 1;
                count++;
            }
            curr = curr.fwdTrainCar;
        }
        return count;
    }

    public int dropLast(int numberDrop){
        Node curr = tail;
        int count = 0;
        while(curr != null && count < numberDrop){
            if (curr.trainCar.typeCargo.compareTo("engine") != 0) {
                if (top == tail){
                    top = null;
                }
                else if(curr.fwdTrainCar == null) {
                    curr.backTrainCar.fwdTrainCar = null;
                    tail = curr.backTrainCar;
                }
                else {
                    curr.fwdTrainCar.backTrainCar = curr.backTrainCar;
                    curr.backTrainCar.fwdTrainCar = curr.fwdTrainCar;
                }
                totalValue = totalValue - curr.trainCar.valueCargo;
                numberCargo = numberCargo - 1;
                count++;
            }
                curr = curr.backTrainCar;
        }
        return count;
    }


    public int drop(String typeCargo, int numberDrop) {
        Node curr = top;
        int count = 0;
        while (curr != null && count < numberDrop) {
            if (curr.trainCar.typeCargo.compareTo(typeCargo) == 0) {
                if (top == tail){
                    top = null;
                }
                else if(curr.fwdTrainCar == null){
                    curr.backTrainCar.fwdTrainCar = null;
                } else if(curr.backTrainCar == null){
                    curr.fwdTrainCar.backTrainCar = null;
                    top = curr.fwdTrainCar;
                }else {
                    curr.fwdTrainCar.backTrainCar = curr.backTrainCar;
                    curr.backTrainCar.fwdTrainCar = curr.fwdTrainCar;
                }
                totalValue = totalValue - curr.trainCar.valueCargo;
                numberCargo = numberCargo - 1;
                count++;
            }
            curr = curr.fwdTrainCar;
        }
        return count;
    }


    public String toString(){
        String answer = "";
        answer+= "Total number of engines: " + numberEngine + ", Total number of cargo cars: " + numberCargo + " Total value of cargo: " + totalValue +"\n";
        answer += "The cars on the train are: ";
        Node curr = top;
        while (curr!= null){
            answer += curr.trainCar.typeCargo + " - ";
            curr = curr.fwdTrainCar;
        }
        answer = answer.substring(0,answer.length()-2);
        return answer;
    }

}

