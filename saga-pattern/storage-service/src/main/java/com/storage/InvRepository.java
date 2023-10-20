package com.storage;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InvRepository extends JpaRepository<Inventory,Long> {


        Iterable<Inventory> findByItem(String item);
}