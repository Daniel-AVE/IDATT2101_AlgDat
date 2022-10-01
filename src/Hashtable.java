import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Hashtable {
    private LinkedList hashTable;
    private int numberOfCollisions;
    private LinkedList[] nodes;
    private String collisions;

    public Hashtable() {
        hashTable = new LinkedList();
        numberOfCollisions = 0;
        nodes = new LinkedList[145];
        collisions = "";
    }

    public int hash(String element) {
        int hash = 0;
        for (int i = 0; i < element.length(); i++) {
            hash += (13 * element.charAt(i)) % nodes.length;
        }
        return hash;
    }

    public int divHash(int k, int m) {
        return k % m;
    }

    public void insert(String element) {
        int hashed = hash(element);

        if (nodes[divHash(hashed, nodes.length)] == null) {
            nodes[divHash(hashed, nodes.length)] = new LinkedList();
            nodes[divHash(hashed, nodes.length)].insertAtBack(element, hashed);
        } else {
            collisions += nodes[divHash(hashed, nodes.length)].getTail().getElementAndkey() + " collides with: ";
            nodes[divHash(hashed, nodes.length)].insertAtBack(element, hashed);
            collisions += nodes[divHash(hashed, nodes.length)].getTail().getElementAndkey() + "\n";
            numberOfCollisions++;
        }
    }

    public int getNumberOfCollisions() {
        return numberOfCollisions;
    }

    public String getCollisions() {
        return collisions;
    }

    public double getCollisionsPerPerson() {
        return (double) numberOfCollisions / nodes.length;
    }

    public int getTotElements() {
        int totElements = 0;
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] == null) ;
            else totElements = nodes[i].getTotElements();
        }
        return totElements;
    }

    public HashNode findPerson(String nameOfPerson) {
        System.out.println(nodes[divHash(hash(nameOfPerson), nodes.length)]);
        return nodes[divHash(hash(nameOfPerson), nodes.length)].findPerson(nameOfPerson);
    }

    public double getLoad() {
        return (double) getTotElements() / nodes.length;
    }

    public static void main(String[] args) throws IOException {
        Hashtable hashTable = new Hashtable();
        String[] names = new String[114];

        try {
            BufferedReader b = new BufferedReader(new FileReader(new File("src/navn.txt")));
            Scanner sc = new Scanner(b);
            int num = 0;
            if (!sc.hasNext()) {
                System.out.println("File is empty");
            } else {
                while (sc.hasNext()) {
                    String data = sc.nextLine();
                    names[num] = data;
                    num++;
                }
                sc.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < names.length; i++) {
            hashTable.insert(names[i]);
        }

        System.out.println(hashTable.getCollisions());
        System.out.println("Number of collisions: " + hashTable.getNumberOfCollisions() + "\nCollisions per person: " + hashTable.getCollisionsPerPerson() + "\nLoad: " + hashTable.getLoad());

        //Slår opp på person
        System.out.println("Find me: " + hashTable.findPerson("Daniel André Vestly Evensen"));
    }

    class HashNode {
        String element;
        HashNode next;
        int key;

        public HashNode(String element, HashNode next, int key) {
            this.key = key;
            this.element = element;
            this.next = next;
        }

        public String getElement() {
            return element;
        }


        public String getElementAndkey() {
            return element + ", " + key;
        }

        @Override
        public String toString() {
            return "Node {" +
                    " element = '" + element + '\'' +
                    ", neste = " + next +
                    ", key = " + key +
                    '}';
        }
    }

    class LinkedList {
        private HashNode head, tail = null;
        private int totElements = 0;

        public void insertAtBack(String element, int key) {
            HashNode node = new HashNode(element, null, key);

            if (head == null) head = node;
            else tail.next = node;

            tail = node;
            tail.next = null;
            totElements++;
        }

        public HashNode findPerson(String nameOfPerson) {
            HashNode node = head;

            for (int i = 0; i < totElements; i++) {
                if (node.getElement().equals(nameOfPerson)) return node;
                node = node.next;
            }
            return null;
        }

        public int getTotElements() {
            return totElements;
        }

        public HashNode getHead() {
            return head;
        }

        public HashNode getTail() {
            return tail;
        }

        @Override
        public String toString() {
            return "LinkedList {" +
                    " head = " + head.toString() +
                    ", totElements = " + totElements +
                    "}";
        }
    }
}