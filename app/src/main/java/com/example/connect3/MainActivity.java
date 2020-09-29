package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import androidx.gridlayout.widget.GridLayout;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    //0: yellow, 1:red
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    boolean isGameOver = false;

    public void dropIn(View v)
    {
        ImageView counter = (ImageView) v;
        TextView winner = (TextView) findViewById(R.id.winnerTextView);

        //get what slot has been tapped
        int tapped = Integer.parseInt(counter.getTag().toString());

        //put a chip if not before
        if (gameState[tapped] == 2 && !isGameOver)
        {

            gameState[tapped] = activePlayer;

            v.setY(-1500);
            if (activePlayer == 0)
            {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;

            } else
            {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().rotation(3600).translationY(10).setDuration(300);

            //Check if the game is won
            String winnerCounter;
            for (int[] winningPosition : winningPositions)
            {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[2]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] != 2)
                {

                    if (gameState[winningPosition[0]] == 1)
                    {
                        winnerCounter = "Red";
                    } else
                    {
                        winnerCounter = "Yellow";
                    }
                    winner.setVisibility(View.VISIBLE);
                    winner.setText(winnerCounter + " has won!");
                    isGameOver = true;
                }
            }

            //Check if draw
            boolean gameOver = true;
            if (!isGameOver)
            {
                for (int i : gameState)
                {
                    if (i == 2)
                    {
                        gameOver = false;
                    }
                }
                if (gameOver)
                {
                    winner.setVisibility(View.VISIBLE);
                    winner.setText("It's a draw!");
                    isGameOver = true;
                }
            }
        }

    }

    public void newGame(View v)
    {
        TextView winner = (TextView) findViewById(R.id.winnerTextView);
        winner.setVisibility(View.INVISIBLE);

        GridLayout myGridLayout = (GridLayout) findViewById(R.id.gridlayout);

        //remove images from board
        for (int i = 0; i < myGridLayout.getChildCount(); i++)
        {
            ImageView child = (ImageView) myGridLayout.getChildAt(i);
            child.setImageDrawable(null);
        }

        //Reset values
        activePlayer = 0;
        isGameOver = false;
        for (int i = 0; i < 9; i++)
        {
            gameState[i] = 2;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}