package hm.springframework.spring6restmvc.repositories;

import hm.springframework.spring6restmvc.entities.Beverage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BeverageRepository extends JpaRepository<Beverage, UUID> {
}
