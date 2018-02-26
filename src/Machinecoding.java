import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by hari on 17/11/17.
 */
public class Machinecoding {


    static int board[][] = new int[4][4];
    static ArrayList<coordinates> listOfEmpty = new ArrayList<>();
    static ArrayList<Integer> fibonaccinumbers = new ArrayList<>();

    //initialise all cells to be empty
    public void initialiseBoard(){

        generatefibonos(board.length * board[0].length);

        for(int i = 0 ; i<board.length ; i++){
            for(int j = 0 ; j<board[0].length ; j++){
                listOfEmpty.add(new coordinates(i,j));
            }
        }

        coordinates xy1 = throwRandomcoordinates();
        board[xy1.x][xy1.y] = 1;
        updatelistofEmptyCells();

        coordinates xy2 = throwRandomcoordinates();
        board[xy2.x][xy2.y] = 1;
        updatelistofEmptyCells();

    }

    void generatefibonos(int f){

        int n, a = 0, b = 0, c = 1;
        //System.out.print("Enter value of n:");
        n = f;
        //System.out.print("Fibonacci Series:");
        for(int i = 1; i <= n; i++) {
            a = b;
            b = c;
            c = a + b;
            fibonaccinumbers.add(a);
            //System.out.print(a+" ");
        }

    }

    boolean isadjfibonos(int n1,int n2){

        int first = -1;
        for(int i = 0 ; i < fibonaccinumbers.size()-1 ; ){

            //System.out.println("fibo1: " +fibonaccinumbers.get(i) +"fibo2: "+fibonaccinumbers.get(i+1));
            if(fibonaccinumbers.get(i) == n1 && fibonaccinumbers.get(i+1) == n2 || fibonaccinumbers.get(i) == n2 && fibonaccinumbers.get(i+1) == n1){
                first = i;
                return true;
            }
            else {
                i++;
            }
        }

        return false;
    }

