package models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.Map;
@Entity
public class ExecutionItem {
    private Long id;
    private String status;
    private Map<String,String> params;
    private String result;
    private String error;
    private Date startedAt;
    private Date finishedAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "identifier", nullable = false)
    private Execution execution;
}
