/**
 * Game.java
 * @author Abdullah Mohammad
 */
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;


public class Game {
	private String title;
	private String developer;
	private String id;
	private String genre;
	private Date releaseDate;
	private String summary;
    private ArrayList<String> platforms;
    private double price;
	private int stock;

    /**** CONSTRUCTORS ****/

    /**
     * Default constructor for Game
     * 
     * @postcondition a new Game object is created with default values
     */
    public Game() {
        this.title = "Untitled";
        this.developer = "Unknown";
        this.id = "000000";
        this.genre = "None";
        this.releaseDate = new Date(0, 0, 0);
        this.summary = "None";
        this.platforms = new ArrayList<String>();
        this.price = 0.0;
        this.stock = 0;
    }

    /**
     * Constructor for Game
     * 
     * @param title
     * @postcondition a new Game object is created with the parameter title and developer
     */
    public Game(String title, String developer) {
        this.title = title;
        this.developer = developer;
        this.id = "000000";
        this.genre = "None";
        this.releaseDate = new Date(0, 0, 0);
        this.summary = "None";
        this.platforms = new ArrayList<String>();
        this.price = 0.0;
        this.stock = 0;
    }

    /**
     * Constructor for Game
     * 
     * @param title
     * @param developer
     * @param id
     * @param genre
     * @param releaseDate
     * @param price
     * @param stock
     * @param summary
     * @param platforms
     * @param price
     * @param stock
     * @postcondition a new Game object is created with the given values
     */
    public Game(String title, String developer, String id, String genre, Date releaseDate, String summary, ArrayList<String> platforms, double price, int stock){
        this.title = title;
        this.developer = developer;
        this.id = id;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.summary = summary;
        this.platforms = platforms;
        this.price = price;
        this.stock = stock;
    }

    /**** ACCESSORS ****/

    /**
     * Accessor for title
     * 
     * @return the title of the game
     */
    public String getTitle() {
        return title;
    }

    /**
     * Accessor for developer
     * 
     * @return the developer of the game
     */
    public String getDeveloper() {
        return developer;
    }

    /**
     * Accessor for id
     * 
     * @return the id of the game
     */
    public String getId() {
        return id;
    }

    /**
     * Accessor for genre
     * 
     * @return the genre of the game
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Accessor for releaseDate
     * 
     * @return the release date of the game
     */
    public Date getReleaseDate() {
        return releaseDate;
    }

    /**
     * Accessor for summary
     * 
     * @return the summary of the game
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Accessor for platforms
     * 
     * @return the platforms of the game
     */
    public ArrayList<String> getPlatforms() {
        return platforms;
    }

