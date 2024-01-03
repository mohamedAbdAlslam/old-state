package stringofos;
import java.util.List;
import java.util.Scanner;
public class GamePlay {
      int counter =0;
    State board = new State(3);
    public void playGame() {
        System.out.println(board);
        while (true) {
            System.out.println("************USER************");
            getUserMove();
            System.out.println(board);
            if (board.lose()) {
                System.out.println("Computer Wins");
                break;
            }
            System.out.println("************Computer************");
            getComputerMove();
            System.out.println(board);
            if (board.lose()) {
                System.out.println("User Wins");
                break;
            }
        }
    }
    private void getUserMove() {
        Scanner s = new Scanner(System.in);
        int row;
        while (true) {
            System.out.print("Enter row: ");
            row = s.nextInt();
            System.out.println();
            if ((row > 0) && (row < board.getWidth() + 1)) {
                break;
            }
        }
        int col;
        while (true) {
            System.out.print("Enter column: ");
            col = s.nextInt();
            System.out.println();
            if ((col > 0) && (col < board.getWidth() + 1)) {
                break;
            }
        }
        board.play(row - 1, col - 1);
    }
    public static void main(String[] args) {
       
        GamePlay g = new GamePlay();
        g.playGame();
    }
    private void getComputerMove() {
      // board= maxMove(board,Integer.MIN_VALUE,Integer.MAX_VALUE,2).Board;
         board = maxMovea(board).Board;
        System.out.println(counter);
    }
private Board_Eval maxMove(State b) {
    if(b.lose())
    {
    
    }
    int max=Integer.MIN_VALUE;
   
    State bestState = null;
    List <State> states = b.get_Next_States();      
    for(int i=0; i<states.size(); i++) {
      
           
            bestState = states.get(i);
           
              if(max < bestState.eval()) {
                   max= minMove(bestState).eval;
              }
                counter++;
              b = bestState;
        
    }
   // if(bestState != null) {
       
     //  b = bestState;
     
   // }   
    Board_Eval m = new Board_Eval(b, b.eval());  
    return m;
}
private Board_Eval minMove(State b) {
    int min = Integer.MAX_VALUE;
    State bestState = null;
    List <State> states = b.get_Next_States();      
    for(int i=0; i<states.size(); i++) {
        if(min > states.get(i).eval()) {
            min = states.get(i).eval();
            bestState = states.get(i); 
       min=  maxMove(bestState).eval;
         counter++;
        b = bestState;
        }
    }
   // if(bestState != null) {
       
      //  b = bestState;
   // }
    Board_Eval m = new Board_Eval(b, b.eval());    return m;
}
private Board_Eval maxMovea(State b) {
       if(b.lose()){
    Board_Eval m = new Board_Eval(b, b.eval()); 
 return m;
 }
    int max=Integer.MIN_VALUE;
 State bestState = null; 
 List <State> states = b.get_Next_States(); 
 for(int i=0; i<states.size(); i++) {    
   bestState = states.get(i);  
   
    
    max=minMove(bestState).eval; 
     
        counter++;
          b = bestState;
  }
 
 
 

 Board_Eval m = new Board_Eval(b, b.eval()); 
 return m;
} 
private Board_Eval minMovea(State b) {
       if(b.lose()){
    Board_Eval m = new Board_Eval(b, b.eval()); 
 return m;
 }
    int min=Integer.MAX_VALUE;
 State bestState = null; 
 List <State> states = b.get_Next_States(); 
 for(int i=0; i<states.size(); i++) {    
   bestState = states.get(i);  
   
    
    min=minMovea(bestState).eval; 
     
        counter++;
          b = bestState;
  }
 
 
 

 Board_Eval m = new Board_Eval(b, b.eval()); 
 return m;
} 
private Board_Eval maxMove(State b, int alpha, int beta,int depth) {
     if(depth==0||b.lose()){
    Board_Eval m = new Board_Eval(b, b.eval()); 
 return m;
 }
     else{
 State bestState = null; 
 List <State> states = b.get_Next_States(); 
 for(int i=0; i<states.size(); i++) {    
   bestState = states.get(i);  
   
      
    
        if (alpha >= beta) {
            break; 
        } 
     alpha=minMove(bestState, alpha, beta,depth-1).eval; 
     
        counter++;
          b = bestState;
  }
 
     
 

 Board_Eval m = new Board_Eval(b, b.eval()); 
 return m;
} }

private Board_Eval minMove(State b, int alpha, int beta, int depth) { 
 //int min = Integer.MAX_VALUE; 
 if(depth==0||b.lose()){
    Board_Eval m = new Board_Eval(b, b.eval()); 
 return m;
 }
 else{
 State bestState = null; 
 List <State> states = b.get_Next_States(); 
 for(int i=0; i<states.size(); i++) {    
   
   bestState = states.get(i); 
    if (beta <= alpha) {
      break; 
  }
   beta= maxMove(bestState, alpha, beta,depth-1).eval;
    counter++;
  b = bestState;
  }  
 Board_Eval m = new Board_Eval(b, b.eval()); 
 return m;
}
}
}
class Board_Eval{
    State Board;
    Integer eval;

    public Board_Eval(State Board, Integer eval) {
        this.Board = Board;
        this.eval = eval;
    }
    public State getBoard() {
        return Board;
    }
    public void setBoard(State Board) {
        this.Board = Board;
    }
    public Integer getEval(){return eval;}
    public void setEval(Integer eval){this.eval = eval;}}
