package org.acme.expense;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.acme.cashback.Cashback;
import org.acme.customer.Customer;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Expense extends PanacheEntityBase {
    @Id
    @Column(name="sale_id")
    public Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    public Customer customer;

    @ManyToOne
    @JoinColumn(name = "cashback_id")
    @JsonIgnore

    public Cashback cashback;
    public BigDecimal amount;
    public LocalDate date;

    public Cashback getCashback() {
        return cashback;
    }
}
