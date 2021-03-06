package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(),View.OnClickListener {

    lateinit var board: Array<Array<Button>>
    var PLAYER = true
    var TURN_COUNT = 0

    var boardStatus = Array(3){IntArray(3)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1 : Button = findViewById(R.id.button1)
        val button2 : Button = findViewById(R.id.button2)
        val button3 : Button = findViewById(R.id.button3)
        val button4 : Button = findViewById(R.id.button4)
        val button5 : Button = findViewById(R.id.button5)
        val button6 : Button = findViewById(R.id.button6)
        val button7 : Button = findViewById(R.id.button7)
        val button8 : Button = findViewById(R.id.button8)
        val button9 : Button = findViewById(R.id.button9)

        board = arrayOf(
            arrayOf(button1,button2,button3),
            arrayOf(button4,button5,button6),
            arrayOf(button7,button8,button9)
        )

        for(i in board){
            for (button in i){
                button.setOnClickListener(this)
            }
        }

        initializeBoardStatus()

        val reset : Button = findViewById(R.id.reset)
        reset.setOnClickListener{
                PLAYER = true
                TURN_COUNT=0
                initializeBoardStatus()
                newgame()
        }
    }

    private fun newgame() {
        if (PLAYER){
            updatedisplay("Player X Turn")
        }else{
            updatedisplay("Player O Turn")
        }
    }

    private fun initializeBoardStatus() {
        for (i in 0..2)
        {
            for (j in 0..2)
            {
                boardStatus[i][j]=-1
                board[i][j].isEnabled=true
                board[i][j].text=""
            }
        }
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.button1->{
                updateValue(row=0,col=0,player=PLAYER)
            }
            R.id.button2->{
                updateValue(row=0,col=1,player=PLAYER)
            }
            R.id.button3->{
                updateValue(row=0,col=2,player=PLAYER)
            }
            R.id.button4->{
                updateValue(row=1,col=0,player=PLAYER)
            }
            R.id.button5->{
                updateValue(row=1,col=1,player=PLAYER)
            }
            R.id.button6->{
                updateValue(row=1,col=2,player=PLAYER)
            }
            R.id.button7->{
                updateValue(row=2,col=0,player=PLAYER)
            }
            R.id.button8->{
                updateValue(row=2,col=1,player=PLAYER)
            }
            R.id.button9->{
                updateValue(row=2,col=2,player=PLAYER)
            }
        }
        TURN_COUNT++
        PLAYER=!PLAYER

        if (PLAYER){
            updatedisplay("Player X Turn")
        }else{
            updatedisplay("Player O Turn")
        }

        if(TURN_COUNT == 9) {
            updatedisplay("GAME DRAW")
        }

        checkwinner()
    }

    private fun checkwinner() {
        for(i in 0..2)
        {
            if(boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][0] == boardStatus[i][2] )
            {
                if (boardStatus[i][0]==1) {
                    updatedisplay("PLAYER X WINS")
                    break
                }
                else if(boardStatus[i][0]==0){
                    updatedisplay("PLAYER O WINS")
                    break
                }
            }
            else if(boardStatus[0][i] == boardStatus[1][i] && boardStatus[1][i] == boardStatus[2][i] )
            {
                if (boardStatus[0][i]==1) {
                    updatedisplay("PLAYER X WINS")
                    break
                }
                else if(boardStatus[0][i]==0){
                    updatedisplay("PLAYER O WINS")
                    break
                }
            }
            else if(boardStatus[0][0] == boardStatus[1][1] && boardStatus[1][1] == boardStatus[2][2] )
            {
                if (boardStatus[0][0]==1) {
                    updatedisplay("PLAYER X WINS")
                    break
                }
                else if(boardStatus[0][0]==0){
                    updatedisplay("PLAYER O WINS")
                    break
                }
            }
            else if(boardStatus[0][2] == boardStatus[1][1] && boardStatus[1][1] == boardStatus[2][0] )
            {
                if (boardStatus[0][2]==1) {
                    updatedisplay("PLAYER X WINS")
                    break
                }
                else if(boardStatus[0][2]==0){
                    updatedisplay("PLAYER O WINS")
                    break
                }
            }
        }
    }



    private fun updatedisplay(text: String) {
        var displayTv :TextView = findViewById(R.id.displayTv)
        displayTv.text = text
        if(text.contains("WINS"))
            disablebutton()
    }

    private fun disablebutton() {
        for(i in board){
            for (button in i){
                button.isEnabled = false
            }
        }
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {

        val text = if (player) "X" else "O"
        val value = if (player) 1 else 0

        board[row][col].apply {
            isEnabled=false
            setText(text)
        }

        boardStatus[row][col]=value
    }
}