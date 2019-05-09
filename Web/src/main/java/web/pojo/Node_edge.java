package web.pojo;

public class Node_edge {
    private String node_id;

    public String getNode_id() {
        return node_id;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

    public String getEdges() {
        return edges;
    }

    public void setEdges(String edges) {
        this.edges = edges;
    }

    private String edges;//使用特定的分隔符$$连接起来的
}
