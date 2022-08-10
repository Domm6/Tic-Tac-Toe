import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    static ArrayList<Integer> playerPos = new ArrayList<Integer>();
    static ArrayList<Integer> CPUpos = new ArrayList<Integer>();
    public static void main(String[] args) {
       char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                             {'-', '+', '-', '+', '-'}, 
                             {' ', '|', ' ', '|', ' '},
                             {'-', '+', '-', '+', '-'},
                             {' ', '|', ' ', '|', ' '},};

        printGameBoard(gameBoard);

        while(true) {
        Scanner scanner = new Scanner(System.in); 
        System.out.println("Where would you like to place your next marker? (1-9)");
        int playerPosition  = scanner.nextInt();
        while(playerPos.contains(playerPosition) || CPUpos.contains(playerPosition)){
            System.out.println("Sorry this spot is taken. Enter correct position");
            playerPosition = scanner.nextInt();
        }
        placeMarker(gameBoard, playerPosition, "player");

        String result = checkWinner();
        if(result.length() > 0){
            System.out.println(result);
                break;
                }

        printGameBoard(gameBoard);

        Random random = new Random() ;
        int CPUposition = random.nextInt(9) + 1;
        while(playerPos.contains(CPUposition) || CPUpos.contains(CPUposition)){
            CPUposition = random.nextInt(9) + 1;
        }
        placeMarker(gameBoard, CPUposition, "CPU");

        printGameBoard(gameBoard);      
        
        result = checkWinner();
        if(result.length() > 0){
        System.out.println(result);
            break;
            }
        } 

    }

    public static String checkWinner(){
        List topRow = Arrays.asList(1,2,3);
        List middleRow = Arrays.asList(4,5,6);
        List bottomRow = Arrays.asList(7,8,9);
        List leftCol = Arrays.asList(1,4,7);
        List middleCol = Arrays.asList(2,5,8);
        List rightCol = Arrays.asList(3,6,9);
        List acrossRight = Arrays.asList(1,5,9);
        List acrossLeft = Arrays.asList(3,5,7);

        List<List> winningConditions = new ArrayList<List>();
        winningConditions.add(topRow);
        winningConditions.add(middleRow);
        winningConditions.add(bottomRow);
        winningConditions.add(leftCol);
        winningConditions.add(rightCol);
        winningConditions.add(middleCol);
        winningConditions.add(topRow);
        winningConditions.add(acrossRight);
        winningConditions.add(acrossLeft);

        for (List l : winningConditions) {
            if(playerPos.containsAll(l)){
                return "Congratulations you won!";
            } else if (CPUpos.containsAll(l)){
                return "CPU wins! Sorry.";
            } else if (playerPos.size() + CPUpos.size() == 9){
                return "It is a tie!";
            }
        }
        return "";
    }

    public static void printGameBoard(char[][] gameBoard){
            for(char[] row: gameBoard){
            for(char c: row ){
                System.out.print(c);
            }
            System.out.println();
        } 
        System.out.println();
    } 

    public static void placeMarker(char[][] gameBoard, int position, String user){

        try {
            char symbol = ' ';

            if (user.equals("player")){
                symbol = 'X';
                playerPos.add(position);
            } else if(user.equals("CPU")){
                symbol = 'O'; 
                CPUpos.add(position);
            }

                if (position == 1 ){
                    gameBoard[0][0] = symbol;
                } else if(position == 2){
                    gameBoard[0][2] = symbol;
                }else if(position == 3){
                    gameBoard[0][4] = symbol;
                }else if(position == 4){
                    gameBoard[2][0] = symbol;
                }else if(position == 5){
                    gameBoard[2][2] = symbol;
                }else if(position == 6){
                    gameBoard[2][4] = symbol;
                }else if(position == 7){
                    gameBoard[4][0] = symbol;
                }else if(position == 8){
                    gameBoard[4][2] = symbol;
                }else if(position == 9){
                    gameBoard[4][4] = symbol;
                } else {
                    System.out.println("That is not a valid position. Please retry.");
                }    
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid whole number.");
            } catch (Exception e){
                System.out.println("Something went wrong.");
            }  
    }

}
