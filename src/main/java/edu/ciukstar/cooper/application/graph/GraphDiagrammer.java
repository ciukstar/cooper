package edu.ciukstar.cooper.application.graph;

import edu.ciukstar.cooper.domain.Edge;
import edu.ciukstar.cooper.domain.Graph;
import java.io.Serializable;
import java.util.Random;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.DiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.StateMachineConnector;
import org.primefaces.model.diagram.endpoint.BlankEndPoint;
import org.primefaces.model.diagram.endpoint.EndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;
import org.primefaces.model.diagram.overlay.ArrowOverlay;
import org.primefaces.model.diagram.overlay.LabelOverlay;

/**
 *
 * @author sergiu
 */
@Named
@ViewScoped
public class GraphDiagrammer implements Serializable {

    private DiagramModel diagram;

    public DiagramModel getDiagram(Graph graph) {
        return diagram == null ? diagram = buildDiagram(graph) : diagram;
    }

    DiagramModel buildDiagram(Graph graph) {
        DefaultDiagramModel model = new DefaultDiagramModel();
        model.setMaxConnections(-1);

        StateMachineConnector connector = new StateMachineConnector();
        connector.setOrientation(StateMachineConnector.Orientation.ANTICLOCKWISE);
        connector.setPaintStyle("{strokeStyle:'#7D7463',lineWidth:3}");
        model.setDefaultConnector(connector);

        Random rand = new Random();
        
        graph.getNodes().stream().forEach(node -> {
            Element elem = new Element(node, rand.nextInt(20) + "em", rand.nextInt(20) + "em");
            if (graph.isStartNode(node)) {
                elem.setStyleClass("start-node");
            }
            elem.setDraggable(true);
            model.addElement(elem);
        });

        graph.getEdges().forEach((edge) -> {
            Element source = model.getElements().stream().filter(e -> e.getData().equals(edge.getSource())).findAny().get();
            Element target = model.getElements().stream().filter(e -> e.getData().equals(edge.getTarget())).findAny().get();

            final EndPoint from = new BlankEndPoint(edge.isForward() ? EndPointAnchor.TOP : EndPointAnchor.BOTTOM);
            final EndPoint to = new BlankEndPoint(edge.isBackward() ? EndPointAnchor.BOTTOM : EndPointAnchor.TOP);
            source.addEndPoint(from);
            target.addEndPoint(to);
            model.connect(createConnection(from, to, edge.getTransitionName()));
        });
;
        return model;
    }

    Connection createConnection(EndPoint from, EndPoint to, String label) {
        Connection conn = new Connection(from, to);
        conn.getOverlays().add(new ArrowOverlay(20, 20, 1, 1));

        if (label != null) {
            conn.getOverlays().add(new LabelOverlay(label, "flow-label", 0.5));
        }

        return conn;
    }
}
