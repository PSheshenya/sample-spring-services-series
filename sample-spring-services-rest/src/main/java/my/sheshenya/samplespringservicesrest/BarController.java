package my.sheshenya.samplespringservicesrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Controller
@RequestMapping("/bars")
public class BarController {

    @Autowired
    private FooService service;


    @GetMapping("/")
    public @ResponseBody List<Foo> read() {
        return service.readAll();
    }

    @ResponseBody
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public String handleHttpMediaTypeNotAcceptableException() {
        return "acceptable MIME type:" + MediaType.APPLICATION_JSON_VALUE;
    }

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<Foo> read(@PathVariable("id") Long id) {
        Foo foundFoo = service.read(id);
        if (foundFoo == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundFoo);
        }
    }

}
