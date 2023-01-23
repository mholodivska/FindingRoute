package com.example.findingroute.api;

import com.example.findingroute.domain.Node;
import com.example.findingroute.domain.Route;
import com.example.findingroute.service.NodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/routing")
public class FindTheRouteResource {

    @GetMapping("/{origin}/{destination}")
    public ResponseEntity<Object> findTheRouteFromOriginToDestination(@PathVariable String origin, @PathVariable String destination) {
        Node start = NodeService.findTheNode(origin);
        Node finish = NodeService.findTheNode(destination);

        if (start == null || finish == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        String resultRoute = new Route(start, finish).searchTheRoute();

        if (resultRoute == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(resultRoute, HttpStatus.OK);
    }
}
