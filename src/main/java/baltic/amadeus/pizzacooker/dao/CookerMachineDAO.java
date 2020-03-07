package baltic.amadeus.pizzacooker.dao;

import baltic.amadeus.pizzacooker.entity.CookerMachine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CookerMachineDAO extends JpaRepository<CookerMachine, Integer> {
}
