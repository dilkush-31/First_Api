package com.matcurve.fstAPI.controller;

import com.matcurve.fstAPI.Service.MatcurveApllicationService;
import com.matcurve.fstAPI.entity.JournalEntry;
import com.matcurve.fstAPI.repository.MatcurveEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2{

@Autowired
private MatcurveApllicationService matcurveApllicationService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<JournalEntry> all=matcurveApllicationService.getAll();
   if(all!=null && all.isEmpty()){
       return new ResponseEntity<>(all, HttpStatus.OK);
   }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<JournalEntry> CreateEntry(@RequestBody JournalEntry Myentry) {
        try {
            Myentry.setDate(LocalDateTime.now());
            matcurveApllicationService.SaveEntry(Myentry);
            return new ResponseEntity<>(Myentry,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("id/{MYID}")
    public ResponseEntity<JournalEntry> getJournalEntryById (@PathVariable ObjectId MYID) {
        Optional<JournalEntry> journalEntry=matcurveApllicationService.findById(MYID);
        if(journalEntry.isPresent()) {
            return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/id/{MYId}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId MYID) {
       matcurveApllicationService.deleteEntryById(MYID);
       return new ResponseEntity<JournalEntry>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity UpdateJournalEntryById(@RequestBody JournalEntry Myentry, @PathVariable ObjectId id) {
 JournalEntry old = matcurveApllicationService.findById(id).orElse(null);
 if(old != null) {
     old.setTitle(Myentry.getTitle()!=null && Myentry.getTitle()!=("")?Myentry.getTitle():old.getTitle());
     old.setContent(Myentry.getContent()!=null && Myentry.getContent()!=("")?Myentry.getContent():old.getContent());
     matcurveApllicationService.SaveEntry(old);
     return new ResponseEntity<>(old,HttpStatus.OK);
 }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
