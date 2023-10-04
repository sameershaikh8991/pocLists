package com.kafka.repo;

import com.kafka.mode.Data;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepo extends JpaRepository<Data,Long> {
}
