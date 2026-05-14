package models;

/**
 * Modelo que representa la tabla address de Sakila.
 * Foreign Key: city_id → objeto City (composición)
///Johan Manuel Feliz Montero 100146608
 */
public class Address {

    private int    addressId;
    private String address;
    private String address2;
    private String district;
    private City   city;     // FK como composición
    private String postalCode;
    private String phone;

    public Address() {}

    public Address(int addressId, String address, String address2,
                   String district, City city,
                   String postalCode, String phone) {
        this.addressId  = addressId;
        this.address    = address;
        this.address2   = address2;
        this.district   = district;
        this.city       = city;
        this.postalCode = postalCode;
        this.phone      = phone;
    }

    public int    getAddressId()              { return addressId; }
    public void   setAddressId(int id)        { this.addressId = id; }

    public String getAddress()                { return address; }
    public void   setAddress(String address)  { this.address = address; }

    public String getAddress2()               { return address2; }
    public void   setAddress2(String address2){ this.address2 = address2; }

    public String getDistrict()               { return district; }
    public void   setDistrict(String district){ this.district = district; }

    public City   getCity()                   { return city; }
    public void   setCity(City city)          { this.city = city; }

    public String getPostalCode()             { return postalCode; }
    public void   setPostalCode(String code)  { this.postalCode = code; }

    public String getPhone()                  { return phone; }
    public void   setPhone(String phone)      { this.phone = phone; }

    @Override
    public String toString() {
        return String.format("[%d] %s, %s | Ciudad: %s | Tel: %s",
                addressId, address, district,
                city != null ? city.getCity() : "N/A", phone);
    }
}