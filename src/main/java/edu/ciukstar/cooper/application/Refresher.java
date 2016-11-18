package edu.ciukstar.cooper.application;

import edu.ciukstar.cooper.domain.Persistable;
import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 *
 * @author sergiu
 */
@Named
@Dependent
public class Refresher implements Serializable {
    public <E extends Persistable<?>> Optional<E> select(Collection<E> candidates, E current) {
        if (current != null && current.isNew()) {
            return Optional.of(current);
        }
        return candidates.stream().filter(g -> g.equals(current)).findAny();
    }
}
