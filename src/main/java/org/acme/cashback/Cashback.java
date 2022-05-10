package org.acme.cashback;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.acme.customer.Customer;
import org.acme.expense.Expense;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Cashback extends PanacheEntityBase {
    @Id
    @Column(name = "cashback_id")
    public Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "customer_id")
    public Customer customer;

    @OneToMany(mappedBy = "cashback", fetch = FetchType.LAZY)
    public List<Expense> expenses;

    public Customer getCustomer() {
        return customer;
    }

    public BigDecimal amount;
}
