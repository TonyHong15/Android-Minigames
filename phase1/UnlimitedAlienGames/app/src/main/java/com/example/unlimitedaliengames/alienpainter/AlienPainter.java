package com.example.unlimitedaliengames.alienpainter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unlimitedaliengames.LoginActivity;
import com.example.unlimitedaliengames.MainActivity;
import com.example.unlimitedaliengames.R;
import com.example.unlimitedaliengames.userdata.User;

import java.util.concurrent.TimeUnit;


public class AlienPainter extends AppCompatActivity implements View.OnClickListener, AlienPainterView {

    /**
     * The English Constants for what to display on the screen
     */
    static final String NUM_MOVES = "Number of Moves: ";
    static final String TIME_LEFT = "Time Remaining: ";
    static final String WIN = "You Have Won!";
    static final String LOSS = "You Have Lost!";
    static final String INSTRUCTIONS_ENGLISH = "Clicking a button changes its and all " +
            "nearby buttons' colour. Click on each button to try to get all of them to turn black";

    /**
     * The Chinese Constants for what to display on the screen
     */
    static final String NUM_MOVES_CHINESE = "步数: ";
    static final String TIME_LEFT_CHINESE = "剩余时间: ";
    static final String WIN_CHINESE = "你赢了！";
    static final String LOSS_CHINESE = "你输了！";
    static final String INSTRUCTIONS_CHINESE = "点击一个按钮时会转变它和它周围的按钮的颜色，" +
            "通过点击按钮来把它们全部变成黑色";

    /**
     * The boolean variable used to check whether the player has turned on Chinese Language
     */
    private boolean isEnglish;

    /**
     * The 2D Array of imageButtons
     */
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
     * Refers to the button in alien painter layout that switches the language of the game
     */
    private Button languageButton;

    /**
     * Holds the current user of this game
     */
    private User currUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alien_painter);

        Intent intent = getIntent();
        currUser = (User) intent.getSerializableExtra(LoginActivity.PASS_USER);

        painterTextViewMoves = findViewById(R.id.painterTextView1);
        painterTextViewTime = findViewById(R.id.painterTextView2);

        isEnglish = true;

        //Initialize the Timer
        painterTimer = new AlienPainterTimer(this);

        //Setup the exitButton, languageButton and retryButton
        buttonSetup();

        //Construct the 2D imageButton array
        gridCreation();

        //Game Instructions
        if (isEnglish) {
            Toast.makeText(this, INSTRUCTIONS_ENGLISH, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, INSTRUCTIONS_CHINESE, Toast.LENGTH_LONG).show();
        }
        //Start the Timer
        startTimer();

        //Initialize the Presenter
        presenter = new AlienPainterPresenter(this, painterTimer, grid,
                new AlienPainterFunctions(this, grid, this), this, currUser);
    }

    /**
     * Setup for the exitButton and retryButton.
     * When exitButton is clicked, the game goes back to MainActivity.
     * When retryButton is clicked, the game resets its timer and generates a new board.
     * When languageButton is clicked, the game changes its UI language to Chinese.
     */
    private void buttonSetup() {
        exitButton = findViewById(R.id.exitButton);
        retryButton = findViewById(R.id.retryButton);
        languageButton = findViewById(R.id.languageButton);

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
                if (isEnglish) {
                    painterTextViewMoves.setText(NUM_MOVES + presenter.getNumMoves());
                } else {
                    painterTextViewMoves.setText(NUM_MOVES_CHINESE + presenter.getNumMoves());
                }
            }
        });

        //Setup the languageButton
        languageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isEnglish = !isEnglish;
                updateLanguage();
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
     * The event in which any of the imageButtons are pressed.
     * This method will call the presenter to update the number of moves the player has made.
     * This method will flip the buttons according to whichever one the player has pressed.
     * This method wil check whether the player has won the game and call the playerWon
     * method in the presenter if so.
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

            if (isEnglish) {
                painterTextViewMoves.setText(NUM_MOVES + presenter.getNumMoves());
            } else {
                painterTextViewMoves.setText(NUM_MOVES_CHINESE + presenter.getNumMoves());
            }

            flipButton();

            if (checkWinCondition()) {
                if (isEnglish) {
                    Toast.makeText(this, WIN, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, WIN_CHINESE, Toast.LENGTH_SHORT).show();
                }
                presenter.playerWon();

                gameOver(true);
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
            if (isEnglish) {
                painterTextViewTime.setText(TIME_LEFT + time);
            } else {
                painterTextViewTime.setText(TIME_LEFT_CHINESE + time);
            }
            presenter.setTimeLeft(time);
        }
    }

    /**
     * Calls this when the timer is done.
     */
    @Override
    public void TimerExpired() {
        //Inform the player they have failed
        if (isEnglish) {
            Toast.makeText(this, LOSS, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, LOSS_CHINESE, Toast.LENGTH_SHORT).show();
        }

        gameOver(false);


        //Ask whether the player wants to try again
    }

    /**
     * A helper function used to update the language displayed on the map when the use
     * switches the language.
     */
    private void updateLanguage() {
        if (isEnglish) {
            languageButton.setText(R.string.alien_painter_language_chinese);
            exitButton.setText(R.string.alien_painter_exit);
            retryButton.setText(R.string.alien_painter_retry);
        } else {
            languageButton.setText(R.string.alien_painter_language_english);
            exitButton.setText(R.string.alien_painter_exit_chinese);
            retryButton.setText(R.string.alien_painter_retry_chinese);
        }
    }

    /**
     * Calls this method to start the game over activity
     */
    private void gameOver(boolean won) {
        Intent intent = new Intent(this, AlienPainterGameOverActivity.class);
        intent.putExtra(NUM_MOVES, presenter.getNumMoves());
        intent.putExtra(TIME_LEFT, presenter.getTimeLeft());
        startActivity(intent);
        finish();
    }
}