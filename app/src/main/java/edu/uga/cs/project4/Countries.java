package edu.uga.cs.project4;

public class Countries {
    private long   id;
    private String countryName;
    private String countryContinent;

    public Countries() {
        this.id = -1;
        this.countryName = null;
        this.countryContinent = null;
    }

    public Countries(String countryName, String countryContinent) {
        this.id = -1;  // the primary key id will be set by a setter method
        this.countryName = countryName;
        this.countryContinent = countryContinent;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getCountryName()
    {
        return countryName;
    }

    public void setCountryName(String companyName)
    {
        this.countryName = countryName;
    }

    public String getContinent()
    {
        return countryContinent;
    }

    public void setContinent(String countryContinent)
    {
        this.countryContinent = countryContinent;
    }

    public String toString() {
        return id + ": " + countryName + " " + countryContinent;
    }

}
