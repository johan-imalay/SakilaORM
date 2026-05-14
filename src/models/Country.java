package models;

/**
 * Modelo que representa la tabla country de Sakila.
///Johan Manuel Feliz Montero 100146608
 */
public class Country {

    private int    countryId;
    private String country;

    public Country() {}

    public Country(int countryId, String country) {
        this.countryId = countryId;
        this.country   = country;
    }

    public int    getCountryId()            { return countryId; }
    public void   setCountryId(int id)      { this.countryId = id; }

    public String getCountry()              { return country; }
    public void   setCountry(String country){ this.country = country; }

    @Override
    public String toString() {
        return String.format("[%d] %s", countryId, country);
    }
}