package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.model.Complaint;
import com.example.model.user;
import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findByUser(user user);
}