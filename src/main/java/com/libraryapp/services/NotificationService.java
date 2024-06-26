package com.libraryapp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryapp.DAO.NotificationRepository;
import com.libraryapp.entities.Notification;

@Service
public class NotificationService {

	@Autowired
	NotificationRepository notifRepo;
	
	public Notification save (Notification notification) {
		notifRepo.save(notification);
		return notification;
	}

	public Notification findById(Long id) {
		return notifRepo.findById(id).orElse(null);
	}
	
	public void saveById (Long id) {
		Notification notification = notifRepo.findById(id).get();
		notifRepo.save(notification);
	}
	
	public List<Notification> findAll(){
		List<Notification> notifications = (ArrayList<Notification>) notifRepo.findAll();
		return notifications;
	}
	
	public void deleteById(Long id) {
		notifRepo.deleteById(id);
	}
}
