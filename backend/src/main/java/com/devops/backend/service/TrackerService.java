package com.devops.backend.service;

import com.devops.backend.form.TrackerForm;
import com.devops.backend.model.Tracker;
import com.devops.backend.repository.DeliveryRequestRepository;
import com.devops.backend.repository.TrackerRepository;
import com.devops.backend.service.observer.NotificationObserver;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TrackerService {
    private final TrackerRepository trackerRepository;
    private final NotificationObserver notificationObserver;

    public TrackerService(TrackerRepository trackerRepository, NotificationObserver notificationObserver){
        this.trackerRepository = trackerRepository;
        this.notificationObserver = notificationObserver;
    }

    // ensures that the notificationObserver is added as an observer to all trackers
    @PostConstruct
    public void init() {
        List<Tracker> trackers = trackerRepository.findAll();
        for (Tracker tracker : trackers) {
            tracker.addObserver(notificationObserver);
        }
    }

    public Tracker save(Tracker tracker){
        Tracker t = trackerRepository.save(tracker);
        t.addObserver(notificationObserver);
        return t;
    }

    public Tracker get(Long id){
        Optional<Tracker> tracker = trackerRepository.findById(id);
        return tracker.orElseThrow(() -> new IllegalArgumentException("Tracker ID " + id + " not found"));
    }

    public List<Tracker> getAll(){
        return trackerRepository.findAll();
    }

    public void delete(Long id){
        trackerRepository.deleteById(id);
    }

    // update a tracker
    public Tracker updateTracker(Long id, TrackerForm trackerForm){
        Tracker tracker = get(id);
        // loop through all delivery requests and get the one with the tracker
        tracker.addObserver(notificationObserver);
        tracker.updateTracker(trackerForm.getStatus(), trackerForm.getLocation(), id);
        return trackerRepository.save(tracker);
    }
}