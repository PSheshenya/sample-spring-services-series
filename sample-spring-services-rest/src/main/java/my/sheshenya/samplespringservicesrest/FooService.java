package my.sheshenya.samplespringservicesrest;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class FooService {

    // DB repository mock
    private Map<Long, Foo> repository = Arrays.asList(
            new Foo[]{
                    new Foo(1, "Alan","Turing"),
                    new Foo(2, "Sebastian","Bach"),
                    new Foo(3, "Pablo","Picasso"),
            }).stream()
            .collect(Collectors.toConcurrentMap(s -> s.getId(), Function.identity()));

    // DB id sequence mock
    private AtomicLong sequence = new AtomicLong(3);

    public List<Foo> readAll() {
        return repository.values().stream().collect(Collectors.toList());
    }

    public Foo read(Long id) {
        return repository.get(id);
    }

    public Foo create(Foo foo) {
        long key = sequence.incrementAndGet();
        foo.setId(key);
        repository.put(key, foo);
        return foo;
    }

    public Foo update(Long id, Foo foo) {
        foo.setId(id);
        Foo oldFoo = repository.replace(id, foo);
        return oldFoo == null ? null : foo;
    }

    public void delete(Long id) {
        repository.remove(id);
    }
}