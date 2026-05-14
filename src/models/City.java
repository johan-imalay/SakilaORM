package models;

/**
 * Modelo que representa la tabla city de Sakila.
 * Foreign Key: country_id → objeto Country (composición)

 */
public class City {

    private int     cityId;
    private String  city;
    private Country country; // FK como composición

    public City() {}

    public City(int cityId, String city, Country country) {
        this.cityId  = cityId;
        this.city    = city;
        this.country = country;
    }

    public int     getCityId()              { return cityId; }
    public void    setCityId(int id)        { this.cityId = id; }

    public String  getCity()               { return city; }
    public void    setCity(String city)    { this.city = city; }

    public Country getCountry()            { return country; }
    public void    setCountry(Country c)   { this.country = c; }

    @Override
    public String toString() {
        return String.format("[%d] %s | País: %s",
                cityId, city, country != null ? country.getCountry() : "N/A");
    }
}