    public boolean winner() {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == fibonaccinumbers.get((board.length * board[0].length) - 1)) {
                    System.out.println("Game over. win");
                    return true;
                }
            }
        }

        return false;
    }

    public void updatelistofEmptyCells(){

        listOfEmpty.clear();
        for(int i = 0 ; i < board.length ; i++){
            for(int j = 0 ; j < board[0].length ; j++){
                if(board[i][j] == 0) {
                    listOfEmpty.add(new coordinates(i, j));
                }
            }
        }
        //listOfEmpty.remove(xy);
    }

    private coordinates throwRandomcoordinates(){

        Random random = new Random();
        int randomnumber = random.nextInt(listOfEmpty.size());

        return listOfEmpty.get(randomnumber);
    }

    public void play(String move){

        switch (move){

            case "up":
                moveUp();
                break;
            case "down":
                movedown();
                break;
            case "right":
                moveRight();
                break;
            case "left":
                moveleft();
                break;

            default:
                System.out.println("invalid move -- only up down right left\n");
                break;
        }

        print();

        if(listOfEmpty.isEmpty()){
            System.out.println("No more empty cells to add");
            return;
        }

        if(winner()){
            System.out.println("\nGame over.you win\n");
            return;
        }
        coordinates coordinates = throwRandomcoordinates();

        if(listOfEmpty.contains(coordinates)) {
            board[coordinates.x][coordinates.y] = 1;
            updatelistofEmptyCells();
            System.out.println("Random xy at "+coordinates.x + coordinates.y);
        }

        print();
    }

    public void print(){

        for(int i = 0 ; i<board.length ; i++){
            for(int j = 0 ; j <board[0].length ; j++){
                System.out.print(board[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println("List of empty cells "+listOfEmpty.toString());
        System.out.println("\n");
    }

    public boolean validMerge(coordinates xy,String direction){

        int currentValue = board[xy.x][xy.y];
        int nextValue = 0;
        int diff = 0;
        int min = 0;

        switch (direction){

            case "up":
                nextValue = board[(xy.x)+1][xy.y];

                if(isadjfibonos(currentValue,nextValue)){
                    //System.out.println(currentValue+","+nextValue+" are adj fibo nos.");
                    return true;
                }

                break;

            case "down":
                nextValue = board[(xy.x)-1][xy.y];
                if(isadjfibonos(currentValue,nextValue)){
                    //System.out.println(currentValue+","+nextValue+" are adj fibo nos.");
                    return true;
                }
                break;

            case "right":
                nextValue = board[(xy.x)][(xy.y)-1];
                if(isadjfibonos(currentValue,nextValue)){
                    //System.out.println(currentValue+","+nextValue+" are adj fibo nos.");
                    return true;
                }
                break;

            case "left":
                nextValue = board[(xy.x)][(xy.y)+1];
                if(isadjfibonos(currentValue,nextValue)){
                    //System.out.println(currentValue+","+nextValue+" are adj fibo nos.");
                    return true;
                }
                break;
        }

        //System.out.print("\tInvalid merge");
        return false;
    }

    public void moveUp(){

        int i = 1,j= 0;
        boolean maxswap = false;

        System.out.println("\nMoving up .. ");

        int l = 0;
        while(l < board.length) {
            for (i = 0; i < board[0].length-1; i++) {
                for (j = 0; j < board.length; j++) {

                    if (board[i][j] == 0) {
                        board[i][j] = board[i+1][j];
                        board[i+1][j] = 0;
                    }
                }
            }
            l++;
        }

        updatelistofEmptyCells();

        for(j = 0 ; j < board[0].length ; j++){
            for(i = 0 ; i < board.length-1 ; i++){
                if(validMerge(new coordinates(i,j),"up")) {
                    //System.out.println("merge--@ "+(i)+","+j);
                    board[i][j] += board[i+1][j];
                    board[i+1][j] = 0;
                    maxswap = true;
                    updatelistofEmptyCells();
                }

                if (maxswap){
                    //System.out.println("max swap: "+maxswap);
                    i++;
                    maxswap = false;
                }
            }

        }

        l = 0;
        while(l < board.length) {
            for (i = 0; i < board[0].length-1; i++) {
                for (j = 0; j < board.length; j++) {

                    if (board[i][j] == 0) {
                        board[i][j] = board[i+1][j];
                        board[i+1][j] = 0;
                    }
                }
            }
            l++;
        }

        updatelistofEmptyCells();

    }

    public void movedown(){

        int i = 0,j= 0;
        boolean maxswap = false;

        int l = 0;
        while(l < board.length) {
            for (i = board.length - 1; i > 0; i--) {
                for (j = 0; j < board[0].length; j++) {

                    if (board[i][j] == 0) {
                        board[i][j] = board[i - 1][j];
                        board[i - 1][j] = 0;
                    }
                }
            }
            l++;
        }

        updatelistofEmptyCells();

        System.out.println("\nMoving down .. ");
        for(j = 0 ; j < board[0].length ; j++){
            for(i = board.length-1 ; i > 0 ; i--){
                if(validMerge(new coordinates(i,j),"down")) {
                    //System.out.println("merge--@ "+(i+1)+","+j);
                    board[i][j] += board[i-1][j];
                    board[i-1][j] = 0;
                    maxswap = true;
                    updatelistofEmptyCells();
                }
            }
            if (maxswap){
                //System.out.println("max swap: "+maxswap);
                i++;
                maxswap = false;
            }
        }

        l = 0;
        while(l < board.length) {
            for (i = board.length - 1; i > 0; i--) {
                for (j = 0; j < board[0].length; j++) {

                    if (board[i][j] == 0) {
                        board[i][j] = board[i - 1][j];
                        board[i - 1][j] = 0;
                    }
                }
            }
            l++;
        }

        updatelistofEmptyCells();

    }


    public void moveRight(){

        int i = 0,j= 0;
        boolean maxswap = false;

        int l = 0;
        while(l < board[0].length) {
            for (i = 0; i < board.length; i++) {
                for (j = board[0].length - 1; j > 0; j--) {

                    if (board[i][j] == 0) {
                        board[i][j] = board[i][j - 1];
                        board[i][j - 1] = 0;
                    }
                }
            }
            l++;
        }

        updatelistofEmptyCells();

        System.out.println("\nMoving right .. ");
        for(i = 0 ; i < board.length ; i++){
            for(j = board[0].length-1 ; j > 0 ; j--){

                if(validMerge(new coordinates(i,j),"right")) {
                    //System.out.println("merge--@ "+(i)+","+j);
                    board[i][j] += board[i][j-1];
                    board[i][j-1] = 0;
                    maxswap = true;
                    updatelistofEmptyCells();
                }
            }

            if (maxswap){
                //System.out.println("max swap: "+maxswap);
                j--;
                maxswap = false;
            }
        }

        l = 0;
        while(l < board[0].length) {
            for (i = 0; i < board.length; i++) {
                for (j = board[0].length - 1; j > 0; j--) {

                    if (board[i][j] == 0) {
                        board[i][j] = board[i][j - 1];
                        board[i][j - 1] = 0;
                    }
                }
            }
            l++;
        }

        updatelistofEmptyCells();
    }

    public void moveleft(){

        int i = 0,j= 0;
        boolean maxswap = false;

        int l = 0;
        while (l < board[0].length) {
            for (i = 0; i < board.length; i++) {
                for (j = 0; j < board[0].length - 1; j++) {

                    if (board[i][j] == 0) {
                        board[i][j] = board[i][j + 1];
                        board[i][j + 1] = 0;
                    }
                }
            }

            l++;
        }

        updatelistofEmptyCells();

        System.out.println("\nMoving left .. ");
        for(i = 0 ; i < board.length ; i++){
            for(j = 0 ; j < board[0].length-1 ; j++){

                if(validMerge(new coordinates(i,j),"left")) {
                    //System.out.println("valid--@ "+(i)+","+(j-1));
                    board[i][j] += board[i][j+1];
                    board[i][j+1] = 0;
                    maxswap = true;
                    updatelistofEmptyCells();
                }
            }

            if (maxswap){
                //System.out.println("max swap: "+maxswap);
                j++;
                maxswap = false;
            }
        }

        l = 0;
        while (l < board[0].length) {
            for (i = 0; i < board.length; i++) {
                for (j = 0; j < board[0].length - 1; j++) {

                    if (board[i][j] == 0) {
                        board[i][j] = board[i][j + 1];
                        board[i][j + 1] = 0;
                    }
                }
            }

            l++;
        }

        updatelistofEmptyCells();

    }

    public static void main(String[] args) {

        Machinecoding game = new Machinecoding();
        game.initialiseBoard();
        game.print();

        System.out.println("Press x to break");

        while(true){

            Scanner in = new Scanner(System.in);
            String move = in.nextLine();
            if(move.equals("x")){
                break;
            }
            game.play(move);
        }

//        game.play("left");
//        game.play("down");
//        game.play("down");
//        game.play("right");
//
//        for(int i = 1 ; i <= 20 ; i++){
//            game.play("left");
//        }


    }

    class coordinates{

        int x;
        int y;

        public coordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x +
                    "," + y+") ";
        }
    }
}
