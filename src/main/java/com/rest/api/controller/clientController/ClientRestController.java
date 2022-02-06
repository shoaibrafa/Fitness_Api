package com.rest.api.controller.clientController;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rest.api.model.Client;
import com.rest.api.repositories.clientMembershipRepo.ClientMembershipRepository;
import com.rest.api.repositories.clientRepo.ClientRepository;
import com.rest.api.service.Client_PhotoUploadService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ClientRestController {

    private final ClientRepository clientRepo;
    private final Client_PhotoUploadService clientPhotoUploadService;
    private final ClientMembershipRepository clientMembershipRepository;

    /**
     * Constructor based auto wiring of class fields
     */
    @Autowired
    ClientRestController(ClientRepository clientRepo,
            Client_PhotoUploadService clientPhotoUploadService,
            ClientMembershipRepository clientMembershipRepository) {
        this.clientRepo = clientRepo;
        this.clientPhotoUploadService = clientPhotoUploadService;
        this.clientMembershipRepository = clientMembershipRepository;
    }

    /**
     * This method returns all clients from database.
     * The result is shown in clients component
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/restclients/loadclients/{page}")
    public Page<Client> getClients(@PathVariable("page") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize) throws ParseException {

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("id"));
        Page<Client> clients = clientRepo.findByOrderByIdDesc(pageable);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Date memEndDate;
        for (Client c : clients) {
            memEndDate = sdf.parse(getDate(c.getId()));
            if (memEndDate.after(date)) {
                c.setStatus("Active");
            } else if (memEndDate.before(date)) {
                c.setStatus("Inactive");
            } else {
                c.setStatus("No Membership");
            }
        }
        return clients;
    }

    /**
     * This method returns is used to get client last membership date.
     * It is used in getClients() method.
     */
    public String getDate(String id) {
        return clientMembershipRepository.findClient_MembershipByEnd_date(id).getEnd_date().toString();
    }

    /**
     * This method returns single client information based on his or her id.
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/restclients/loadclient/{id}")
    public List<Client> getClients(@PathVariable("id") String id) {
        Client clients = clientRepo.findById(id).orElse(null);
        List c = new ArrayList();
        c.add(clients);
        return c;
    }

    /**
     * This method returns all clients based on his name.
     * This method is usually used for searching purposes.
     */
    @GetMapping("restclients/loadsingleclient/{name}")
    public List<Client> searchClient(@PathVariable("name") String name) throws ParseException {
        List<Client> clients = clientRepo.findByNameStartsWith(name);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Date memEndDate;
        for (Client c : clients) {
            memEndDate = sdf.parse(getDate(c.getId()));
            if (memEndDate.after(date)) {
                c.setStatus("Active");
            } else if (memEndDate.before(date)) {
                c.setStatus("Inactive");
            } else {
                c.setStatus("No Membership Found");
            }
        }
        return clients;
    }

    /**
     * This method creates a new client. It also gets client image and stores it in
     * a directory
     * shown bellow. The directory should be out side of the application directory.
     */
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/restclients/createclient")
    public Client createClient(@RequestParam("photo") MultipartFile photo,
            @RequestParam("id") String id,
            @RequestParam("name") String name,
            @RequestParam("lastName") String lastName,
            @RequestParam("phone") Long phone,
            @RequestParam("joiningDate") String joiningDate,
            @RequestParam("status") String status,
            @RequestParam("remarks") String remarks) throws IOException, ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date jDate = new java.sql.Date(formatter.parse(joiningDate).getTime());

        System.out.print("something here");
        
        Client client = new Client();
        client.setId(id);
        client.setName(name);
        client.setLastName(lastName);
        client.setPhone(phone);
        client.setJoiningDate(jDate);
        client.setStatus(status);
        client.setRemarks(remarks);
        Client c = clientRepo.save(client);
        clientPhotoUploadService.uploadPhoto(photo, id);
        return c;
    }

    /**
     * This method is used to only upload client image or change the existing image
     * in client-info component.
     */
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/restclients/changeClientImage")
    public void changeClientImage(@RequestParam("photo") MultipartFile photo, @RequestParam("id") String id)
            throws IOException {
        clientPhotoUploadService.uploadPhoto(photo, id);
    }

    /**
     * This method returns clients based on their status (active, inactive).
     */
    @CrossOrigin(origins = "*")
    @GetMapping(value = "restclients/clientstatus/{status}")
    public List<Client> activeClients(@PathVariable("status") String status) {
        return clientRepo.findClientsByStatus(status);
    }
}
