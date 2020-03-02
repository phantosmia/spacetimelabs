package models;

import models.ExecutionItem;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;
import java.util.Map;
@Entity
public class Execution {

    private Long identifier;
    private Map<String, String> inputs;
    private Map<String,String> outputs;
    private Date createdAt;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "execution")
    private List<ExecutionItem> executions;

    public List<ExecutionItem> getExecutions() {
        return executions;
    }

    public Execution(Long identifier, Map<String, String> inputs, Map<String, String> outputs, Date createdAt, List<ExecutionItem> executions) {
        this.identifier = identifier;
        this.inputs = inputs;
        this.outputs = outputs;
        this.createdAt = createdAt;
        this.executions = executions;
    }

    public Long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Long identifier) {
        this.identifier = identifier;
    }

    public Map<String, String> getInputs() {
        return inputs;
    }

    public void setInputs(Map<String, String> inputs) {
        this.inputs = inputs;
    }

    public Map<String, String> getOutputs() {
        return outputs;
    }

    public void setOutputs(Map<String, String> outputs) {
        this.outputs = outputs;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setExecutions(List<ExecutionItem> executions) {
        this.executions = executions;
    }
}
