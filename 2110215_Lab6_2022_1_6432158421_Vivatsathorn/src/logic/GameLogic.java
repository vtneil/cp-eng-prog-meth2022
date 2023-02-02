package logic;

import java.util.Hashtable;

import component.KeyButton;
import component.KeyboardPane;
import component.PlayerPane;
import component.Status;
import component.TimerPane;
import component.TurnPane;
import component.WordCanvas;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class GameLogic {
	private static String[] playerAnswerWord;

	private static String[] playerGuessWord;
	private static Status[][] playerAnswerStatus;

	private static int currentPlayer = 0;

	private static Hashtable[] playerKeyGuess;
	private static Timer[] playerTimer;

	private static boolean isTurnActive;

	private static KeyboardPane keyPane;
	private static TurnPane turnPane;
	private static TimerPane[] timerPane;
	private static PlayerPane[] playerPane;

	private static int[] playerScore;

	public static void init() {
		playerAnswerWord = new String[2];

		playerGuessWord = new String[2];
		playerAnswerStatus = new Status[2][];

		Hashtable<String, Status> p1Guess = new Hashtable<String, Status>();
		Hashtable<String, Status> p2Guess = new Hashtable<String, Status>();

		playerKeyGuess = new Hashtable[] { p1Guess, p2Guess };

		playerTimer = new Timer[] { new Timer(5, 0, 0), new Timer(5, 0, 0) };
		playerScore = new int[] { 0, 0 };

		timerPane = new TimerPane[2];
		playerPane = new PlayerPane[2];

		currentPlayer = 0;

		isTurnActive = false;

		WordStorage.init();

	}

	// ===========================Assignment Portion==============================
	public static void startCountDownTimer(int pl) {

		try {
			runCountDownTimer(pl);
		} catch (InterruptedException e1) {
			return;
		}

	}

	public static void runCountDownTimer(int pl) throws InterruptedException {
//		For debug
//		System.out.println(playerAnswerWord[0]);
//		System.out.println(playerAnswerWord[1]);
//		end for debug
		Runnable runTimer = new Runnable() {
			@Override
			public void run() {
				Timer plTimer = playerTimer[pl];
				plTimer.setStop(false);

				try {
					Thread.sleep(20);
				} catch (InterruptedException e1) {
				}

				while (isTurnActive && !plTimer.isTimerEmpty()) {
					try {

						Thread.sleep(20);

						plTimer.decrementTimer(2);
						timerPane[pl].setTimer(plTimer);

					} catch (InterruptedException e) {
						break;
					}
				}

				plTimer.setStop(true);

				if (plTimer.isTimerEmpty()) {
					Platform.runLater(() -> {
						endGame();
					});
				}
			}
		};

		Thread timerThread = new Thread(runTimer);

		timerThread.start();
	}

	public static void endGame() {

		// Score penalty for the player who lets the time runs out
		if (playerScore[currentPlayer] > 0) {
			playerScore[currentPlayer] -= 1;
		}

		// Update the final score
		playerPane[0].setScore(playerScore[0]);
		playerPane[1].setScore(playerScore[1]);

		String gameResult = "DRAW";

		if (playerScore[0] > playerScore[1]) {
			gameResult = "PLAYER 1 WINS!";
		} else if (playerScore[0] < playerScore[1]) {
			gameResult = "PLAYER 2 WINS!";
		}

		String dialogueString = "Game End!\nThe game result is: " + gameResult + "\n\nDo you want to restart?";
		final String yes = "Yes";
		final String no = "No";

		Alert alert = new Alert(AlertType.NONE, dialogueString, new ButtonType(yes), new ButtonType(no));
		alert.setTitle("Congratulations");
		alert.showAndWait();

		switch (alert.getResult().getText()) {
		case yes:
			resetGame();
			break;
		case no:
			Platform.exit();
			break;
		default:
			break;
		}
	}
	// ===========================================================================

	/*
	 * ===========================================================================
	 * You do not need to touch the rest of these codes to finish the assignment
	 * ===========================================================================
	 */

	public static WordCanvas getPlayerWordCanvas(int player) {
		return playerPane[player].getCurrentWordCanvas();
	}

	public static int getCurrentPlayer() {
		return currentPlayer;
	}

	public static void setCurrentPlayer(int cP) {
		currentPlayer = cP;
	}

	public static String getAnswerWord(int pl) {
		return playerAnswerWord[pl];
	}

	public static void setAnswerWord(int pl, String answer) {
		playerAnswerWord[pl] = answer;
	}

	public static void beginTurns(int pl) {
		if (getAnswerWord(pl) == null) {
			playerPane[pl].setPreviousWord("", new Status[] {});
			setNewAnswer(pl);
		} else {
			playerPane[pl].setPreviousWord(playerGuessWord[pl], playerAnswerStatus[pl]);
		}

		startCountDownTimer(pl);

		timerPane[pl].setButtonDisable(true);
		turnPane.setSubText("Start");

		isTurnActive = true;

		WordCanvas wc = getPlayerWordCanvas(pl);
		wc.resetBox();

		keyPane.enableKeyboard();
		keyPane.setKeyStatus(getPlayerKeyStatus(getCurrentPlayer()));
	}

	public static void endTurns() {

		// If all positions are correct, reset the answer list
		boolean isCorrect = true;
		for (int i = 0; i < 5; i++) {
			if (playerAnswerStatus[currentPlayer][i] != Status.CORRECT) {
				isCorrect = false;
				break;
			}
		}

		if (isCorrect) {
			playerAnswerWord[currentPlayer] = null;
			playerScore[currentPlayer] += 1;
			playerPane[currentPlayer].setScore(playerScore[currentPlayer]);
		}

		currentPlayer += 1;
		currentPlayer %= 2;

		prepareTurns(currentPlayer);
	}

	public static void resetGame() {
		playerAnswerWord = new String[2];

		playerGuessWord = new String[2];
		playerAnswerStatus = new Status[2][];

		Hashtable<String, Status> p1Guess = new Hashtable<String, Status>();
		Hashtable<String, Status> p2Guess = new Hashtable<String, Status>();

		playerKeyGuess = new Hashtable[] { p1Guess, p2Guess };

		playerTimer = new Timer[] { new Timer(5, 0, 0), new Timer(5, 0, 0) };
		playerScore = new int[] { 0, 0 };

		currentPlayer = 0;

		isTurnActive = false;

		// Reset the timer, score, and word
		for (int i = 0; i < 2; i++) {
			timerPane[i].setTimer(playerTimer[i]);
			playerPane[i].setScore(playerScore[i]);
			playerPane[i].setPreviousWord("", new Status[] {});
			WordCanvas wc = getPlayerWordCanvas(i);
			wc.resetBox();
		}

		keyPane.disableKeyboard();

		prepareTurns(0);
	}

	public static void prepareTurns(int pl) {
		turnPane.setPlayer(pl + 1);
		turnPane.setSubText("Ready");

		timerPane[pl].setButtonDisable(false);
	}

	public static void setNewAnswer(int pl) {
		clearPlayerLetterStatus(pl);
		setAnswerWord(pl, WordStorage.getRandomWord());
		// System.out.println("The answer is "+getAnswerWord(pl));
	}

	public static void addLetterToCurrentPlayer(String letter) {
		WordCanvas wc = getPlayerWordCanvas(currentPlayer);

		switch (letter) {
		case KeyButton.KEY_BKSPC:
			wc.removeLetter();
			wc.updateCanvas();
			break;
		case KeyButton.KEY_ENTER:
			verifyCurrentPlayerWord();
			break;
		default:
			wc.addLetter(letter);
			wc.updateCanvas();
		}
	}

	public static void removeLetterToCurrentPlayer() {
		WordCanvas wc = getPlayerWordCanvas(currentPlayer);
		wc.removeLetter();
		wc.updateCanvas();
	}

	public static void verifyCurrentPlayerWord() {
		WordCanvas wc = getPlayerWordCanvas(currentPlayer);
		if (wc.getCurrentLetter() != 5) {
			return;
		}

		String answer = getAnswerWord(currentPlayer);
		String guess = wc.getGuessWord();

		if (!WordStorage.isWordInList(guess)) {
			return;
		}

		isTurnActive = false;

		Status[] verifyStatusList = WordValidator.checkWord(guess, answer);
		addPlayerKeyStatus(currentPlayer, guess, verifyStatusList);

		playerGuessWord[currentPlayer] = guess;
		playerAnswerStatus[currentPlayer] = verifyStatusList;

		keyPane.disableKeyboard();

		wc.flipWord(verifyStatusList);
	}

	public static void addPlayerKeyStatus(int pl, String s, Status st) {
		playerKeyGuess[pl].put(s, st);
	}

	public static void addPlayerKeyStatus(int pl, String s, Status[] st) {
		if (s.length() != 5) {
			return;
		}

		for (int i = 0; i < 5; i++) {
			String c = "" + s.charAt(i);
			Status currentState = getPlayerKeyStatus(pl).get(c);
			if (currentState == null || currentState.ordinal() < st[i].ordinal()) {
				addPlayerKeyStatus(pl, c, st[i]);
			}
		}
	}

	public static Hashtable<String, Status> getPlayerKeyStatus(int pl) {
		return playerKeyGuess[pl];
	}

	public static void clearPlayerLetterStatus(int pl) {
		playerKeyGuess[pl].clear();
	}

	public static KeyboardPane getKeyPane() {
		return keyPane;
	}

	public static void setKeyPane(KeyboardPane keyPane) {
		GameLogic.keyPane = keyPane;
	}

	public static TurnPane getTurnPane() {
		return turnPane;
	}

	public static void setTurnPane(TurnPane turnPane) {
		GameLogic.turnPane = turnPane;
	}

	public static TimerPane getTimerPane(int pl) {
		return timerPane[pl];
	}

	public static void setTimerPane(int pl, TimerPane timerPane) {
		GameLogic.timerPane[pl] = timerPane;
	}

	public static PlayerPane getPlayerPane(int pl) {
		return playerPane[pl];
	}

	public static void setPlayerPane(int pl, PlayerPane playerPane) {
		GameLogic.playerPane[pl] = playerPane;
	}

	public static Timer getPlayerTimer(int pl) {
		return playerTimer[pl];
	}

	public static void setPlayerTimer(int pl, Timer playerTimer) {
		GameLogic.playerTimer[pl] = playerTimer;
	}
}
