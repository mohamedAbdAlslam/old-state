package stringofos;
import java.util.LinkedList;
import java.util.List;
public class State {

    private int width = 3;

    private char[][] board;

    private int last_move_r = -1;
    private int last_move_c = -1;
    public State(int width) {
        this.width = width;
        board = new char[width][width];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = ' ';
            }
        }
    }
    public State(State state) {
        this.width = state.width;
        board = new char[width][width];
        for (int i = 0; i < width; i++) {
            System.arraycopy(state.board[i], 0, board[i], 0, width);
        }
    }

    public int getWidth() {
        return this.width;
    }

    public void play(int x, int y) {
        if (board[x][y] == ' ') {
            board[x][y] = 'O';
        }
        this.last_move_r = x;
        this.last_move_c = y;
    }

    public List<State> get_Next_States() {
        List<State> nextBoards = new LinkedList<>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] == ' ') {
                    State nextBoard = new State(this);
                    nextBoard.play(i, j);
                    nextBoards.add(nextBoard);
                }
            }
        }
        return nextBoards;
    }

public int eval() {
    if(lose()) { 
        return -10000000;  
    } else {
        int eval = 0;
        for (int i = 0; i < width; i++) {
            if ((board[i][0] == ' ' && board[i][1] == ' ' && board[i][2] == ' ')|| (board[i][0] == ' ' && board[i][1] == ' ' && board[i][2] == ' ')||(board[i][0] == ' ' && board[i][1] == ' ' && board[i][2] == ' ')) {
                eval += +10;  
                System.out.println("no o");
            }
        }
        for (int i = 0; i < width; i++) {
            if ((board[i][0] == ' ' && board[i][1] == 'O' && board[i][2] == 'O')|| (board[i][0] == 'O' && board[i][1] == ' ' && board[i][2] == 'O')||(board[i][0] == 'O' && board[i][1] == 'O' && board[i][2] == ' ')) {
                eval += +100;  
                System.out.println("o");
            }
        }
        for (int i = 0; i < width; i++) {
            if ((board[i][0] == ' ' && board[i][1] == ' ' && board[i][2] == 'O')|| (board[i][0] == 'O' && board[i][1] == ' ' && board[i][2] == ' ')||(board[i][0] == ' ' && board[i][1] == 'O' && board[i][2] == ' ')) {
                eval += +10;  
      
            }
        }
          for (int i = 0; i < width; i++) {
            if ((board[0][i] == ' ' && board[1][i] == 'O' && board[2][i] == 'O')|| (board[0][i] == 'O' && board[1][i] == ' ' && board[2][i] == 'O')||(board[0][i] == 'O' && board[1][i] == 'O' && board[2][i] == ' ')) {
                eval += +100;  
            }
        }
         for (int i = 0; i < width; i++) {
            if ((board[0][i] == ' ' && board[1][i] == ' ' && board[2][i] == 'O')|| (board[0][i] == 'O' && board[1][i] == ' ' && board[2][i] == ' ')||(board[0][i] == ' ' && board[1][i] == 'O' && board[2][i] == ' ')) {
                eval += +10;  
            }
        }
        if ((board[0][0] == 'O' && board[1][1] == 'O' && board[2][2] == ' ')|| (board[0][0] == ' ' && board[1][1] == 'O' && board[2][2] == 'O' )||(board[0][0] == 'O' && board[1][1] == ' ' && board[2][2] == 'O')){
            eval  += 60; 
        }
        if ((board[0][0] == 'O' && board[1][1] == ' ' && board[2][2] == ' ')|| (board[0][0] == ' ' && board[1][1] == ' ' && board[2][2] == 'O' )){
            eval  += 50; 
        }
        
        if ((board[0][2] == 'O' && board[1][1] == 'O' && board[2][0] == ' ')|| (board[0][2] == ' ' && board[1][1] == 'O' && board[2][0] == 'O' )||(board[0][2] == 'O' && board[1][1] == ' ' && board[2][0] == 'O')){
            eval += 60; 
        }
        if ((board[0][2] == 'O' && board[1][1] == ' ' && board[2][0] == ' ')|| (board[0][2] == ' ' && board[1][1] == ' ' && board[2][0] == 'O' )||(board[0][2] == ' ' && board[1][1] == 'O' && board[2][0] == ' ')){
            eval  += 50; 
        }  

        return eval;  
    }
}


    public boolean lose() {
        int row = this.last_move_r;
        int col = this.last_move_c;
        //check row
        List<Character> collected_row = new LinkedList<>();
        for (int c = 0; c < width; c++) {
            collected_row.add(board[row][c]);
        }
        if (all_are_O(collected_row)) {
            return true;
        }
        //check row
        List<Character> collected_col = new LinkedList<>();
        for (int r = 0; r < width; r++) {
            collected_col.add(board[r][col]);
        }
        if (all_are_O(collected_col)) {
            return true;
        }
        // check diag 1
        if (row == col) {
            List<Character> collected_d1 = new LinkedList<>();
            for (int i = 0; i < width; i++) {
                collected_d1.add(board[i][i]);
            }
            if (all_are_O(collected_d1)) {
                return true;
            }
        }

        // check diag 2
        if (row == (width - (col + 1))) {
            List<Character> collected_d1 = new LinkedList<>();
            for (int i = 0; i < width; i++) {
                collected_d1.add(board[i][width - (i + 1)]);
            }
            if (all_are_O(collected_d1)) {
                return true;
            }
        }
        return false;
    }

    private boolean all_are_O(List<Character> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != 'O') {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("   ");
        for (int i = 0; i < width; i++) {
            sb.append(i+1);
            sb.append(" | ");
        }
        sb.delete(sb.length() - 2, sb.length() - 1);
        sb.append("\n");
        sb.append("   ");
        for (int i = 0; i < width; i++) {
            sb.append("....");
        }
        sb.delete(sb.length() - 2, sb.length() - 1);
        sb.append("\n");
        for (int i = 0; i < width; i++) {
            sb.append(i+1);
            sb.append(": ");
            for (int j = 0; j < width; j++) {
                sb.append(board[i][j]);
                sb.append(" | ");
            }
            sb.delete(sb.length() - 2, sb.length() - 1);
            sb.append('\n');
        }
        return sb.toString();
    }
   

}
