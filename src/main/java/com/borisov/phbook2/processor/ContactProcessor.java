package com.borisov.phbook2.processor;

import com.borisov.phbook2.model.Contact;
import com.borisov.phbook2.model.ContactDTO;
import org.springframework.batch.item.ItemProcessor;

public class ContactProcessor implements ItemProcessor<Contact, ContactDTO> {

    @Override
    public ContactDTO process(final Contact contact) throws Exception {
        System.out.println("Transforming contact(s) to contactDTO(s)..");
        final ContactDTO contactDto = new ContactDTO(contact.getId(), contact.getFirstName(), contact.getLastName(),
                contact.getEmail(), contact.getPhone());

        return contactDto;
    }

}