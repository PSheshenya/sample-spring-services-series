package my.sheshenya.samplespringservicesrest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Foo {

    private long id;

    @NotNull
    private String name;
    private String email;

    // standard getters and setters
}
