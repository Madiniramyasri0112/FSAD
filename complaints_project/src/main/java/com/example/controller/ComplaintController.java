package com.example.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.repository.*;
import com.example.model.*;

import java.util.*;

@RestController
@CrossOrigin
public class ComplaintController {

    @Autowired
    private ComplaintRepository complaintRepo;

    @Autowired
    private UserRepository userRepo;

    @PostMapping("/complaints")
    public Complaint addComplaint(@RequestBody Map<String, String> data) {

        user user = userRepo.findByEmail(data.get("email")).orElseThrow();

        Complaint complaint = new Complaint();
        complaint.setTitle(data.get("title"));
        complaint.setDescription(data.get("description"));
        complaint.setUser(user);

        return complaintRepo.save(complaint);
    }

    @GetMapping("/complaints/{email}")
    public List<Complaint> getUserComplaints(@PathVariable String email) {

        user user = userRepo.findByEmail(email).orElseThrow();
        return complaintRepo.findByUser(user);
    }

    @GetMapping("/admin/complaints")
    public List<Complaint> getAllComplaints() {
        return complaintRepo.findAll();
    }

    @PutMapping("/complaints/{id}/{status}")
    public Complaint updateStatus(@PathVariable Long id,
                                  @PathVariable String status) {

        Complaint complaint = complaintRepo.findById(id).orElseThrow();
        complaint.setStatus(status);
        return complaintRepo.save(complaint);
    }
}