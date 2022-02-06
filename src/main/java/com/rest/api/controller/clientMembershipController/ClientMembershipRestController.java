package com.rest.api.controller.clientMembershipController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rest.api.model.Client;
import com.rest.api.model.Client_Membership;
import com.rest.api.model.Membership;
import com.rest.api.repositories.clientMembershipRepo.ClientMembershipRepository;
import com.rest.api.repositories.clientRepo.ClientRepository;
import com.rest.api.repositories.membershipRepo.MembershipRepository;

;


@RestController
public class ClientMembershipRestController {

    private final ClientMembershipRepository clientMembershipRepository;
    private final ClientRepository clientRepository;
    private final MembershipRepository membershipRepository;

    /**
     * Constructor based auto wiring of class fields
     */
    @Autowired
    public ClientMembershipRestController(ClientRepository clientRepository,
                                          MembershipRepository membershipRepository,
                                          ClientMembershipRepository clientMembershipRepository) {
        this.clientRepository = clientRepository;
        this.membershipRepository = membershipRepository;
        this.clientMembershipRepository = clientMembershipRepository;
    }

    /**
     * This method returns all memberships a client has taken based on his id
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/restclients/client_membership/{id}")
    public List<Client_Membership> getClientMembership(@PathVariable("id") String id) {
        List<Client_Membership> client = clientMembershipRepository.findClient_MembershipByClient_Id(id);
        return client;
    }

    /**
     * This method creates or registers a new membership for a an existing client
     */
    @CrossOrigin(origins = "*")
    @PostMapping("/restclients/client_membership/create")
    public Client_Membership saveClientMembership(@RequestBody Client_Membership client_membership) {

        Membership membership = membershipRepository.findByType(client_membership.getMembership().getType());
        Client client = clientRepository.findById(client_membership.getClient().getId()).orElse(null);

        client_membership.setClient(client);
        client_membership.setMembership(membership);

        return clientMembershipRepository.save(client_membership);
    }

    /**
     * This method returns clients image based on his or her id. If an image is found it is returned
     * and if the image is not found a FileNotFoundException will rise in which case the system returns
     * the default emty image. System.getProperty() method returns the home directory of the current user.
     * This method uses IOUtils.toByteArray from Apache commons.
     * This method is used from the QT Desktop application to retrieve the client image.
     */
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/restclients/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getImageWithMediaType(@PathVariable("id") String id) throws IOException {
        try {
            InputStream in = new FileInputStream(System.getProperty("user.home") + "/athletiqueimages/" + id + ".jpg");
            return IOUtils.toByteArray(in);
        } catch (FileNotFoundException e) {
            InputStream in = new FileInputStream(System.getProperty("user.home") + "/athletiqueimages/noImage.jpg");
            return IOUtils.toByteArray(in);
        }
    }


    /**
     * This method returns clients image based on his or her id. If an image is found it is returned
     * and if the image is not found default empty image is returned.
     * System.getProperty() method returns the home directory of the current user.
     */
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/restclients/clientimage/{id}")
    public @ResponseBody
    Map<String, String> getImage(@PathVariable("id") String id) throws IOException {

        File file = new File(System.getProperty("user.home") + "/athletiqueimages/" + id + ".jpg");
        if(!file.exists()){
            file = new File(System.getProperty("user.home") + "/athletiqueimages/noImage.jpg");
        }
        String encodedImage = Base64.getEncoder().withoutPadding().encodeToString(Files.readAllBytes(file.toPath()));
        Map<String, String> jsonMap = new HashMap<>();
        jsonMap.put("content", encodedImage);
        return jsonMap;
    }
}