    public String getPlatformsString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < platforms.size(); i++) {
            String platform = platforms.get(i);
            sb.append(platform);
            if (i != platforms.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    /**
     * Accessor for price
     * @return the price of the game
     */
    public double getPrice() {
        return price;
    }

    /**
     * Accessor for stock
     * @return the stock of the game
     */
    public int getStock() {
        return stock;
    }

    public String getFormattedPrice() {
        DecimalFormat df = new DecimalFormat("#.00");
        return "$" + df.format(price);
    }


    /**** MUTATORS ****/

    /**
     * Mutator for title
     * 
     * @param title
     * @postcondition the title of the game is set to the given value
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Mutator for developer
     * 
     * @param developer
     * @postcondition the developer of the game is set to the given value
     */
    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    /**
     * Mutator for id
     * 
     * @param id
     * @postcondition the id of the game is set to the given value
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Mutator for genre
     * 
     * @param genre
     * @postcondition the genre of the game is set to the given value
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Mutator for releaseDate
     * 
     * @param releaseDate
     * @postcondition the release date of the game is set to the given value
     */
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * Mutator for summary
     * 
     * @param summary
     * @postcondition the summary of the game is set to the given value
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * Mutator for platforms
     * 
     * @param platforms
     * @postcondition the platforms of the game are set to the given value
     */
    public void setPlatforms(ArrayList<String> platforms) {
        this.platforms = platforms;
    }

    /**
     * Mutator for price
     * @param price
     * @postcondition the price is set to the given value
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Mutator for stock
     * @param stock
     * @postcondition the stock is set to the given value
     */
    public void setStock(int stock) {
        this.stock = stock;
    }


    /**** ADDITIONAL OPERATIONS ****/

    /**
     * Returns the game information
     * 
     * @return the game information
     */
    @Override
    public String toString() {
        return title + "\n" +
            developer + "\n" +
            id + "\n" +
            genre + "\n" +
            releaseDate.toString() + "\n" +
            summary + "\n" +
            getPlatformsString() + "\n" +
            getFormattedPrice() + "\n" +
            getStock() + "\n";
    }

    /**
     * Returns the game information
     *
     * @return the game information
     */
    public String toGameInfoString() {
        return "Title: " + title + "\n" +
                "Developer: " + developer + "\n" +
                "ID: " + id + "\n" +
                "Genre: " + genre + "\n" +
                "Release Date: " + releaseDate.toString() + "\n" +
                "Summary: " + summary + "\n" +
                "Platforms: " + getPlatformsString() + "\n" +
                "Price: " + getFormattedPrice() + "\n"
        ;
    }

    /**
     * Compares two Game objects to determine if they are equal
     * 
     * @param o
     * @return whether the two Game objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Game)) {
            return false;
        } else {
            Game g = (Game) o;
            return (
                title.equals(g.getTitle()) &&
                developer.equals(g.getDeveloper()) &&
                id.equals(g.getId()) &&
                genre.equals(g.getGenre()) &&
                releaseDate.equals(g.getReleaseDate()) &&
                summary.equals(g.getSummary()) &&
                price == g.getPrice() &&
                stock == g.getStock()
            );
        }
    }
} // end class Game

class TitleComparator implements Comparator<Game> {
    /**
     * Compares two Game objects by title
     * 
     * @param g1
     * @param g2
     * @return the comparison
     */
    @Override
    public int compare(Game g1, Game g2) {
        return (g1.getTitle().toLowerCase()).compareTo(g2.getTitle().toLowerCase());
    }
} // end class TitleComparator

class PriceComparator implements Comparator<Game> {
    /**
     * Compares two Game objects by price
     * 
     * @param g1
     * @param g2
     * @return the comparison
     */
    @Override
    public int compare(Game g1, Game g2) {
        return Double.compare(g1.getPrice(), g2.getPrice());
    }
} // end class PriceComparator

class StockComparator implements Comparator<Game> {
    /**
     * Compares two Game objects by stock
     * 
     * @param g1
     * @param g2
     * @return the comparison
     */
    @Override
    public int compare(Game g1, Game g2) {
        return g1.getStock() - g2.getStock();
    }
} // end class StockComparator

class DeveloperComparator implements Comparator<Game> {
    /**
     * Compares two Game objects by developer
     * 
     * @param g1
     * @param g2
     * @return the comparison
     */
    @Override
    public int compare(Game g1, Game g2) {
        return (g1.getDeveloper().toLowerCase()).compareTo(g2.getDeveloper().toLowerCase());
    }
} // end class DeveloperComparator

class ReleaseDateComparator implements Comparator<Game> {
    /**
     * Compares two Game objects by release date
     * 
     * @param g1
     * @param g2
     * @return the comparison
     */
    @Override
    public int compare(Game g1, Game g2) {
        return g1.getReleaseDate().compareTo(g2.getReleaseDate());
    }
} // end class ReleaseDateComparator


/*
 * Possible additional comparators:
 * 
 * - GenreComparator
 * - SummaryComparator
 * - PlatformComparator
 * - IDComparator 
 */
/*
Half-Life 2
Valve
1
First-Person Shooter
11/16/2004
Step into the shoes of a physicist in a dystopian world of science-fiction. Survival meets an immersive narrative featuring climatic gunfights, brain-scratching puzzles, and even love.
PC
$40.00
80

Halo 3
Microsoft Game Studios
2
First-Person Shooter
11/26/2007
Enter an interstellar war in a richly-detailed sci-fi universe and fight for humanity. Known for its multiplayer and co-op.
Xbox 360
$40.00
75

Minecraft
Mojang
3
Sandbox
11/18/2011
Create explore and survive in a block-y world filled with endless possibilities and creative freedom. Beware of night-time...
PC, Mac, Linux
$26.95
120

Portal 2
Valve
4
Puzzle-Platformer
4/18/2011
Witty dialogue, ingenious puzzles, and an oddly compelling story. Manipulate portals and physics to navigate through a mind-bending laboratory named Aperture Science.
PC, PlayStation 3, Xbox 360
$30.00
100

Rocket League
Psyonix
5
Sports
07/07/2015
It's car soccer in a physics-driven, action-packed arena. Offers a unique twist on team sports, blending high-speed driving with soccer strategy in a dynamic, rocket-powered universe.
PC, PlayStation 4
$20.00
140

Undertale
Toby Fox
6
Role-Playing
09/15/2015
Every choice matters. Charm meets clever writing, quirky characters, and an innovative combat system that allows for nonviolent resolutions, challenging moral conventions of traditional RPGs.
PC, Mac
$10.00
105

The Witcher 3: Wild Hunt
CD Projekt
7
Action role-playing
05/19/2015
Embark on a dark fantasy adventure as Geralt of Rivia a monster hunter for hire in a vast open world full of rich storytelling and immersive gameplay.
PC, PlayStation 4, Xbox One
$40.00
60

The Legend of Zelda: Breath of the Wild
Nintendo
8
Action-adventure
03/03/2017
Embark on a grand adventure as Link in a vast open world filled with puzzles battles and exploration in this iconic Nintendo title.
Nintendo Switch, Wii U
$60.00
110

Red Dead Redemption 2
Rockstar Games
9
Action-Adventure
10/26/2018
Explore the unforgiving heartand of the Wild West as outlaw Arthur Morgan in this critically acclaimed, historically-detailed open-world epic from Rockstar Games.
PlayStation 4, Xbox One
$59.99
25

Assassin's Creed Valhalla
Ubisoft Montreal
10
Action RPG
11/10/2020
Forge your Viking legend as Eivor raiding conquering and building alliances in the stunning landscapes of Norway and England.
PlayStation 4, Xbox One, Xbox Series X, Xbox Series S
$59.99
60

FIFA 22
EA Vancouver
11
Sports
10/01/2021
Experience the thrill of football with realistic gameplay stunning visuals and enhanced career modes in the latest installment of the FIFA series.
Nintendo Switch, PlayStation 4, PlayStation 5, Xbox One, Xbox Series X, Xbox Series S
$59.99
25

Among Us
Innersloth
12
Social Deduction
06/15/2018
Work together to complete tasks on a spaceship but beware of impostors among the crew in this popular multiplayer game of deception and strategy.
Nintendo Switch, PlayStation 4, PlayStation 5, Xbox One, Xbox Series X, Xbox Series S
$4.99
50

Animal Crossing: New Horizons
Nintendo
13
Life Simulation
03/20/2020
Create your dream island paradise interact with adorable anthropomorphic villagers and customize your world in this relaxing life simulation game.
Nintendo Switch, Wii U
$59.99
60


 */