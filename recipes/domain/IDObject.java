package recipes.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Random;

@Data
@AllArgsConstructor
public class IDObject {
    private int id;

    public IDObject() {
        Random idGenerator = new Random();
        this.id = idGenerator.nextInt(1000);
    }
}
