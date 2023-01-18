package com.borisov.phbook2.listener;

import com.borisov.phbook2.model.ContactDTO;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobListener extends JobExecutionListenerSupport {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JobListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            System.out.println("In Completion Listener ..");
            List<ContactDTO> results = jdbcTemplate.query("SELECT id, first_name,last_name,email,phone FROM contacts",
                    (rs,rowNum)->{
                        return new ContactDTO(rs.getLong(1), rs.getString(2),rs.getString(3),rs.getString(4),
                                rs.getString(5));
                    }
            );
            results.forEach(System.out::println);
        }
    }
}