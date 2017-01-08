package edu.ciukstar.cooper.repo;

import edu.ciukstar.cooper.application.admin.Credentials;
import edu.ciukstar.cooper.domain.Role;
import edu.ciukstar.cooper.domain.RoleType;
import edu.ciukstar.cooper.domain.Role_;
import edu.ciukstar.cooper.domain.User;
import edu.ciukstar.cooper.domain.User_;
import io.atlassian.fugue.Either;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

@Named
@Stateless
public class UserRepo extends AbstractRepo<User> {

    @PersistenceContext(unitName = "cooper")
    private EntityManager em;
    @Inject
    private Event<List<User>> e;
    
    public Either<Exception, User> findUserByCredentials(Credentials c) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> user = cq.from(User.class);
        cq.select(user).where(cb.and(
                cb.equal(user.get(User_.username), c.getUsername()),
                cb.equal(user.get(User_.password), c.getPassword())
        ));
        try {
            return Either.right(getEntityManager().createQuery(cq).getSingleResult());
        } catch (NoResultException | NonUniqueResultException ex) {
            return Either.left(new AuthenticationException(ex));
        }
    }
    
    public List<User> findAllOrganizers() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> user = cq.from(User.class);

        Subquery<Role> sq = cq.subquery(Role.class);
        Root<Role> role = sq.from(Role.class);
        sq.select(role).where(cb.and(
                cb.equal(role.get(Role_.type), RoleType.ORGANIZER),
                cb.isMember(role, user.get(User_.roles))
        ));

        cq.select(user).where(cb.exists(sq));
        return getEntityManager().createQuery(cq).getResultList();
    }

    @Override
    public List<User> findAll() {
        final List<User> res = super.findAll();
        e.fire(res);
        return res;
    }

    public UserRepo() {
        super(User.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
