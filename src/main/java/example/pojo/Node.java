package example.pojo;

public class Node {
    private String node_id;
    private String node_type;
    private String content;
    private String model_id;

    public Node(){};
    public Node(String node_id,String node_type,String content, String model_id)
    {
        this.content = content;
        this.node_id = node_id;
        this.node_type = node_type;
        this.model_id = model_id;
    }
    public String getNode_id() {
        return node_id;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

    public String getNode_type() {
        return node_type;
    }

    public void setNode_type(String node_type) {
        this.node_type = node_type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getModel_id() {
        return model_id;
    }

    public void setModel_id(String model_id) {
        this.model_id = model_id;
    }


}
