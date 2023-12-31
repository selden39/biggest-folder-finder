import java.io.File;
import java.util.ArrayList;

public class Node {
    private File folder;
    private ArrayList<Node> children;
    private long size;
    private int level;

    public Node (File folder){
        this.folder = folder;
        children = new ArrayList<>();
    }

    public File getFolder(){
        return folder;
    }

    public void addChild(Node node){
        node.setLevel(level + 1);
        children.add(node);
    }

    public ArrayList<Node> getChildren(){
        return children;
    }

    private void setLevel(int level) {
        this.level = level;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String toStringWithFilter(long sizeLimit){
        String size = SizeCalculator.getHumanReadableSize(getSize());
        StringBuilder builder = new StringBuilder();
        builder.append(folder.getName() + " - " + size + "\n");
        for(Node child : children){
/*            String indent = "  ";
            for (int i = 0; i < level; i++) { indent += "  "; }
            builder.append(indent + child.toString());*/
            // заменяем на repeat
            if (child.size >= sizeLimit) {
                builder.append("  ".repeat(level + 1) + child.toStringWithFilter(sizeLimit));
            }
        }
        return builder.toString();
    }
}
