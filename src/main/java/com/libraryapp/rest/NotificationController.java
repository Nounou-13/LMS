package com.libraryapp.rest;

import com.libraryapp.entities.Notification;
import com.libraryapp.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotifications() {
        List<Notification> notifications = notificationService.findAll();
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notification> getNotificationById(@PathVariable Long id) {
        Notification notification = notificationService.findById(id);
        if (notification == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(notification, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
        try {
            Notification savedNotification = notificationService.save(notification);
            return new ResponseEntity<>(savedNotification, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notification> updateNotification(@PathVariable Long id, @RequestBody Notification notificationDetails) {
        Notification notification = notificationService.findById(id);
        if (notification == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            notification.setNotificationDate(notificationDetails.getNotificationDate());
            notification.setValidUntilDate(notificationDetails.getValidUntilDate());
            notification.setNotificationMessage(notificationDetails.getNotificationMessage());
            notification.setNotificationReceiver(notificationDetails.getNotificationReceiver());

            Notification updatedNotification = notificationService.save(notification);
            return new ResponseEntity<>(updatedNotification, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNotification(@PathVariable Long id) {
        try {
            notificationService.deleteById(id);
            return new ResponseEntity<>("Notification supprimée avec succès", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>("Erreur lors de la suppression de la notification", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
