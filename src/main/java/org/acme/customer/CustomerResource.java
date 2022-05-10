package org.acme.customer;


import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;

public interface CustomerResource extends PanacheEntityResource<Customer, Long> {
}
