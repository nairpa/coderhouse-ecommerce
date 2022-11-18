package edu.coderhouse.ecommerce.services;

import edu.coderhouse.ecommerce.models.documents.DatabaseSequences;
import edu.coderhouse.ecommerce.services.interfaces.SequenceGenerator;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

@AllArgsConstructor
@Service
public class SequenceGeneratorImpl implements SequenceGenerator {
    private final MongoOperations mongoOperations;

    public Long generateSequence(String sequenceName) {
        DatabaseSequences counter = mongoOperations.findAndModify(query(where("_id").is(sequenceName)),
                new Update().inc("value", 1), options().returnNew(true).upsert(true), DatabaseSequences.class);
        return !Objects.isNull(counter) ? counter.getValue() : 1;
    }
}
