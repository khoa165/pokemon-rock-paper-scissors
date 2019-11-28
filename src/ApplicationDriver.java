
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class ApplicationDriver extends PApplet {

  private Random rand;
  PFont boldFont;

  private PImage backgroundImage; // Background image.
  private PImage myPetImage;
  private PImage yourPetImage;
  private PImage myBall;
  private PImage yourBall;

  private String myPet;
  private String yourPet;
  private String result;

  private ArrayList<Button> buttonCollection;
  private Button startButton;
  private Button ruleButton;
  private Button charmanderButton;
  private Button squirtleButton;
  private Button bulbasaurButton;
  private Button readyButton;
  private String[] petOption;

  private boolean pokemonClicked;
  private boolean gameActivated;
  private boolean readyClicked;
  private boolean opponentActivated;

  private int timeControl;

  public static void main(String[] args) {
    PApplet.main("ApplicationDriver");
  }

  @Override
  public void settings() {
    size(1050, 570);
  }

  @Override
  public void setup() {
    this.graphicSetup();
    this.addButtons();
    this.generalSetup();
    this.boldFont = createFont("Times New Roman-Bold", 16, true);
  }

  @Override
  public void draw() {
    this.image(this.backgroundImage, 0, 30);
    this.rect(0, 0, 1050, 30);
    this.drawButtons();
    this.drawPokemon();
    this.winOrLose();
  }

  private void graphicSetup() {
    this.getSurface().setTitle("Pokemon Rock Paper Scissors, created by HARRY LE");
    this.textAlign(ApplicationDriver.CENTER, ApplicationDriver.CENTER);
    this.rectMode(ApplicationDriver.CORNERS);
    this.focused = true; // Confirms that our Processing program is "focused," meaning that it is
    // active and will accept mouse or keyboard input.

  }

  private void addButtons() {
    this.buttonCollection = new ArrayList<Button>();
    this.startButton = new Button(this, "Start", 75, 15);
    this.ruleButton = new Button(this, "Rule", 225, 15);
    this.charmanderButton = new Button(this, "Charmander", 375, 15);
    this.squirtleButton = new Button(this, "Squirtle", 525, 15);
    this.bulbasaurButton = new Button(this, "Bulbasaur", 675, 15);
    this.readyButton = new Button(this, "Ready", 500, 350);

    this.buttonCollection.add(startButton);
    this.buttonCollection.add(ruleButton);
    this.buttonCollection.add(charmanderButton);
    this.buttonCollection.add(squirtleButton);
    this.buttonCollection.add(bulbasaurButton);
  }

  private void generalSetup() {
    this.rand = new Random();

    this.backgroundImage = this.loadImage("images" + File.separator + "Background.jpg");
    this.myBall = this.loadImage("images" + File.separator + "MyBall.png");
    this.yourBall = this.loadImage("images" + File.separator + "YourBall.png");
    this.myPetImage = null;
    this.yourPetImage = null;

    this.myPet = null;
    this.yourPet = null;
    this.result = null;

    this.gameActivated = false;
    this.pokemonClicked = false;
    this.readyClicked = false;
    this.opponentActivated = false;

    this.timeControl = 1;

    this.petOption = new String[] {"Charmander", "Squirtle", "Bulbasaur"};
  }

  private void drawButtons() {
    for (int i = 0; i < this.buttonCollection.size(); ++i) {
      this.buttonCollection.get(i).draw();
    }
  }

  private void drawPokemon() {
    if (this.pokemonClicked) {
      this.image(this.myBall, 200, 350);
      this.image(this.yourBall, 600, 170);
      this.readyButton.draw();
    } else if (this.readyClicked) {
      this.myPetImage = this.loadImage("images" + File.separator + "My" + this.myPet + ".png");
      if (this.opponentActivated) {
        this.opponentActivated = false;
        this.yourPet = this.petOption[this.rand.nextInt(3)];
      }
      this.yourPetImage =
          this.loadImage("images" + File.separator + "Your" + this.yourPet + ".png");
      this.image(this.myPetImage, 220, 300);
      this.image(this.yourPetImage, 610, 175);
    }
  }

  private void winOrLose() {
    if (this.readyClicked) {
      if (this.timeControl % 80 == 0) {
        if (this.myPet.equals(this.yourPet)) {
          this.result = "HEY! WE HAVE A TIE!";
        } else if (this.myPet.equals("Charmander")) {
          if (this.yourPet.equals("Squirtle")) {
            this.result = "SORRY! YOU LOSE!";
          } else {
            this.result = "CONGRATS! YOU WIN!";
          }
        } else if (this.myPet.equals("Squirtle")) {
          if (this.yourPet.equals("Bulbasaur")) {
            this.result = "SORRY! YOU LOSE!";
          } else {
            this.result = "CONGRATS! YOU WIN!";
          }
        } else if (this.myPet.equals("Bulbasaur")) {
          if (this.yourPet.equals("Charmander")) {
            this.result = "SORRY! YOU LOSE!";
          } else {
            this.result = "CONGRATS! YOU WIN!";
          }
        }
      } else {
        this.timeControl++;
        if (this.timeControl == 80) {
          this.timeControl = 0;
        }
      }
    }
    if (this.result != null) {
      this.textFont(this.boldFont, 30);
      this.fill(0);
      this.text(this.result, 520, 330);
      this.textFont(this.boldFont, 12);
    }
  }

  @Override
  public void mousePressed() {
    for (int i = 0; i < this.buttonCollection.size(); ++i) {
      if (this.buttonCollection.get(i).isMouseOver()) {
        if (i == 0) {
          this.setup();
          this.draw();
          this.gameActivated = true;
        } else if (i == 1) {

        } else if (this.gameActivated) {
          this.myPet = this.buttonCollection.get(i).getLabel();
          this.pokemonClicked = true;
        }
      }
    }
    if (this.readyButton.isMouseOver()) {
      this.pokemonClicked = false;
      this.gameActivated = false;
      this.readyClicked = true;
      this.opponentActivated = true;
    }
  }
}
