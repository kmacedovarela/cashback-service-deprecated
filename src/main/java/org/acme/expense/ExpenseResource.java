package org.acme.expense;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;
import org.acme.cashback.Cashback;

public interface ExpenseResource extends PanacheEntityResource<Expense, Long> {}
