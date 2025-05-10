package com.matcurve.fstAPI.repository;

import com.matcurve.fstAPI.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MatcurveEntryRepository extends MongoRepository<JournalEntry, ObjectId> {
    JournalEntry getById(ObjectId id);
}
























//controller --> service --> repository