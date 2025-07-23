package com.artur.demoApp.repositories;

import com.artur.demoApp.models.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeakerRepository extends JpaRepository <Speaker, Long> {
}
