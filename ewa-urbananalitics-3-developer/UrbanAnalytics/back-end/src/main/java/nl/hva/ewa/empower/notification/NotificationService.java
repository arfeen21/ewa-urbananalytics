package nl.hva.ewa.empower.notification;

import nl.hva.ewa.empower.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NotificationService {

    public NotificationService() {
    }

    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getAllNotifications() {
        List<Notification> allNotifications = new ArrayList<>();
        for (Notification n: notificationRepository.findAll()){
            allNotifications.add(n);
        }
        return allNotifications;
    }

    public boolean addNotification(Notification n){
        notificationRepository.save(n);
        return true;
    }

}
