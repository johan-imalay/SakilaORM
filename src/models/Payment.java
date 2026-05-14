package models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Modelo que representa la tabla payment de Sakila.
 * Foreign Keys: customer_id → Customer, staff_id → Staff,
 *               rental_id → Rental (composición)

 */
public class Payment {

    private int            paymentId;
    private Customer       customer;  // FK como composición
    private Staff          staff;     // FK como composición
    private Rental         rental;    // FK como composición
    private BigDecimal     amount;
    private LocalDateTime  paymentDate;

    public Payment() {}

    public Payment(int paymentId, Customer customer, Staff staff,
                   Rental rental, BigDecimal amount,
                   LocalDateTime paymentDate) {
        this.paymentId   = paymentId;
        this.customer    = customer;
        this.staff       = staff;
        this.rental      = rental;
        this.amount      = amount;
        this.paymentDate = paymentDate;
    }

    public int           getPaymentId()               { return paymentId; }
    public void          setPaymentId(int id)         { this.paymentId = id; }

    public Customer      getCustomer()                { return customer; }
    public void          setCustomer(Customer c)      { this.customer = c; }

    public Staff         getStaff()                   { return staff; }
    public void          setStaff(Staff s)            { this.staff = s; }

    public Rental        getRental()                  { return rental; }
    public void          setRental(Rental r)          { this.rental = r; }

    public BigDecimal    getAmount()                  { return amount; }
    public void          setAmount(BigDecimal amount) { this.amount = amount; }

    public LocalDateTime getPaymentDate()             { return paymentDate; }
    public void          setPaymentDate(LocalDateTime date) { this.paymentDate = date; }

    @Override
    public String toString() {
        return String.format("[%d] Cliente: %s | Monto: $%s | Fecha: %s",
                paymentId,
                customer != null ? customer.getFirstName() + " " +
                        customer.getLastName() : "N/A",
                amount, paymentDate);
    }
}