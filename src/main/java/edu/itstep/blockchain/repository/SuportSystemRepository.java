package edu.itstep.blockchain.repository;

import edu.itstep.blockchain.domain.SupportSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuportSystemRepository extends JpaRepository<SupportSystem, Long> {
     List<SupportSystem> findByStatus(boolean status);
     List<SupportSystem> findByTitleContaining(String title);
}
