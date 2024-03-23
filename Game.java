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
	private String releaseDate; // in the format of MM/DD/YYYY
	private String description;
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
        this.releaseDate = "00/00/0000";
        this.description = "None";
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
     * @param description
     * @postcondition a new Game object is created with the given values
     */
    public Game(String title, String developer, String id, String genre, String releaseDate, String description, double price, int stock){
        this.title = title;
        this.developer = developer;
        this.id = id;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.description = description;
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
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     * Accessor for description
     * 
     * @return the description of the game
     */
    public String getDescription() {
        return description;
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
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * Mutator for description
     * 
     * @param description
     * @postcondition the description of the game is set to the given value
     */
    public void setDescription(String description) {
        this.description = description;
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
            "Release Date: " + releaseDate + "\n" +
            "Description: " + description + "\n"
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
                description.equals(g.getDescription()) &&
                price == g.getPrice() &&
                stock == g.getStock()
            );
        }
    }

    /**
     * Returns a consistent hash code for each Game object
     * 
     * @return the hash code for the Game object
     */
    @Override
    public int hashCode() {
        int sum = 0;
        for (int i = 0; i < id.length(); i++) {
            sum += id.charAt(i);
        }
        return sum;
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
        return g1.getTitle().compareTo(g2.getTitle());
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
 * - DeveloperComparator
 * - GenreComparator
 * - DescriptionComparator
 * - IDComparator 
 */