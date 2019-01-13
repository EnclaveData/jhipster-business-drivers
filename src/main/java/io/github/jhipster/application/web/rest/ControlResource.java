package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.Control;
import io.github.jhipster.application.repository.ControlRepository;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Control.
 */
@RestController
@RequestMapping("/api")
public class ControlResource {

    private final Logger log = LoggerFactory.getLogger(ControlResource.class);

    private static final String ENTITY_NAME = "control";

    private final ControlRepository controlRepository;

    public ControlResource(ControlRepository controlRepository) {
        this.controlRepository = controlRepository;
    }

    /**
     * POST  /controls : Create a new control.
     *
     * @param control the control to create
     * @return the ResponseEntity with status 201 (Created) and with body the new control, or with status 400 (Bad Request) if the control has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/controls")
    @Timed
    public ResponseEntity<Control> createControl(@RequestBody Control control) throws URISyntaxException {
        log.debug("REST request to save Control : {}", control);
        if (control.getId() != null) {
            throw new BadRequestAlertException("A new control cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Control result = controlRepository.save(control);
        return ResponseEntity.created(new URI("/api/controls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /controls : Updates an existing control.
     *
     * @param control the control to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated control,
     * or with status 400 (Bad Request) if the control is not valid,
     * or with status 500 (Internal Server Error) if the control couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/controls")
    @Timed
    public ResponseEntity<Control> updateControl(@RequestBody Control control) throws URISyntaxException {
        log.debug("REST request to update Control : {}", control);
        if (control.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Control result = controlRepository.save(control);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, control.getId().toString()))
            .body(result);
    }

    /**
     * GET  /controls : get all the controls.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of controls in body
     */
    @GetMapping("/controls")
    @Timed
    public ResponseEntity<List<Control>> getAllControls(Pageable pageable) {
        log.debug("REST request to get a page of Controls");
        Page<Control> page = controlRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/controls");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /controls/:id : get the "id" control.
     *
     * @param id the id of the control to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the control, or with status 404 (Not Found)
     */
    @GetMapping("/controls/{id}")
    @Timed
    public ResponseEntity<Control> getControl(@PathVariable Long id) {
        log.debug("REST request to get Control : {}", id);
        Optional<Control> control = controlRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(control);
    }

    /**
     * DELETE  /controls/:id : delete the "id" control.
     *
     * @param id the id of the control to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/controls/{id}")
    @Timed
    public ResponseEntity<Void> deleteControl(@PathVariable Long id) {
        log.debug("REST request to delete Control : {}", id);

        controlRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
