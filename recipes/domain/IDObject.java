package recipes.domain;

import lombok.Data;

@Data
public class IDObject {
    private Long id;

    public IDObject(Long id) {
        this.id = id;
    }
}
