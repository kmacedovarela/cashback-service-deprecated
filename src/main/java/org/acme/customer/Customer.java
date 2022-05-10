package org.acme.customer;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import org.acme.cashback.Cashback;
import org.acme.expense.Expense;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer extends PanacheEntityBase {

    @Column(insertable = false, updatable = false)
    @Id
    public Long customer_id;

    @OneToOne
    @JoinColumn(name = "customer_id")
    public Cashback cashback;

    @OneToMany(mappedBy = "customer")
    private List<Expense> expenses;

    public String name;
    public String status;

    @Transient
    private static int pageCount;

    public Customer(){}

    public Customer(Long customer_id, String name) {
        this.customer_id = customer_id;
        this.name = name;
    }

    /**
     *
     * This method can be used for paginated searches. It queries data based on the
     * page details. Additionally, it retrieves the total page count and sets
     * the value in the variable pageCount.
     *
     * @param pageIndex the current page used as the starting index for the search
     * @param pageSize number of results to be queried by page
     * @return list of customers
     */
    public static List<Customer> findAll(Integer pageIndex, Integer pageSize) {
        PanacheQuery<PanacheEntityBase> customersQuery = Customer.findAll().page(Page.of(pageIndex, pageSize));
        setPageCount(customersQuery.pageCount());
        return customersQuery.list();
    }

    public static int getPageCount() {
        return pageCount;
    }
    public static void setPageCount(int pageCount) {
        Customer.pageCount = pageCount;
    }
}
