package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import javax.sql.rowset.JoinRowSet;
import java.util.*;

@RestController
@RequestMapping("/journal") // REST Api controller class is created & can be accessed by /journal
public class JournalEntryController {

    private Map<Long, JournalEntry> journalEntries = new HashMap();

    @GetMapping public List<JournalEntry> getAll(){
     return new ArrayList<>(journalEntries.values());
    }
// All methods inside controller should be public so that external Https request can access the method
    @PostMapping
    public boolean craeteEntity(@RequestBody JournalEntry myEntry){ //@RequestBody – Converts incoming JSON data into a Java object.
        journalEntries.put(myEntry.getId(), myEntry);
        return true;
    }

    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable Long myId) { //@PathVariable – Extracts a specific value from the URL, usually an ID, to identify a particular resource
        return journalEntries.get(myId);
    }

      @DeleteMapping("id/{myId}")
     public JournalEntry deleteJournalEntryById(@PathVariable Long myId){
        return journalEntries.remove(myId);
    }
    @PutMapping
    public JournalEntry updateJournalById(@PathVariable Long id, @RequestBody JournalEntry myEntry) {
        return journalEntries.put(id, myEntry);
    }
}
