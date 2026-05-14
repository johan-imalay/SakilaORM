package models;

import java.time.LocalDateTime;

/**
 * Modelo que representa la tabla rental de Sakila.

 */
public class Rental {

    private int rentalId;
    private LocalDateTime rentalDate;
    private int inventoryId;
    private int customerId;
    private LocalDateTime returnDate;
    private int staffId;

    public Rental() {}

    public Rental(int rentalId, LocalDateTime rentalDate, int inventoryId,
                  int customerId, LocalDateTime returnDate, int staffId) {
        this.rentalId    = rentalId;
        this.rentalDate  = rentalDate;
        this.inventoryId = inventoryId;
        this.customerId  = customerId;
        this.returnDate  = returnDate;
        this.staffId     = staffId;
    }

    public int getRentalId()                        { return rentalId; }
    public void setRentalId(int id)                 { this.rentalId = id; }

    public LocalDateTime getRentalDate()            { return rentalDate; }
    public void setRentalDate(LocalDateTime date)   { this.rentalDate = date; }

    public int getInventoryId()                     { return inventoryId; }
    public void setInventoryId(int id)              { this.inventoryId = id; }

    public int getCustomerId()                      { return customerId; }
    public void setCustomerId(int id)               { this.customerId = id; }

    public LocalDateTime getReturnDate()            { return returnDate; }
    public void setReturnDate(LocalDateTime date)   { this.returnDate = date; }

    public int getStaffId()                         { return staffId; }
    public void setStaffId(int id)                  { this.staffId = id; }

    @Override
    public String toString() {
        return String.format("[%d] Inventario: %d | Cliente: %d | Rentado: %s",
                rentalId, inventoryId, customerId, rentalDate);
    }
}