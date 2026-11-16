package com.javanauta.user.infrastrucure.repository;

import com.javanauta.user.infrastrucure.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
