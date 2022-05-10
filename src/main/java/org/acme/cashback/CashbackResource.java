package org.acme.cashback;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;

public interface CashbackResource extends PanacheEntityResource<Cashback, Long> {}
