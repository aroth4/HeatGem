package edu.ycp.cs320.heatgem.client;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.CanvasElement;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.MouseListener;

import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.widget.client.TextButton;

import edu.ycp.cs320.heatgem.shared.Battle;
import edu.ycp.cs320.heatgem.shared.Game;
import edu.ycp.cs320.heatgem.shared.Logic;
import edu.ycp.cs320.heatgem.shared.Player;

public class GameUI extends Composite {

	public int MouseX;
	public int MouseY;
	private Canvas buffer;
	private Context2d bufCtx;
	private Canvas canvas;
	private Context2d ctx;
	private Timer timer;
	private Image background, GameWin, GameLoss;
	private Image PlayerHealth;
	private Image EnemyHealth;
	private Image PlayerFace;
	private Image EnemyFace;
	private Image Attack;
	private Image AttackSelected;
	private Image Heal;
	private Image HealSelected;
	private Image Defeat;
	private Player player1;
	private Player player2;
	private Battle BattleState;
	// The game object contains all of the game state data.
	private Game game;
	private int MilliTime;
	private int SecondTime;

	public GameUI() {

		// FocusPanel
		final FocusPanel panel = new FocusPanel();
		// LayoutPanel layoutPanel = new LayoutPanel();
		panel.setSize("800px", "480px");

		// "buffer" canvas
		this.buffer = Canvas.createIfSupported();
		buffer.setSize(Game.Width + "px", Game.Height + "px");
		buffer.setCoordinateSpaceWidth(Game.Width);
		buffer.setCoordinateSpaceHeight(Game.Height);
		this.bufCtx = buffer.getContext2d();

		// The visible canvas
		this.canvas = Canvas.createIfSupported();
		canvas.setSize(Game.Width + "px", Game.Height + "px");
		canvas.setCoordinateSpaceWidth(Game.Width);
		canvas.setCoordinateSpaceHeight(Game.Height);
		this.ctx = canvas.getContext2d();
		panel.add(canvas);

		initWidget(panel);

		this.timer = new Timer() {
			@Override
			public void run() {
				Draw();
				if (BattleState.battleState() == 0) {
					MilliTime++; // Framerate at 10 frames per second
					if (MilliTime % 100 == 0) { // Incrememnt timer by seconds ONLY if game is in session
						SecondTime++;
						MilliTime = 0;
					}
				}
			}
		};
		
//		this.scoreTimer = new Timer() {
//			@Override
//			public void run() {
//
//			}
//		};

		canvas.addMouseMoveHandler(new MouseMoveHandler() {
			@Override
			public void onMouseMove(MouseMoveEvent event) {
				// GWT.log("Mouse moved: x="+ event.getX()+ ", y=" +
				// event.getY());
				MouseX = event.getX();
				MouseY = event.getY();
			}

		});

		canvas.addMouseDownHandler(new MouseDownHandler() {
			@Override
			public void onMouseDown(MouseDownEvent event) {
				if (BattleState.battleState() == 0) { // If No one has one,
														// allow the game to
														// continue
					if ((MouseX > 380 && MouseX < 455)
							&& (MouseY > 360 && MouseY < 390)) {
						// Attack
						GWT.log("Health before attack(2):"
								+ player2.getHealth());
						Logic.doBattle(player1, player2);

						GWT.log("Health after attack(2):" + player2.getHealth());

						GWT.log("" + player1.getHealth());
						GWT.log("" + player2.getHealth());
					} else if ((MouseX > 380 && MouseX < 455)
							&& (MouseY > 410 && MouseY < 440)) {
						// Heal
						GWT.log("Health before heal(1):" + player1.getHealth());

						Logic.doHeal(player1, player2);

						GWT.log("Health after heal(1):" + player1.getHealth());

						GWT.log("" + player1.getHealth());
						GWT.log("" + player2.getHealth());
					}
				}
			}
		});

	}

	public void onMouseDown(Widget sender, int x, int y) {

	}

	public void onMouseEnter(Widget sender) {

	}

	public void onMouseLeave(Widget sender) {

	}

	public void onMouseUp(Widget sender, int x, int y) {

	}

	public void onMouseMove(Widget sender, int x, int y) {
		x = MouseX;
		y = MouseY;
	}

