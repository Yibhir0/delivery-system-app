package com.devops.backend.controller;

import com.devops.backend.form.TrackerForm;
import com.devops.backend.model.Tracker;
import com.devops.backend.service.TrackerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/tracker", produces = "application/json")
public class TrackerController {
    private final TrackerService trackerService;

    @Autowired
    public TrackerController(TrackerService trackerService){
        this.trackerService = trackerService;
    }

    @Tag(name="Company GET Endpoints / Admin GET Endpoints")
    @Operation(summary = "Get all trackers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trackers retrieved"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping(value="/")
    public ResponseEntity<List<Tracker>> getAllTrackers() {
        List<Tracker> trackers = trackerService.getAll();
        return  ResponseEntity.ok(trackers);
    }

    @Tags({
            @Tag(name = "Company GET Endpoints / Admin GET Endpoints"),
            @Tag(name = "User GET Endpoints"),
    })
    @Operation(summary = "Get a tracker by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tracker retrieved"),
            @ApiResponse(responseCode = "404", description = "Tracker ID not valid", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping(value="/{trackerId}")
    public ResponseEntity<Tracker> getTrackerById(@PathVariable Long trackerId) {
        Tracker tracker = trackerService.get(trackerId);
        return ResponseEntity.ok(tracker);
    }

    @Tags({
            @Tag(name = "Company PUT Endpoints / Admin PUT Endpoints"),
            @Tag(name = "Driver PUT Endpoints")
    })
    @Operation(summary = "Update a tracker")
    @PutMapping(value="/{trackerId}/status",consumes = "application/json")
    public ResponseEntity<Tracker> updateTrackerStatus(@PathVariable Long trackerId, @RequestBody TrackerForm trackerForm) {
        // TODO: Add validation for tracker form
        Tracker updatedTracker = trackerService.updateTracker(trackerId, trackerForm);
        return ResponseEntity.ok(updatedTracker);
    }

}