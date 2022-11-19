package com.company;
public class MiniMax {
    static class Move
    {
        int row, col;
    };
    static char player = 'x', opponent = 'o';

 /* бул функция доскада ход бар экенин текшерет*/
    static Boolean isMovesLeft(char board[][]) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == '_')
                    return true;
        return false;
    }

    /*бул игранын ачкосун эсептейт*/
    static int evaluate(char b[][]) {
        // Checking for Rows for X or O victory.
        for (int row = 0; row < 3; row++) {
            if (b[row][0] == b[row][1] &&
                    b[row][1] == b[row][2]) {
                if (b[row][0] == player)
                    return +10;
                else if (b[row][0] == opponent)
                    return -10;
            }
        }

        // Checking for Columns for X or O victory.
        for (int col = 0; col < 3; col++) {
            if (b[0][col] == b[1][col] &&
                    b[1][col] == b[2][col]) {
                if (b[0][col] == player)
                    return +10;

                else if (b[0][col] == opponent)
                    return -10;
            }
        }

        // Checking for Diagonals for X or O victory.
        if (b[0][0] == b[1][1] && b[1][1] == b[2][2]) {
            if (b[0][0] == player)
                return +10;
            else if (b[0][0] == opponent)
                return -10;
        }

        if (b[0][2] == b[1][1] && b[1][1] == b[2][0]) {
            if (b[0][2] == player)
                return +10;
            else if (b[0][2] == opponent)
                return -10;
        }

        // Else if none of them have won then return 0
        return 0;
    }

    /* минимакс фукнция - мумкун болгон ходдорду эсептейт*/
    static int minimax(char board[][],
                       int depth, Boolean isMax) {
        int score = evaluate(board);

        /*егер максимайзер женсе эсептелген очкосун кайтар*/
        if (score == 10)
            return score;

        /*егер минимайзер женсе эсептелген очкосун кайтар*/
        if (score == -10)
            return score;

        /* 'x rv */
        if (isMovesLeft(board) == false)
            return 0;

        // егер максимайзердин ходу болсо
        if (isMax) {
            int best = -1000;

            // обход кылавыз
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // пустой екенин текшеревиз
                    if (board[i][j] == '_') {
                        // ход журовуз
                        board[i][j] = player;

                        /*минимаксты рекурсивно чакырып максимумду кайтаравыз*/
                        best = Math.max(best, minimax(board,
                                depth + 1, !isMax));

                        // ходду кайтаруу
                        board[i][j] = '_';
                    }
                }
            }
            return best;
        }

        // минимайзер ход
        else {
            int best = 1000;

            //обход
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {

                    if (board[i][j] == '_') {

                        board[i][j] = opponent;

                        /*минимакты рекурсивно чакырып  минимумду кайтаруу*/
                        best = Math.min(best, minimax(board,
                                depth + 1, !isMax));

                        // ходду отмена
                        board[i][j] = '_';
                    }
                }
            }
            return best;
        }
    }

    /*бул оюнчунун эн жакшы ходун кайтарат*/
    static Move findBestMove(char board[][]) {
        int bestVal = -1000;
        Move bestMove = new Move();
        bestMove.row = -1;
        bestMove.col = -1;

        /*бардык пустой  ходду карап  , эн оптималдуу кординатаны кайтрат */
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (board[i][j] == '_') {
                    // ход журуу
                    board[i][j] = player;

                    /*ушуд ход учун очко эсептоо*/
                    int moveVal = minimax(board, 0, false);

                    // отмена хода
                    board[i][j] = '_';

                    /*азр жургон ход эн жакшы ходдон жакшы болсо ордугн алмаштыруу*/
                    if (moveVal > bestVal) {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }

        //System.out.printf("The value of the best Move " +
        //        "is : %d\n\n", bestVal);

        return bestMove;
    }
}
