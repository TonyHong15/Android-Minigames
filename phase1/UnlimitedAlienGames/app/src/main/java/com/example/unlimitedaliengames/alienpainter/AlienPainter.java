package com.example.unlimitedaliengames.alienpainter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unlimitedaliengames.MainActivity;
import com.example.unlimitedaliengames.R;
import com.example.unlimitedaliengames.userdata.User;


public class AlienPainter extends AppCompatActivity implements View.OnClickListener, AlienPainterView {

    private ImageButton[][] grid = new ImageButton[3][3];

    /**
     * Holds the textView for number of moves the player has made
     */
    private TextView painterTextViewMoves;

    /**
     * Holds the textView for amount of time that has passed
     */
    private TextView painterTextViewTime;

    /**
     * Holds the instance of AlienPainterTimer for the...timer
     */
    private AlienPainterTimer painterTimer;

    /**
     * Holds the presenter of this game
     */
    private AlienPainterPresenter presenter;

    /**
     * Holds the button for exiting the game
     */
    private Button exitButton;

    /**
     * Holds the button for retrying the game
     */
    private Button retryButton;

    /**
     * Holds the current user of this game
     */
    private User currUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alien_painter);

        Intent intent = getIntent();
        currUser = (User) intent.getParcelableExtra("parcel_data");

        painterTextViewMoves = findViewById(R.id.painterTextView1);
        painterTextViewTime = findViewById(R.id.painterTextView2);
        exitButton = findViewById(R.id.exitButton);
        retryButton = findViewById(R.id.retryButton);

        //Initialize the Timer
        painterTimer = new AlienPainterTimer(this);

        //Setup the exitButton and retryButton
        buttonSetup();

        //Construct the 2D imageButton array
        gridCreation();

        //Game Instructions
        Toast.makeText(this, "Click on each button to try to get all of them to turn black", Toast.LENGTH_LONG).show();

        //Start the Timer
        startTimer();

        //Initialize the Presenter
        presenter = new AlienPainterPresenter(this, painterTimer, grid, new AlienPainterFunctions(this, grid, this), this);
    }

    /**
     * Setup for the exitButton and retryButton.
     * When exitButton is clicked, the game goes back to MainActivity.
     * When retryButton is called, the game resets its timer and generates a new board.
     */
    private void buttonSetup() {
        //Setup the exitButton
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), MainActivity.class));
                finish();
            }

        });

        //Setup the retryButton
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.resetBoard();
                painterTimer.reset();
                painterTextViewMoves.setText("Number of Moves: " + presenter.getNumMoves());
            }
        });
    }

    /**
     * Constructs the 2D array for the imageButtons. Also sets onClickListener for each imageButton
     * inside.
     */
    private void gridCreation() {
        String imageButtonID;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                imageButtonID = "painterImageButton_" + i + j;

                //This step allows easy assignment of each imageButton with a nested loop
                int resID = this.getResources().getIdentifier(imageButtonID, "id", this.getPackageName());
                grid[i][j] = findViewById(resID);
                grid[i][j].setOnClickListener(this); //Response to each button click

                //Generate a random number to decide whether to put white or black here
                double x = Math.random() * 2;
                if (x < 1) {
                    grid[i][j].setImageResource(R.drawable.black_circle);
                    grid[i][j].setContentDescription(getString(R.string.alien_painter_black));
                } else if (x < 2) {
                    grid[i][j].setImageResource(R.drawable.white_circle);
                    grid[i][j].setContentDescription("white");
                }

            }
        }

        //Make sure the grid isn't entirely black at the beginning
        grid[1][1].setImageResource(R.drawable.white_circle);
        grid[1][1].setContentDescription("white");
    }

    /**
     * The event in which any of the imageButtons are pressed
     *
     * @param v The view
     */
    @Override
    public void onClick(View v) {
        //Checks whether the time is still active and whether the win condition has been met
        if (painterTimer.getIsActive() && !checkWinCondition()) {
            if (v.getContentDescription().equals("white")) {
                v.setContentDescription(getString(R.string.alien_painter_black_clicked));
            } else {
                v.setContentDescription(getString(R.string.alien_painter_white_clicked));
            }
            presenter.updateNumMoves();
            painterTextViewMoves.setText("Number of Moves: " + presenter.getNumMoves());

            flipButton();

            if (checkWinCondition()) {
                Toast.makeText(this, "You have won!", Toast.LENGTH_SHORT).show();
                presenter.playerWon();
            }
        }

    }

    /**
     * Checks whether all the player has completed the task. In this case, all the buttons should
     * be displaying a black circle.
     *
     * @return Whether the player has won or not
     */
    private boolean checkWinCondition() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!grid[i][j].getContentDescription().equals("black")) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Flips the imageButtons around the imageButton the user clicked,
     * including the one the user clicked.
     */
    @Override
    public void flipButton() {
        presenter.flipButton();
    }

    /**
     * Starts the timer.
     */
    @Override
    public void startTimer() {
        painterTimer.setActive(true);
        painterTimer.start();
    }

    /**
     * Resets the timer of the game
     */
    @Override
    public void resetTimer() {
        painterTimer.cancel();
        painterTimer.start();
        painterTimer.setActive(true);
    }

    /**
     * Updates the time displayed on screen.
     *
     * @param time the time to update painterTextViewTime with.
     */
    @Override
    public void updateTimer(int time) {
        if (!checkWinCondition()) {
            painterTextViewTime.setText("Time Remaining: " + time);
            presenter.setTimeLeft(time);
        }
    }

    /**
     * Calls this when the timer is done.
     */
    @Override
    public void TimerExpired() {
        //Inform the player they have failed
        Toast.makeText(this, "You have lost!", Toast.LENGTH_SHORT).show();
        //Ask whether the player wants to try again
    }
}
