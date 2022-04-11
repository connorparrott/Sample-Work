import java.io.File;
import java.io.IOException;
import java.util.Scanner;


/*
 * Got help at CS TA office hours.
 */
public class PlayerLinkedList {

	static Scanner fin;
    static FileOut fout;
    
	public static void main(String[] args) {
		Player squidGame = new Player();
		String toPrint = "Output.txt";
		FileOut printing = new FileOut(toPrint);
		int numNodes = 0;
	
		try{
            fin = new Scanner(new File("input.txt"));
        }catch(IOException e)
        {
            System.err.println(e);
        }
        fout = new FileOut("output.txt");
        while(fin.hasNext()){
				int n = fin.nextInt();
				int k = fin.nextInt();
				int m = fin.nextInt();
			for(int i = 1; i <= n; i++) {
				numNodes++;
				squidGame.add(i);
				}
				squidGame.print();
				System.out.println("N = " + n + " K = " + k + " M = "+ m);
				Node first = squidGame.getNode(1);
				Node last = squidGame.getNode(n);
				Node Stun = first;
				Node Cow = last;
		do{
			System.out.println("Going forwards");
			for(int j = 1; j < k; j++) {
				System.out.println(Stun.getData());
				Stun = Stun.getPrev();
			}
			
			
			System.out.println("Going backwards");
			for(int i = 1; i < m; i++) {
				System.out.println(Cow.getData());
				Cow = Cow.getNext();
			}
			
			
			if(Stun.getData() == Cow.getData()) {
				System.out.println("We have a winner " + Stun.getData());
				printing.writer(Stun.getData());
				Stun = Stun.getPrev();
				Cow = Cow.getNext();
				squidGame.deleteNode(Stun.getNext().getData());
				numNodes--;
				squidGame.print();
			} else {
				Node stunToDelete = squidGame.getNode(Stun.getData());
				Node cowToDelete = squidGame.getNode(Cow.getData());
				System.out.print(Stun.getData() + " will be eliminated.");
				printing.write(Stun.getData() + " ");
				System.out.println(" " + Cow.getData() + " will also be eliminated");
				printing.write(Cow.getData());
				printing.writer("");
				Stun = Stun.getPrev();
				Cow = Cow.getNext();
				while(Stun.getData() == stunToDelete.getData() || Stun.getData() == cowToDelete.getData()) {
					//System.out.println("Moving to avoid deleting a Stun reference");
					Stun = Stun.getPrev();
				}
				
				while(Cow.getData() == stunToDelete.getData() || Cow.getData() == cowToDelete.getData()) {
					//System.out.println("Moving to avoid deleting a cow reference");
					Cow = Cow.getNext();
					
				}
				squidGame.deleteNodeCow(Stun.getNext().getData());
				numNodes--;
				squidGame.deleteNodeCow(Cow.getPrev().getData());
				numNodes--;
				squidGame.print();
				System.out.println("Stun and Cow are: ");
				System.out.println(Stun.getData());
				System.out.println(Cow.getData());
				
			}
		} while(numNodes != 0);
			}
	}
}