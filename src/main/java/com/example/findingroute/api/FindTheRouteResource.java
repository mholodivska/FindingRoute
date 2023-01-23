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

    /**
     * Search for the possible land route from one country to another.
     *
     * @param origin
     *              the country code - e.g. BEL, BEN, BFA, BGD, BGR, BHR, BHS
     * @param destination
     *              the country code - e.g. CHE, CHL, CHN, CIV, CMR, COD, COG
     * @return string with the route if exists
     * @throws ResponseStatusException with code 400
     *             if some problem occurs
     */
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