	public void startGame() {
		// get background and sprite images that will be used for painting
		background = HeatGem.getImage("RoughBattle.jpg");
		PlayerHealth = HeatGem.getImage("TBAR.jpg");
		EnemyHealth = HeatGem.getImage("TBAR.jpg");
		PlayerFace = HeatGem.getImage("FullHealth.png");
		EnemyFace = HeatGem.getImage("YellowHealth.png");
		Attack = HeatGem.getImage("Attack.png");
		Heal = HeatGem.getImage("Heal.png");
		Defeat = HeatGem.getImage("Defeat.png");
		AttackSelected = HeatGem.getImage("AttackSelected.png");
		HealSelected = HeatGem.getImage("HealSelected.png");
		GameWin = HeatGem.getImage("BattleWin.jpg");
		GameLoss = HeatGem.getImage("BattleLoss.jpg");

		game = new Game();
		player1 = new Player("Player");
		player2 = new Player("Monster");
		BattleState = new Battle(player1, player2);
		// Add a listener for mouse motion.
		// Each time the mouse is moved, clicked, released, etc. the
		// handleMouseMove method
		// will be called.
		timer.scheduleRepeating(1000 / 100); //DeciSeconds
	}

	// protected void handleTimerEvent() {
	// // You should not need to change this method.
	// game.timerTick();
	//
	// }

	protected void Draw() {
		// Draw onto buffer

		// Draw background

		if (BattleState.battleState() == 0) {
			// Draw background
			bufCtx.drawImage((ImageElement) background.getElement().cast(), 0,
					0);
			// if (p1Health > 50){
			int player1Health = player1.getHealth();
			if (player1Health > 50) {
				bufCtx.setFillStyle("green");
			} else if (player1Health <= 50 && player1Health > 25) {
				bufCtx.setFillStyle("yellow");
			} else {
				bufCtx.setFillStyle("red");
			}
			// Draw PlayerHealth Bar that scales based on health size
			bufCtx.fillRect(30, 430, (double) player1Health * 3, 25);

			int player2Health = player2.getHealth();
			if (player2Health > 50) {
				bufCtx.setFillStyle("green");
			} else if (player2Health <= 50 && player2Health > 25) {
				bufCtx.setFillStyle("yellow");
			} else {
				bufCtx.setFillStyle("red");
			}

			// Draw EnemyHealth Bar that scales based on health size
			bufCtx.fillRect(450, 35, (double) player2Health * 3, 25);
			// Draw Sprite for character
			bufCtx.drawImage((ImageElement) PlayerFace.getElement().cast(), 50,
					200);
			// Draw Sprite for Enemy
			bufCtx.drawImage((ImageElement) EnemyFace.getElement().cast(), 580,
					100);
			if ((MouseX > 380 && MouseX < 455)
					&& (MouseY > 360 && MouseY < 390)) {
				// Draw AttackSelected Button
				bufCtx.drawImage((ImageElement) AttackSelected.getElement()
						.cast(), 380, 360);
			} else {
				// Draw Attack Button
				bufCtx.drawImage((ImageElement) Attack.getElement().cast(),
						380, 360);
			}
			if ((MouseX > 380 && MouseX < 455)
					&& (MouseY > 410 && MouseY < 440)) {
				// Draw HealSelected Button
				bufCtx.drawImage((ImageElement) HealSelected.getElement()
						.cast(), 380, 410);
			} else {
				// Draw Heal Button
				bufCtx.drawImage((ImageElement) Heal.getElement().cast(), 380,
						410);
			}
			//Set font to red
			bufCtx.setFillStyle("red");
			//Sets font to sans-serif
			bufCtx.setFont("bold 16px sans-serif"); 		
			//Prints Player 1 Health
			bufCtx.fillText((player1Health + " / 100"), 30, 430); 	
			//Prints Player 2 Health
			bufCtx.fillText(player2Health + " / 100", 450, 35);   			
			bufCtx.setFillStyle("red");										
			bufCtx.setFont("bold 18px sans-serif");	
			//Prints current game time
			bufCtx.fillText(Integer.toString(SecondTime) +":" + Integer.toString(MilliTime), 700, 378);				
		} else if (BattleState.battleState() == 1) {
			// Draw loss image
			bufCtx.drawImage((ImageElement) GameLoss.getElement().cast(), 0, 0);
		} else {
			// Draw win image
			bufCtx.drawImage((ImageElement) GameWin.getElement().cast(), 0, 0);
		}
		// Copy buffer onto main canvas
		ctx.drawImage((CanvasElement) buffer.getElement().cast(), 0, 0);

	}

}
