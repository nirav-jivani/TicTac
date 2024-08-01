package com.example.tictac

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var game: TicTacToeGame
    private lateinit var buttons: Array<Array<Button>>
    private lateinit var player1Score: TextView
    private lateinit var player2Score: TextView
    private lateinit var winnerDashboard: TextView

    private var player1Points = 0
    private var player2Points = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        game = TicTacToeGame()

        player1Score = findViewById(R.id.textInputLayout3)
        player2Score = findViewById(R.id.textInputLayout2)
        winnerDashboard = findViewById(R.id.textView3)

        buttons = Array(3) { r ->
            Array(3) { c ->
                val buttonID = "B${r * 3 + c + 1}"
                val resID = resources.getIdentifier(buttonID, "id", packageName)
                findViewById<Button>(resID).apply {
                    setOnClickListener(this@MainActivity)
                }
            }
        }

        findViewById<Button>(R.id.clear).setOnClickListener {
            game.resetGame()
            updateBoard()
            winnerDashboard.text = "WINNER DASHBOARD"
        }
        findViewById<Button>(R.id.reset).setOnClickListener {
            game.resetGame()
            updateBoard()
            winnerDashboard.text = "WINNER DASHBOARD"
            player1Score.text = "Player 1 : 0"
            player2Score.text = "Player 2 : 0"

        }
    }

    override fun onClick(v: View?) {
        if (v is Button) {
            var row = -1
            var col = -1
            when (v.id) {
                R.id.B1 -> { row = 0; col = 0 }
                R.id.B2 -> { row = 0; col = 1 }
                R.id.B3 -> { row = 0; col = 2 }
                R.id.B4 -> { row = 1; col = 0 }
                R.id.B5 -> { row = 1; col = 1 }
                R.id.B6 -> { row = 1; col = 2 }
                R.id.B7 -> { row = 2; col = 0 }
                R.id.B8 -> { row = 2; col = 1 }
                R.id.B9 -> { row = 2; col = 2 }
            }
            if (row != -1 && col != -1) {
                if (game.playMove(row, col)) {
                    updateBoard()
                    checkGameStatus()
                } else {
                    Toast.makeText(this, "Invalid move!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun updateBoard() {
        for (r in 0..2) {
            for (c in 0..2) {
                buttons[r][c].text = when (game.getBoard()[r][c]) {
                    1 -> "X"
                    2 -> "O"
                    else -> ""
                }
            }
        }
    }

    private fun checkGameStatus() {
        when (val winner = game.checkForWinner()) {
            1 -> {
                player1Points++
                updatePoints()
                winnerDashboard.text = "Player 1 wins!"
                game.resetGame()
            }
            2 -> {
                player2Points++
                updatePoints()
                winnerDashboard.text = "Player 2 wins!"
                game.resetGame()
            }
            3 -> {
                winnerDashboard.text = "It's a draw!"
                game.resetGame()
            }
        }
        updateBoard()
    }

    private fun updatePoints() {
        player1Score.text = "Player 1 : $player1Points"
        player2Score.text = "Player 2 : $player2Points"
    }
}
