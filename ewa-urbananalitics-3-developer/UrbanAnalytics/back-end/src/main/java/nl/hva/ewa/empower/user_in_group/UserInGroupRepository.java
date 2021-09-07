package nl.hva.ewa.empower.user_in_group;

import nl.hva.ewa.empower.group.Group;
import nl.hva.ewa.empower.user.User;
import org.springframework.data.repository.CrudRepository;

public interface UserInGroupRepository extends CrudRepository<UserInGroup, Integer>  {
    UserInGroup findUserInGroupsByGroupAndUser(Group group, User user);
}
