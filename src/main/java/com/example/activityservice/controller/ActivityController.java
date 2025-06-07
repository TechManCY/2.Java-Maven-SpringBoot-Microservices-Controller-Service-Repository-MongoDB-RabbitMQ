package com.example.activityservice.controller;

import com.example.activityservice.dto.ActivityRequest;
import com.example.activityservice.dto.ActivityResponse;
import com.example.activityservice.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

//    {
//        "userId": "abefe893-992a-445a-9e88-8e6d71a80eb0",
//            "type": "RUNNING",
//            "duration": 30,
//            "caloriesBurned": 300,
//            "startTime": "2024-12-12T10:00:00",
//            "additionalMetrics": {
//        "distance": 5.2,
//                "averageSpeed": 10.4,
//                "maxHeartRate": 165
//    }
//    }
    //docker run -d --name rabbitmq_local -p 5672:5672 -p 15672:15672 rabbitmq:4-management
    @PostMapping
    public ResponseEntity<ActivityResponse> trackActivity(@RequestBody ActivityRequest activityRequest){
        return ResponseEntity.ok(activityService.trackActivity(activityRequest));
    }

    @GetMapping
    public ResponseEntity<List<ActivityResponse>> getUserActivities(@RequestHeader("X-User-ID") String userId){
        return ResponseEntity.ok(activityService.getUserActivities(userId));
    }

    @GetMapping("/{activityId}")
    public ResponseEntity<List<ActivityResponse>> getActivity(@PathVariable String activityId){
        return ResponseEntity.ok(activityService.getActivity(activityId));
    }


}
