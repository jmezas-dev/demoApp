package com.artur.demoApp.repositories;

import com.artur.demoApp.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository <Session, Long> {

}
