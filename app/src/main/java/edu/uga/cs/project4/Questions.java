package edu.uga.cs.project4;

/**
 * Represents a single question, including the id, country, continent,
 * option1, and option2.
 * The id is -1 if the object has not been persisted in the database yet, and
 * the db table's primary key value, if it has been persisted.
 */
public class Questions {

    private long id;
    private String country;
    private String continent;
    private String option1;
    private String option2;

    /**
     * Constructor for {@link Questions}.
     */
    public Questions() {
        this.id = -1;
        this.country = null;
        this.continent = null;
        this.option1 = null;
        this.option2 = null;

    }

    /**
     * Constructor for {@link Questions}.
     *
     * @param country   The country.
     * @param continent The continent.
     * @param option1   The first random option for the user.
     * @param option2   The second random option for the user.
     */
    public Questions(String country, String continent, String option1, String option2) {
        this.id = -1;
        this.country = country;
        this.continent = continent;
        this.option1 = option1;
        this.option2 = option2;
    }

    /**
     * Returns the id of the {@code Question} object.
     *
     * @return long
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the unique id to set to associate to the {@code Question} object.
     *
     * @param id Unique id.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Returns the country of the {@code Question} object.
     *
     * @return String
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country to the {@code Question} object.
     *
     * @param country The country.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Returns the continent of the {@code Question} object.
     *
     * @return String
     */
    public String getContinent() {
        return continent;
    }

    /**
     * Sets the continent to the {@code Question} object.
     *
     * @param continent The country.
     */
    public void setContinent(String continent) {
        this.continent = continent;
    }

    /**
     * Returns the first random option of the {@code Question} object.
     *
     * @return String
     */
    public String getOption1() {
        return option1;
    }

    /**
     * Sets the first random option to the {@code Question} object.
     *
     * @param option1 The country.
     */
    public void setOption1(String option1) {
        this.option1 = option1;
    }

    /**
     * Returns the second random option of the {@code Question} object.
     *
     * @return String
     */
    public String getOption2() {
        return option2;
    }

    /**
     * Sets the second random option to the {@code Question} object.
     *
     * @param option2 The country.
     */
    public void setOption2(String option2) {
        this.option2 = option2;
    }

    /**
     * Returns the question's information as a string.
     *
     * @return String
     */
    public String toString() {
        return id + ": " + country + " " + continent + " " + option1 + " " + option2;
    }

    /**
     * Returns true if the user selected the correct answer.
     *
     * @param userAnswer The users answer selection.
     * @return boolean True if question is answered correctly.
     */
    public boolean gradeQuestion(String userAnswer) {
        Boolean checkAnswer = false;
        if (continent.equals(userAnswer)) {
            checkAnswer = true;
        }
        return checkAnswer;
    }

}