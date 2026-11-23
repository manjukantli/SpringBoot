package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    private Map<Long, JournalEntry> journalEntries = new HashMap<>();

    // GET: Fetch all journal entries
    @GetMapping
    public ResponseEntity<List<JournalEntry>> getAll() {

        return ResponseEntity.ok(
                new ArrayList<>(journalEntries.values())
        );
    }

    // POST: Create a new journal entry
    @PostMapping
    public ResponseEntity<JournalEntry> createEntity(
            @RequestBody JournalEntry myEntry) {

        journalEntries.put(myEntry.getId(), myEntry);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(myEntry);
    }

    // GET: Fetch a journal entry by ID
    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(
            @PathVariable Long myId) {

        JournalEntry entry = journalEntries.get(myId);

        if (entry != null) {
            return ResponseEntity.ok(entry);
        }

        return ResponseEntity.notFound().build();
    }

    // DELETE: Delete a journal entry by ID
    @DeleteMapping("/id/{myId}")
    public ResponseEntity<Void> deleteJournalEntryById(
            @PathVariable Long myId) {

        JournalEntry removedEntry = journalEntries.remove(myId);

        if (removedEntry != null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    // PUT: Update an existing journal entry
    @PutMapping("/id/{id}")
    public ResponseEntity<JournalEntry> updateJournalById(
            @PathVariable Long id,
            @RequestBody JournalEntry myEntry) {

        if (!journalEntries.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }

        journalEntries.put(id, myEntry);

        return ResponseEntity.ok(myEntry);
    }
}