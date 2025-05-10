package com.matcurve.fstAPI.Service;
import com.fasterxml.jackson.annotation.JacksonInject;
import com.matcurve.fstAPI.entity.JournalEntry;
import com.matcurve.fstAPI.repository.MatcurveEntryRepository;
//import org.apache.el.stream.Optional;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


@Component
public class MatcurveApllicationService {

    @Autowired
    private MatcurveEntryRepository matcurveEntryRepository;

   public JournalEntry SaveEntry(JournalEntry journalEntry) {
      return matcurveEntryRepository.save(journalEntry);
   }

   public List<JournalEntry> getAll(){
     return matcurveEntryRepository.findAll();

   }
   public Optional<JournalEntry> findById(ObjectId id) {
       return matcurveEntryRepository.findById(id);
   }

   public void deleteEntryById(ObjectId id) {
       matcurveEntryRepository.deleteById(id);
   }
    }

//controller --> service --> repository