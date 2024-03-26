/**
 * Game.java
 * @author Abdullah Mohammad
 */
import java.text.DecimalFormat;
import java.util.Comparator;

public class Game {
	private String title;
	private String developer;
	private String id;
	private String genre;
	private Date releaseDate;
	private String summary;
    private LinkedList<String> platforms;
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
        this.platforms = new LinkedList<String>();
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
        this.platforms = new LinkedList<String>();
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
    public Game(String title, String developer, String id, String genre, Date releaseDate, String summary, LinkedList<String> platforms, double price, int stock){
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
    public LinkedList<String> getPlatforms() {
        return platforms;
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
    public void setPlatforms(LinkedList<String> platforms) {
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
        return (
            "Title: " + title + "\n" +
            "Developer: " + developer + "\n" +
            "ID: " + id + "\n" +
            "Genre: " + genre + "\n" +
            "Release Date: " + releaseDate.toString() + "\n" +
            "Summary: " + summary + "\n"
        );
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