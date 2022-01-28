package com.Record.Receptionist.repository;

import com.Record.Receptionist.entity.Receptionist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceptionistRepository extends JpaRepository<Receptionist,Long> {
}
