package my.sheshenya.samplespringservicesrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/foos")
public class FooController {

    @Autowired
    private FooService service;

//    @GetMapping("/{id}")
//    public Foo read(@PathVariable String id) {
//        return service.find(id);
//    }

    @GetMapping("/exception")
    public ResponseEntity<Void> requestWithException() {
        throw new RuntimeException("Error in the faulty controller!");
    }

    @GetMapping("/")
    public List<Foo> read() {
        return service.readAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Foo> read(@PathVariable("id") Long id) {
        Foo foundFoo = service.read(id);
        if (foundFoo == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundFoo);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Foo> create(@RequestBody Foo foo) throws URISyntaxException {
        Foo createdFoo = service.create(foo);
        if (createdFoo == null) {
            return ResponseEntity.notFound().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createdFoo.getId())
                    .toUri();

            return ResponseEntity.created(uri)
                    .body(createdFoo);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Foo> update(@RequestBody Foo foo, @PathVariable Long id) {
        Foo updatedFoo = service.update(id, foo);
        if (updatedFoo == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedFoo);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
