package edu.ciukstar.cooper.application.graph;

import edu.ciukstar.cooper.domain.Graph;
import java.io.Serializable;
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

        Element start = new Element(null, "1em", "1em");
        start.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM));
        start.setStyleClass("start-node");
        start.setDraggable(true);
        model.addElement(start);
        graph.getNodes().stream().forEach(node -> {
            Element elem = new Element(node, "4em", "10em");
            elem.addEndPoint(new BlankEndPoint(EndPointAnchor.TOP));
            elem.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM_RIGHT));
            elem.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM_LEFT));
            elem.setDraggable(true);            
            model.addElement(elem);
        });

//        Element idle = new Element("Idle", "4em", "10em");
//        idle.addEndPoint(new BlankEndPoint(EndPointAnchor.TOP));
//        idle.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM_RIGHT));
//        idle.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM_LEFT));
//        idle.setDraggable(true);
//
//        Element turnedOn = new Element("TurnedOn", "10em", "15em");
//        turnedOn.addEndPoint(new BlankEndPoint(EndPointAnchor.TOP));
//        turnedOn.addEndPoint(new BlankEndPoint(EndPointAnchor.RIGHT));
//        turnedOn.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM_RIGHT));
//        turnedOn.setDraggable(true);
//
//        Element activity = new Element("Activity", "16em", "20em");
//        activity.addEndPoint(new BlankEndPoint(EndPointAnchor.LEFT));
//        activity.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM_LEFT));
//        activity.addEndPoint(new BlankEndPoint(EndPointAnchor.TOP));
//        activity.addEndPoint(new BlankEndPoint(EndPointAnchor.TOP_RIGHT));
//        activity.addEndPoint(new BlankEndPoint(EndPointAnchor.TOP_LEFT));
//        activity.setDraggable(true);
//
//        model.addElement(start);
//        model.addElement(idle);
//        model.addElement(turnedOn);
//        model.addElement(activity);
//
//        model.connect(createConnection(start.getEndPoints().get(0), idle.getEndPoints().get(0), null));
//        model.connect(createConnection(idle.getEndPoints().get(1), turnedOn.getEndPoints().get(0), "Turn On"));
//        model.connect(createConnection(turnedOn.getEndPoints().get(0), idle.getEndPoints().get(2), "Turn Off"));
//        model.connect(createConnection(turnedOn.getEndPoints().get(1), activity.getEndPoints().get(0), null));
//        model.connect(createConnection(activity.getEndPoints().get(1), turnedOn.getEndPoints().get(2), "Request Turn Off"));
//        model.connect(createConnection(activity.getEndPoints().get(2), activity.getEndPoints().get(2), "Talk"));
//        model.connect(createConnection(activity.getEndPoints().get(3), activity.getEndPoints().get(3), "Run"));
//        model.connect(createConnection(activity.getEndPoints().get(4), activity.getEndPoints().get(4), "Walk"));
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
