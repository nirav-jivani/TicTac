package com.example.tictac

class TicTacToeGame {
    private val board = Array(3) { IntArray(3) }
    private var currentPlayer = 1

    fun playMove(row: Int, col: Int): Boolean {
        if (board[row][col] != 0) {
            return false
        }
        board[row][col] = currentPlayer
        currentPlayer = if (currentPlayer == 1) 2 else 1
        return true
    }

    fun getBoard(): Array<IntArray> {
        return board
    }

    fun checkForWinner(): Int {
        // Check rows and columns
        for (i in 0..2) {
            if (board[i][0] != 0 && board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
                return board[i][0]
            }
            if (board[0][i] != 0 && board[0][i] == board[1][i] && board[0][i] == board[2][i]) {
                return board[0][i]
            }
        }

        // Check diagonals
        if (board[0][0] != 0 && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            return board[0][0]
        }
        if (board[0][2] != 0 && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            return board[0][2]
        }

        // Check for draw
        if (board.all { row -> row.all { cell -> cell != 0 } }) {
            return 3
        }

        return 0
    }

    fun resetGame() {
        for (i in 0..2) {
            for (j in 0..2) {
                board[i][j] = 0
            }
        }
        currentPlayer = 1
    }
}
