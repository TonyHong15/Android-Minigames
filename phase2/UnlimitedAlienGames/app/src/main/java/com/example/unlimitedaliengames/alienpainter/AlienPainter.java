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


public class AlienPainter extends AppCompatActivity implements View.OnClickListener, AlienPainterView {

    /**
     * The English Constants for what to display on the screen
     */
    static final String SCOREBOARD_STATUS = "You Have ";
    static final String NUM_MOVES = "Number of Moves: ";
    static final String TIME_LEFT = "Time Remaining: ";
    static final String POINTS = "Points: ";
    static final String WIN = "You Have Won!";
    static final String LOSS = "You Have Lost!";

    /**
     * The Chinese Constants for what to display on the screen
     */
    static final String NUM_MOVES_CHINESE = "步数: ";
    static final String TIME_LEFT_CHINESE = "剩余时间: ";
    static final String POINTS_CHINESE = "分数:";
    static final String WIN_CHINESE = "你赢了！";
    static final String LOSS_CHINESE = "你输了！";

    /**
     * A constant used as a keyword to pass the isEnglish variable through intent
     */
    static final String LANGUAGE = "LANGUAGE";

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
     * Holds the textView for the amount of points the user has earned
     */
    private TextView painterTextViewPoints;

    /**
     * Holds the textView for the instructions of the game
     */
    private TextView painterTextViewInstructions;

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
     * Holds the button that switches the view to the scoreboard.
     */
    private Button scoreboardButton;

    /**
     * Holds the current user of this game
     */
    private User currUser;

    /**
     * used to check whether the user has won or not
     */
    private boolean isVictorious;

    /**
     * This method is automatically called when the app view switches to AlienPainter.
     * The method extracts the User that logged in from the intent, and uses it to store player
     * statistics.
     * The method initializes AlienPainterTimer and AlienPainterPresenter.
     * The method calls the gridCreation method that creations a 2D array of buttons
     * The method calls the buttonSetup method that sets up the buttons other than the 2D array
     * The method sends a toast that informs the player of how to play the game.
     *
     * @param savedInstanceState the instanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alien_painter);

        Intent intent = getIntent();
        currUser = (User) intent.getSerializableExtra(LoginActivity.PASS_USER);

        painterTextViewMoves = findViewById(R.id.painterTextView1);
        painterTextViewTime = findViewById(R.id.painterTextView2);
        painterTextViewPoints = findViewById(R.id.painterTextView3);
        painterTextViewInstructions = findViewById(R.id.painterInstructionsView);

        isVictorious = false;
        isEnglish = true;

        //Initialize the Timer
        painterTimer = new AlienPainterTimer(this);

        //Setup the exitButton, languageButton, scoreboardButton and retryButton
        buttonSetup();

        //Construct the 2D imageButton array
        gridCreation();

        //Game Instructions
        if (isEnglish) {
            painterTextViewInstructions.setText(R.string.alien_painter_instructions);
        } else {
            painterTextViewInstructions.setText(R.string.alien_painter_instructions_chinese);
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
     * When scoreboardButton is clicked, the view switches to the scoreboard.
     */
    private void buttonSetup() {
        exitButton = findViewById(R.id.exitButton);
        retryButton = findViewById(R.id.retryButton);
        languageButton = findViewById(R.id.languageButton);
        scoreboardButton = findViewById(R.id.scoreBoardButton);

        //Setup the exitButton
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), MainActivity.class));
                presenter.resetGame();
                painterTimer.reset();
                finish();
            }

        });

        //Setup the retryButton
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.resetGame();
                painterTimer.reset();
                updateStats();
            }
        });

        //Setup the languageButton
        languageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isEnglish = !isEnglish;
                updateLanguage();
                updateStats();
            }
        });

        //Setup the scoreboardButton
        scoreboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //calls the scoreboard method
                painterTimer.cancel();
                scoreboard();
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
     * This method calculates points earned by player for the move they made
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

            //Update the number of moves made by the player
            presenter.updateNumMoves();

            //Do the actual button flipping
            flipButton();

            //Calculate points earned
            presenter.calculatePoints();

            //Display the information calculated
            updateStats();


            if (checkWinCondition()) {
                if (isEnglish) {
                    Toast.makeText(this, WIN, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, WIN_CHINESE, Toast.LENGTH_SHORT).show();
                }
                presenter.playerWon();

                isVictorious = true;
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
    }

    /**
     * A helper function used to update the language displayed on the view when the user
     * switches the language.
     */
    private void updateLanguage() {
        if (isEnglish) {
            languageButton.setText(R.string.alien_painter_language_chinese);
            exitButton.setText(R.string.alien_painter_exit);
            retryButton.setText(R.string.alien_painter_retry);
            scoreboardButton.setText(R.string.alien_painter_scoreboard_button);
            painterTextViewInstructions.setText(R.string.alien_painter_instructions);
        } else {
            languageButton.setText(R.string.alien_painter_language_english);
            exitButton.setText(R.string.alien_painter_exit_chinese);
            retryButton.setText(R.string.alien_painter_retry_chinese);
            scoreboardButton.setText(R.string.alien_painter_scoreboard_button_chinese);
            painterTextViewInstructions.setText(R.string.alien_painter_instructions_chinese);
        }
    }

    /**
     * A helper function used to update the statistics displayed on the view to the user
     */
    private void updateStats() {
        if (isEnglish) {
            painterTextViewMoves.setText(NUM_MOVES + presenter.getNumMoves());
            painterTextViewTime.setText(TIME_LEFT + presenter.getTimeLeft());
            painterTextViewPoints.setText(POINTS + presenter.getPoints());
        } else {
            painterTextViewTime.setText(TIME_LEFT_CHINESE + presenter.getTimeLeft());
            painterTextViewMoves.setText(NUM_MOVES_CHINESE + presenter.getNumMoves());
            painterTextViewPoints.setText(POINTS_CHINESE + presenter.getPoints());
        }
    }

    /**
     * Calls this method when the game ends
     */
    private void scoreboard() {
        Intent intent = new Intent(this, AlienPainterScoreboardActivity.class);
        intent.putExtra(SCOREBOARD_STATUS, isVictorious);
        intent.putExtra(POINTS, presenter.getPoints());
        intent.putExtra(NUM_MOVES, presenter.getNumMoves());
        intent.putExtra(TIME_LEFT, presenter.getTimeLeft());
        intent.putExtra(LANGUAGE, isEnglish);
        startActivity(intent);
        finish();
    }
}