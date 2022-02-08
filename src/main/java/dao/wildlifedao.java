import modal.Animals;

import java.util.List;

public interface wildlifedao {
    List<Animals> getAll();
    void add(Animals animal);
}
