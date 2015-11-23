package com.coderscampus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscampus.domain.Section;

public interface SectionRepository extends JpaRepository<Section, Long>
{

}
