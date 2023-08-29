package edu.itstep.blockchain.controller;

import edu.itstep.blockchain.domain.Issue;
import edu.itstep.blockchain.domain.SupportSystem;
import edu.itstep.blockchain.exceptions.ResourceNotFoundException;
import edu.itstep.blockchain.repository.IssueRepository;
import edu.itstep.blockchain.repository.SuportSystemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
//domain-name:port/support-system
@RequestMapping("/support-system")
public class SupportSystemController {
  	
  SuportSystemRepository ssr;
  IssueRepository ir;
  
  public SupportSystemController(SuportSystemRepository ssr, IssueRepository ir) {
	  this.ssr = ssr;
	  this.ir = ir;
  }
  //domain-name:port/support-system?title=test&name=test2
  @GetMapping("/all")
  public ResponseEntity<List<SupportSystem>> getAll(@RequestParam(required=false)String title){
	  List<SupportSystem> systems = new ArrayList<>();
	  if(title == null) {
		  ssr.findAll().forEach(systems::add);
	  }else {
		  ssr.findByTitleContaining(title).forEach(systems::add);
	  }
	  if(systems.isEmpty())return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  return new ResponseEntity<>(systems, HttpStatus.OK);
  }
  @GetMapping("/issue/{id}")
  public ResponseEntity<Issue> getById(@PathVariable("id") long id) throws ResourceNotFoundException {
	  Issue issue = ir.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found issue with id = " + id));

    return new ResponseEntity<>(issue, HttpStatus.OK);
  }
  @GetMapping("/issues/resolved")
  public ResponseEntity<List<SupportSystem>> findByStatus() {
    List<SupportSystem> listIssue = ssr.findByStatus(true);

    if (listIssue.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    return new ResponseEntity<>(listIssue, HttpStatus.OK);
  }
}

