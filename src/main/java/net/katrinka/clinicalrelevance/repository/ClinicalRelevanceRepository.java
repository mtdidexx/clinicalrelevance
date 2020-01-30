package net.katrinka.clinicalrelevance.repository;

import net.katrinka.clinicalrelevance.domain.ClinicalRelevance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicalRelevanceRepository extends CrudRepository<ClinicalRelevance, String> {
}
