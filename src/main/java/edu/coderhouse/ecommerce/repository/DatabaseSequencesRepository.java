package edu.coderhouse.ecommerce.repository;

import edu.coderhouse.ecommerce.models.documents.DatabaseSequences;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DatabaseSequencesRepository extends MongoRepository<DatabaseSequences, String> {
}
