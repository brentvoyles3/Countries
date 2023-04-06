package edu.uga.cs.project4;

/**
 * Represents a single quiz's results, including the id, questions associated with it,
 * and the score and date that the quiz was completed.
 * The id is -1 if the object has not been persisted in the database yet, and
 * the db table's primary key value, if it has been persisted.
 */
public class QuizInfo {

    //quiz variables and associated information
    private long id;
    private Questions qa1;
    private Questions qa2;
    private Questions qa3;
    private Questions qa4;
    private Questions qa5;
    private Questions qa6;
    String strScore;
    private Integer numberAnswered;
    private String date;
    private Integer score;

    /**
     * Constructor for {@link QuizInfo}.
     */
    public QuizInfo() {
        this.id = -1;
        this.qa1 = null;
        this.qa2 = null;
        this.qa3 = null;
        this.qa4 = null;
        this.qa5 = null;
        this.qa6 = null;
        this.numberAnswered = 0;
        this.date = null;
        this.score = 0;
    }

    /**
     * Constructor for {@link QuizInfo}.
     *
     * @param score The user's quiz score.
     * @param date The date the user completed the quiz.
     */
    public QuizInfo(Integer score, String date){
        this.id = -1;
        this.qa1 = null;
        this.qa2 = null;
        this.qa3 = null;
        this.qa4 = null;
        this.qa5 = null;
        this.qa6 = null;
        this.numberAnswered = 0;
        this.date = date;
        this.score = score;
    }

    /**
     * Returns the id of the {@code QuizInfo} object.
     *
     * @return long
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the unique id to set to associate to the {@code QuizInfo} object.
     *
     * @param id Unique id.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Returns the first question of quiz.
     *
     * @return String
     */
    public String getQA1() {
        return qa1.toString();
    }

    /**
     * Sets the first question of the quiz.
     *
     * @param qa The quiz question.
     */
    public void setQA1(Questions qa) {
        this.qa1 = qa;
    }

    /**
     * Returns the second question of quiz.
     *
     * @return String
     */
    public String getQA2() {
        return qa2.toString();
    }

    /**
     * Sets the second question of the quiz.
     *
     * @param qa The quiz question.
     */
    public void setQA2(Questions qa) {
        this.qa2 = qa;
    }

    /**
     * Returns the third question of quiz.
     *
     * @return String
     */
    public String getQA3() {
        return qa3.toString();
    }

    /**
     * Sets the third question of the quiz.
     *
     * @param qa The quiz question.
     */
    public void setQA3(Questions qa) {
        this.qa3 = qa;
    }

    /**
     * Returns the fourth question of quiz.
     *
     * @return String
     */
    public String getQA4() {
        return qa4.toString();
    }

    /**
     * Sets the fourth question of the quiz.
     *
     * @param qa The quiz question.
     */
    public void setQA4(Questions qa) {
        this.qa4 = qa;
    }

    /**
     * Returns the fifth question of quiz.
     *
     * @return String
     */
    public String getQA5() {
        return qa5.toString();
    }

    /**
     * Sets the fifth question of the quiz.
     *
     * @param qa The quiz question.
     */
    public void setQA5(Questions qa) {
        this.qa5 = qa;
    }

    /**
     * Returns the sixth question of quiz.
     *
     * @return String
     */
    public String getQA6() {
        return qa6.toString();
    }

    /**
     * Sets the sixth question of the quiz.
     *
     * @param qa The quiz question.
     */
    public void setQA6(Questions qa) {
        this.qa6 = qa;
    }

    /**
     * Returns the number of questions the user has answered.
     *
     * @return Integer
     */
    public Integer getNumberAnswered() {
        return numberAnswered;
    }

    /**
     * Sets the number of questions the user has answered.
     *
     * @param numberAnswered The number of questions the user has answered.
     */
    public void setNumberAnswered(Integer numberAnswered) {
        this.numberAnswered = numberAnswered;
    }

    /**
     * Returns the score of the completed quiz.
     *
     * @return Integer
     */
    public Integer getScore() {
        return score;
    }

    /**
     * Sets the users quiz score.
     *
     * @param score The user's quiz score.
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * Returns a string representation of the user's score.
     *
     * @return String
     */
    public String getStrScore() {
        strScore = String.valueOf(score);
        return strScore;
    }

    /**
     * Sets the user's score as a string.
     *
     * @param strScore The user's score.
     */
    public void setStrScore(String strScore) {
        this.strScore = strScore;
    }

    /**
     * Sends the user a message depending on their quiz results.
     *
     * @param score The user's score.
     * @return String
     */
    public String setMessage(int score) {
        if (score < 2) {
            return "Keep Practicing!";
        } else if (score == 2 || score == 3) {
            return "Keep It Up!";
        } else if (score == 4 || score == 5) {
            return "Great Job!";
        } else { //score is 6
            return "Perfect Score!";
        }
    }

    /**
     * Returns the date that the user completed the quiz.
     *
     * @return String
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date that the quiz was completed.
     *
     * @param date The date the quiz was completed.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Tracks the user's score and increments the score when
     * the user answers correctly.
     */
    public void incrementScore() {
        this.score += 1;
    }


    /**
     * Returns the quiz's information as a string.
     *
     * @return String
     */
    public String toString() {
        return id + ": " + score + " " + date;
    }

}