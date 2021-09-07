package nl.hva.ewa.empower.dataset;

import org.springframework.data.repository.CrudRepository;

public interface DatasetRepository extends CrudRepository<Dataset, Integer> {
    Dataset findByName(String name);
}
