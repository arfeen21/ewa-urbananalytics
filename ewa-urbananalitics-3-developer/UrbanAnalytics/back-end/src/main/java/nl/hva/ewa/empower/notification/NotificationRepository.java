package nl.hva.ewa.empower.notification;

import nl.hva.ewa.empower.user.User;
import org.springframework.data.repository.CrudRepository;

public interface NotificationRepository extends CrudRepository<Notification, Integer> {
}
